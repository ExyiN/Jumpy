package me.exyin.jumpy;

import me.exyin.jumpy.commands.JumpyCommands;
import me.exyin.jumpy.listeners.JumpListener;
import me.exyin.jumpy.utils.ConfigManager;
import me.exyin.jumpy.utils.JumpManager;
import me.exyin.jumpy.utils.MessageManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jumpy extends JavaPlugin {
    private JumpManager jumpManager;
    private ConfigManager configManager;
    private MessageManager messageManager;
    private LuckPerms luckPerms;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        jumpManager = new JumpManager(this);
        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);
        configManager.setupValues();
        luckPerms = LuckPermsProvider.get();

        getServer().getPluginManager().registerEvents(new JumpListener(this), this);
        Objects.requireNonNull(getCommand("jumpy")).setExecutor(new JumpyCommands(this));
    }

    public JumpManager getJumpManager() {
        return jumpManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
