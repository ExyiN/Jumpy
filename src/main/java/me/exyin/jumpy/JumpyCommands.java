package me.exyin.jumpy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JumpyCommands implements CommandExecutor, TabCompleter {
    private final Jumpy jumpy;

    public JumpyCommands(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length != 1) {
            return false;
        }

        switch (args[0]) {
            case "reload":
                if (!commandSender.hasPermission("jumpy.reload")) {
                    commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageNoPerm()));
                    break;
                }
                jumpy.reloadConfig();
                jumpy.getConfigManager().setupValues();
                commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageReload()));
                break;
            case "on":
                if (!(commandSender instanceof Player player)) {
                    commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageMustBePlayer()));
                    break;
                }
                if (!player.hasPermission("jumpy.use")) {
                    commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageNoPerm()));
                    break;
                }
                if (!jumpy.getJumpManager().isJumpDisabled(player)) {
                    player.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageJumpAlreadyOn()));
                    break;
                }
                player.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageJumpOn()));
                jumpy.getJumpManager().enableJump(player);

                break;
            case "off":
                if (!(commandSender instanceof Player player)) {
                    commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageMustBePlayer()));
                    break;
                }
                if (!player.hasPermission("jumpy.use")) {
                    commandSender.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageNoPerm()));
                    break;
                }
                if (jumpy.getJumpManager().isJumpDisabled(player)) {
                    player.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageJumpAlreadyOff()));
                    break;
                }
                player.sendMessage(jumpy.getMinimessage().deserialize(jumpy.getConfigManager().getMessagePrefix() + jumpy.getConfigManager().getMessageJumpOff()));
                jumpy.getJumpManager().disableJump(player);

                break;
            default:
                return false;
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tabCompleteList = new ArrayList<>();
        if (commandSender.hasPermission("jumpy.use")) {
            tabCompleteList.add("on");
            tabCompleteList.add("off");
        }
        if (commandSender.hasPermission("jumpy.reload")) {
            tabCompleteList.add("reload");
        }
        return tabCompleteList;
    }
}
