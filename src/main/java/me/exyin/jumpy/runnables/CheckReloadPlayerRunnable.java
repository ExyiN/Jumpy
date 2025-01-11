package me.exyin.jumpy.runnables;

import me.exyin.jumpy.Jumpy;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckReloadPlayerRunnable extends BukkitRunnable {
    private final Jumpy jumpy;

    public CheckReloadPlayerRunnable(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    @Override
    public void run() {
        jumpy.getServer().getOnlinePlayers().forEach(player -> {
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
        });
    }
}
