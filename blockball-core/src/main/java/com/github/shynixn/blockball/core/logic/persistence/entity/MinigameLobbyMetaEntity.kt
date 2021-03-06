package com.github.shynixn.blockball.core.logic.persistence.entity

import com.github.shynixn.blockball.api.business.annotation.YamlSerialize
import com.github.shynixn.blockball.api.business.enumeration.PlaceHolder
import com.github.shynixn.blockball.api.persistence.entity.Position
import com.github.shynixn.blockball.api.persistence.entity.MinigameLobbyMeta

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
class MinigameLobbyMetaEntity : MinigameLobbyMeta {
    /** Duration the match will max last. */
    @YamlSerialize(orderNumber = 2, value = "lobby-duration")
    override var lobbyDuration: Int = 20
    /** Duration the match will max last. */
    @YamlSerialize(orderNumber = 1, value = "match-duration")
    override var matchDuration: Int = 300
    /** Spawnpoint of the player in the lobby. */
    @YamlSerialize(orderNumber = 3, value = "lobby-spawnpoint", implementation = PositionEntity::class)
    override var lobbySpawnpoint: Position? = null
    /** Message being played in the action bar displaying the joined players how many players are remaining to start. */
    @YamlSerialize(orderNumber = 4, value = "remaining-players-message")
    override var playersRequiredToStartMessage: String = "&aWaiting for &c" + PlaceHolder.REMAINING_PLAYERS_TO_START.placeHolder + "&a more player(s)..."
}