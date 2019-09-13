package com.github.shynixn.blockball.core.logic.business.commandmenu

import com.github.shynixn.blockball.api.business.enumeration.*
import com.github.shynixn.blockball.api.persistence.entity.Arena
import com.github.shynixn.blockball.api.persistence.entity.ChatBuilder
import com.github.shynixn.blockball.core.logic.persistence.entity.ChatBuilderEntity

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
class BallModifierSettingsPage : Page(BallModifierSettingsPage.ID, BallSettingsPage.ID) {

    companion object {
        /** Id of the page. */
        const val ID = 31
    }

    /**
     * Returns the key of the command when this page should be executed.
     *
     * @return key
     */
    override fun getCommandKey(): MenuPageKey {
        return MenuPageKey.BALLMODIFIER
    }

    /**
     * Executes actions for this page.
     *
     * @param cache cache
     */
    override fun <P> execute(player: P, command: MenuCommand, cache: Array<Any?>, args: Array<String>): MenuCommandResult {
        val ballMeta = (cache[0] as Arena).meta.ballMeta.movementModifier
        if (command == MenuCommand.BALLMOD_HORIZONTALTOUCH && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.horizontalTouchModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_VERTICALTOUCH && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.verticalTouchModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_SHOTVELOCITY && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.shotVelocity = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_PASSVELOCITY && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.passVelocity = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_MAXSPIN && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.maximumSpinModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_MAXPITCH && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.maximumPitch = args[2].toInt()
        }
        else if (command == MenuCommand.BALLMOD_MINPITCH && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.minimumPitch = args[2].toInt()
        }
        else if (command == MenuCommand.BALLMOD_DEFAULTPITCH && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.defaultPitch = args[2].toInt()
        }
        else if (command == MenuCommand.BALLMOD_HORIZONTALTHROW && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.horizontalThrowModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_VERTICALTHROW && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.verticalThrowModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_ROLLINGDISTANCE && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.rollingDistanceModifier = args[2].toDouble()
        }
        else if (command == MenuCommand.BALLMOD_GRAVITY && args.size == 3 && args[2].toDoubleOrNull() != null) {
            ballMeta.gravityModifier = args[2].toDouble()
        }
        return super.execute(player, command, cache, args)
    }

    /**
     * Builds the page content.
     *
     * @param cache cache
     * @return content
     */
    override fun buildPage(cache: Array<Any?>): ChatBuilder {
        val ballMeta = (cache[0] as Arena).meta.ballMeta.movementModifier
        return ChatBuilderEntity()
                .component("- Touch Strength (Horizontal): " + ballMeta.horizontalTouchModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_HORIZONTALTOUCH.command)
                .setHoverText("Changes the horizontal speed modifier when a player touches a ball.")
                .builder().nextLine()
                .component("- Touch Strength (Vertical): " + ballMeta.verticalTouchModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_VERTICALTOUCH.command)
                .setHoverText("Changes the vertical speed modifier when a player touches a ball.")
                .builder().nextLine()
                .component("- Shoot Velocity: " + ballMeta.shotVelocity).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_SHOTVELOCITY.command)
                .setHoverText("Changes the speed modifier when a player left clicks a ball.")
                .builder().nextLine()
                .component("- Pass Velocity: " + ballMeta.passVelocity).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_PASSVELOCITY.command)
                .setHoverText("Changes the speed modifier when a player right clicks a ball.")
                .builder().nextLine()
                .component("- Maximum Spin Velocity: " + ballMeta.passVelocity).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_MAXSPIN.command)
                .setHoverText("Changes the spin modifier when a player tilts head left/right after shooting the ball.")
                .builder().nextLine()
                .component("- Maximum Pitch (Vertical): " + ballMeta.maximumPitch).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_MAXPITCH.command)
                .setHoverText("Changes the maximum angle of pitch when a player kicks or throws the ball.")
                .builder().nextLine()
                .component("- Minimum Pitch (Vertical): " + ballMeta.minimumPitch).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_MINPITCH.command)
                .setHoverText("Changes the minimum angle of pitch when a player kicks or throws the ball.")
                .builder().nextLine()
                .component("- Initial Pitch (Vertical): " + ballMeta.defaultPitch).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_DEFAULTPITCH.command)
                .setHoverText("Changes the angle of pitch when a player kicks or throws the ball without pitch control.")
                .builder().nextLine()
                .component("- Throw Strength (Horizontal): " + ballMeta.horizontalThrowModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_HORIZONTALTHROW.command)
                .setHoverText("Changes the horizontal speed modifier when a player throws the ball after grabbing it by rightclicking.")
                .builder().nextLine()
                .component("- Throw Strength (Vertical): " + ballMeta.verticalThrowModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_VERTICALTHROW.command)
                .setHoverText("Changes the vertical speed modifier when a player throws the ball after grabbing it by rightclicking.")
                .builder().nextLine()
                .component("- Rolling Distance modifier: " + ballMeta.rollingDistanceModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_ROLLINGDISTANCE.command)
                .setHoverText("Changes time modifier how long a ball keeps rolling.")
                .builder().nextLine()
                .component("- Gravity modifier: " + ballMeta.gravityModifier).builder()
                .component(MenuClickableItem.EDIT.text).setColor(MenuClickableItem.EDIT.color)
                .setClickAction(ChatClickAction.SUGGEST_COMMAND, MenuCommand.BALLMOD_GRAVITY.command)
                .setHoverText("Changes gravity modifier which decides how fast a ball falls onto the ground. Negative values will cause the ball to float upwards.")
                .builder().nextLine()
    }
}