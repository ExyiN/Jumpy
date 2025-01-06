package me.exyin.jumpy.listeners;

import me.exyin.jumpy.Jumpy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JumpListener implements Listener {
    private final Jumpy jumpy;

    public JumpListener(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTask(jumpy, () -> jumpy.getJumpManager().reloadPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        jumpy.getJumpManager().deactivateJump(event.getPlayer());
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Bukkit.getScheduler().runTask(jumpy, () -> jumpy.getJumpManager().reloadPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Bukkit.getScheduler().runTask(jumpy, () -> jumpy.getJumpManager().reloadPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        jumpy.getJumpManager().reloadPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("jumpy.use")
                || jumpy.getJumpManager().isJumpDisabled(player)
                || !jumpy.getJumpManager().isValidGameMode(player.getGameMode())
                || jumpy.getJumpManager().isOnCooldown(player)
                || jumpy.getJumpManager().isJumpsLeftAtMax(player)) {
            return;
        }

        if (player.isOnGround()) {
            jumpy.getJumpManager().reloadPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("jumpy.use") && jumpy.getJumpManager().isInJumpsLeftList(player)) {
            jumpy.getJumpManager().deactivateJump(player);
            return;
        }
        if (!player.hasPermission("jumpy.use") || jumpy.getJumpManager().isJumpDisabled(player)
                || !jumpy.getJumpManager().isValidGameMode(player.getGameMode())
                || !jumpy.getJumpManager().hasJumpsLeft(player)
                || jumpy.getJumpManager().isOnCooldown(player)) {
            return;
        }

        event.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().multiply(jumpy.getConfigManager().getVelocity()).setY(jumpy.getConfigManager().getVelocityY()));

        if (jumpy.getConfigManager().isSoundEnabled()) {
            player.playSound(player.getLocation(), Sound.valueOf(jumpy.getConfigManager().getSound()), jumpy.getConfigManager().getSoundVolume(), jumpy.getConfigManager().getSoundPitch());
        }

        jumpy.getJumpManager().removeOneJumpLeft(player);

        if (!jumpy.getJumpManager().hasJumpsLeft(player)) {
            player.setAllowFlight(false);
            if (jumpy.getConfigManager().getCooldown() > 0) {
                jumpy.getJumpManager().setOnCooldown(player);
                Bukkit.getScheduler().runTaskLater(jumpy, () -> jumpy.getJumpManager().removeOnCooldown(player), jumpy.getConfigManager().getCooldown() * 20);
            }
        }
    }
}
