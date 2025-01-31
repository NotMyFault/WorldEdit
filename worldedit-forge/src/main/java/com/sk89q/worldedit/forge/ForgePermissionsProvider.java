/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.forge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

public interface ForgePermissionsProvider {

    boolean hasPermission(ServerPlayer player, String permission);

    void registerPermission(String permission);

    class VanillaPermissionsProvider implements ForgePermissionsProvider {

        private final ForgePlatform platform;

        public VanillaPermissionsProvider(ForgePlatform platform) {
            this.platform = platform;
        }

        @Override
        public boolean hasPermission(ServerPlayer player, String permission) {
            ForgeConfiguration configuration = platform.getConfiguration();
            return configuration.cheatMode
                || ServerLifecycleHooks.getCurrentServer().getPlayerList().isOp(player.getGameProfile())
                || (configuration.creativeEnable && player.gameMode.getGameModeForPlayer() == GameType.CREATIVE);
        }

        @Override
        public void registerPermission(String permission) {
        }
    }

    // TODO Re-add when Sponge for 1.14 is out
    //    class SpongePermissionsProvider implements ForgePermissionsProvider {
    //
    //        @Override
    //        public boolean hasPermission(EntityPlayerMP player, String permission) {
    //            return ((Player) player).hasPermission(permission);
    //        }
    //
    //        @Override
    //        public void registerPermission(ICommand command, String permission) {
    //
    //        }
    //    }
}
