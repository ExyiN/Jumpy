package me.exyin.jumpy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jumpy extends JavaPlugin {
    private JumpManager jumpManager;
    private ConfigManager configManager;
    private LuckPerms luckPerms;
    private MiniMessage mm;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        jumpManager = new JumpManager(this);
        configManager = new ConfigManager(this);
        configManager.setupValues();
        luckPerms = LuckPermsProvider.get();
        mm = MiniMessage.miniMessage();

        getServer().getPluginManager().registerEvents(new JumpListener(this), this);
        Objects.requireNonNull(getCommand("jumpy")).setExecutor(new JumpyCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

    public MiniMessage getMinimessage() {
        return mm;
    }
}
