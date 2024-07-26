package me.exyin.jumpy;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class JumpManager {
    private final Map<UUID, Integer> enabledList = new HashMap<>();
    private final Set<UUID> cooldownList = new HashSet<>();

    public boolean canEnableJump(Player player) {
        return player.hasPermission("jumpy.use");
    }

    public boolean isValidGameMode(GameMode gameMode) {
        return gameMode == GameMode.ADVENTURE || gameMode == GameMode.SURVIVAL;
    }

    public void enableJump(Player player) {
        player.setAllowFlight(true);
        enabledList.put(player.getUniqueId(), getMaxJumps(player));
    }

    public void disableJump(Player player) {
        player.setAllowFlight(false);
        enabledList.remove(player.getUniqueId());
    }

    public boolean isJumpEnabled(Player player) {
        return enabledList.containsKey(player.getUniqueId());
    }

    public int getMaxJumps(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return 1000;
        }
        for (int i = 10; i >= 3; i--) {
            if (player.hasPermission("jumpy.maxjumps." + i)) {
                return i;
            }
        }
        return 2;
    }

    public boolean hasJumpsLeft(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return true;
        }
        return enabledList.getOrDefault(player.getUniqueId(), 0) > 0;
    }

    public void removeOneJumpLeft(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return;
        }
        enabledList.put(player.getUniqueId(), enabledList.get(player.getUniqueId()) - 1);
    }

    public void setOnCooldown(Player player) {
        cooldownList.add(player.getUniqueId());
    }

    public void removeOnCooldown(Player player) {
        cooldownList.remove(player.getUniqueId());
    }

    public boolean isOnCooldown(Player player) {
        return cooldownList.contains(player.getUniqueId());
    }

    public boolean isJumpsLeftAtMax(Player player) {
        return enabledList.getOrDefault(player.getUniqueId(), getMaxJumps(player)) == getMaxJumps(player);
    }
}
