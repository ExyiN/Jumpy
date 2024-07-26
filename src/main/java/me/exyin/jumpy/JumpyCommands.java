package me.exyin.jumpy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class JumpyCommands implements CommandExecutor {
    private final Jumpy jumpy;

    public JumpyCommands(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (args.length != 1) {
            return false;
        }

        switch (args[0]) {
            case "reload":
                jumpy.reloadConfig();
                jumpy.getConfigManager().setupValues();
                commandSender.sendMessage(ChatColor.DARK_GREEN + "Config file reloaded.");
                break;
        }
        return true;
    }
}
