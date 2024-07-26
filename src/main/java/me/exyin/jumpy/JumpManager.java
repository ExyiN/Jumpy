package me.exyin.jumpy;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class JumpManager {
    public boolean canEnableJump(Player player) {
        return player.hasPermission("jumpy.use");
    }

    public boolean isValidGamemode(GameMode gameMode) {
        return gameMode == GameMode.ADVENTURE || gameMode == GameMode.SURVIVAL;
    }

    public void enableJump(Player player) {
        player.setAllowFlight(true);
    }
}
