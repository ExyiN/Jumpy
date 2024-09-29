package me.exyin.jumpy;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

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
        jumpy.getJumpManager().disableJump(event.getPlayer());
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
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!jumpy.getJumpManager().isValidGameMode(player.getGameMode())
                || !jumpy.getJumpManager().isJumpEnabled(player)
                || jumpy.getJumpManager().isOnCooldown(player)
                || jumpy.getJumpManager().isJumpsLeftAtMax(player)) {
            return;
        }

        if (player.isOnGround() && jumpy.getJumpManager().canEnableJump(player)) {
            jumpy.getJumpManager().enableJump(player);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (!jumpy.getJumpManager().isValidGameMode(player.getGameMode())
                || !jumpy.getJumpManager().isJumpEnabled(player)
                || !jumpy.getJumpManager().hasJumpsLeft(player)
                || jumpy.getJumpManager().isOnCooldown(player)) {
            return;
        }

        // Make the player jump
        event.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().multiply(jumpy.getConfigManager().getVelocity()).setY(jumpy.getConfigManager().getVelocityY()));

        if (jumpy.getConfigManager().isSoundEnabled()) {
            player.playSound(player.getLocation(), Sound.valueOf(jumpy.getConfigManager().getSound()), jumpy.getConfigManager().getSoundVolume(), jumpy.getConfigManager().getSoundPitch());
        }

        jumpy.getJumpManager().removeOneJumpLeft(player);

        // Cooldown
        if (jumpy.getConfigManager().getCooldown() > 0 && !jumpy.getJumpManager().hasJumpsLeft(player)) {
            jumpy.getJumpManager().setOnCooldown(player);
            player.setAllowFlight(false);
            Bukkit.getScheduler().runTaskLater(jumpy, () -> jumpy.getJumpManager().removeOnCooldown(player), jumpy.getConfigManager().getCooldown());
        }
    }
}
