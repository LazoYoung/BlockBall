@file:Suppress("unused")

package com.github.shynixn.blockball.sponge.logic.business.listener

import com.github.shynixn.blockball.api.business.enumeration.Team
import com.github.shynixn.blockball.api.business.service.ConcurrencyService
import com.github.shynixn.blockball.api.business.service.PersistenceStatsService
import com.github.shynixn.blockball.api.business.service.StatsCollectingService
import com.github.shynixn.blockball.api.sponge.event.GameEndEvent
import com.github.shynixn.blockball.api.sponge.event.GameGoalEvent
import com.github.shynixn.blockball.api.sponge.event.GameJoinEvent
import com.github.shynixn.blockball.core.logic.business.extension.sync
import com.github.shynixn.blockball.core.logic.business.extension.thenAcceptSafely
import com.google.inject.Inject
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.network.ClientConnectionEvent
import java.util.*

/**
 * Created by Shynixn 2018.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2018 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
class StatsListener @Inject constructor(
    private val persistenceStatsService: PersistenceStatsService,
    private val statsCollectingService: StatsCollectingService,
    private val concurrencyService: ConcurrencyService
) {
    private val alreadyLoading = HashSet<UUID>()
    private val joinCooldown = 20 * 6L

    /**
     * Sets the stats scoreboard for a player when he joins the server.
     *
     * @param event event.
     */
    @Listener
    fun onPlayerJoinEvent(event: ClientConnectionEvent.Join) {
        val uuid = event.targetEntity.uniqueId

        if (alreadyLoading.contains(uuid)) {
            return
        }

        alreadyLoading.add(uuid)

        sync(concurrencyService, joinCooldown) {
            this.loadStats(event.targetEntity)
        }
    }

    /**
     * Removes the stats of a player.
     *
     * @param event event
     */
    @Listener
    fun playerQuitEvent(event: ClientConnectionEvent.Disconnect) {
        this.statsCollectingService.cleanResources(event.targetEntity)
        this.persistenceStatsService.clearResources(event.targetEntity)
    }

    /**
     * Updates the goals of a player when he shoots a goal.
     *
     * @param event event
     */
    @Listener
    fun onPlayerShootGoalEvent(event: GameGoalEvent) {
        persistenceStatsService.getStatsFromPlayer(event.player).amountOfGoals += 1
        statsCollectingService.setStatsScoreboard(event.player)
    }

    /**
     * Gets called when a player joins the match
     *
     * @param event event
     */
    @Listener
    fun onPlayerJoinGameEvent(event: GameJoinEvent) {
        persistenceStatsService.getStatsFromPlayer(event.player).amountOfPlayedGames += 1
        statsCollectingService.setStatsScoreboard(event.player)
    }

    /**
     * Gets called when a game gets won.
     *
     * @param event event
     */
    @Listener
    fun onTeamWinEvent(event: GameEndEvent) {
        var winningPlayers = event.game.redTeam

        if (event.winningTeam == Team.BLUE) {
            winningPlayers = event.game.blueTeam
        }

        winningPlayers.forEach { p ->
            persistenceStatsService.getStatsFromPlayer(p).amountOfWins += 1
            statsCollectingService.setStatsScoreboard(p)
        }
    }

    /**
     * Loads the Stats data.
     */
    private fun loadStats(player: Player) {
        if (!player.isOnline) {
            return
        }

        persistenceStatsService.refreshStatsFromPlayer(player).thenAcceptSafely {
            if (alreadyLoading.contains(player.uniqueId)) {
                alreadyLoading.remove(player.uniqueId)
            }

            statsCollectingService.setStatsScoreboard(player)
        }
    }
}