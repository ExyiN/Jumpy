package me.exyin.jumpy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class JumpListener implements Listener {
    private final Jumpy jumpy;

    public JumpListener(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (jumpy.getJumpManager().canEnableJump(player)) {
            jumpy.getJumpManager().enableJump(player);
        }
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (jumpy.getJumpManager().isValidGamemode(event.getNewGameMode()) && jumpy.getJumpManager().canEnableJump(player)) {
            Bukkit.getScheduler().runTask(jumpy, () -> jumpy.getJumpManager().enableJump(player));
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (!jumpy.getJumpManager().isValidGamemode(player.getGameMode())) {
            return;
        }
        event.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().multiply(jumpy.getConfigManager().getVelocity()).setY(jumpy.getConfigManager().getVelocityY()));
    }
}
