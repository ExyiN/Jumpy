package me.exyin.jumpy;

import net.luckperms.api.node.Node;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class JumpManager {
    private final Jumpy jumpy;
    private final Map<UUID, Integer> jumpsLeftList = new HashMap<>();
    private final Set<UUID> cooldownList = new HashSet<>();

    public JumpManager(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    public boolean isValidGameMode(GameMode gameMode) {
        return gameMode == GameMode.ADVENTURE || gameMode == GameMode.SURVIVAL;
    }

    public void enableJump(Player player) {
        jumpy.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().remove(Node.builder("jumpy.disabled").build());
            activateJump(player);
        });
    }

    public void disableJump(Player player) {
        jumpy.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().add(Node.builder("jumpy.disabled").build());
            deactivateJump(player);
        });
    }

    public void activateJump(Player player) {
        player.setAllowFlight(true);
        player.setFlying(false);
        jumpsLeftList.put(player.getUniqueId(), getMaxJumps(player));
    }

    public void deactivateJump(Player player) {
        if (isValidGameMode(player.getGameMode())) {
            player.setAllowFlight(false);
        }
        jumpsLeftList.remove(player.getUniqueId());
    }

    public boolean isJumpDisabled(Player player) {
        return player.hasPermission("jumpy.disabled");
    }

    public boolean isInJumpsLeftList(Player player) {
        return jumpsLeftList.containsKey(player.getUniqueId());
    }

    public int getMaxJumps(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return 1000;
        }
        for (int i = 10; i >= 2; i--) {
            if (player.hasPermission("jumpy.max_jumps." + i)) {
                return i;
            }
        }
        return 1;
    }

    public boolean hasJumpsLeft(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return true;
        }
        return jumpsLeftList.getOrDefault(player.getUniqueId(), 0) > 0;
    }

    public void removeOneJumpLeft(Player player) {
        if (player.hasPermission("jumpy.infinite")) {
            return;
        }
        jumpsLeftList.put(player.getUniqueId(), jumpsLeftList.get(player.getUniqueId()) - 1);
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
        return jumpsLeftList.getOrDefault(player.getUniqueId(), getMaxJumps(player)) == getMaxJumps(player);
    }

    public void reloadPlayer(Player player) {
        if (!player.hasPermission("jumpy.use") || isJumpDisabled(player) || !isValidGameMode(player.getGameMode()) || isOnCooldown(player)) {
            return;
        }
        activateJump(player);
    }
}
