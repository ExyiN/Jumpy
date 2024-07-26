package me.exyin.jumpy;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jumpy extends JavaPlugin {
    private JumpManager jumpManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        jumpManager = new JumpManager();
        configManager = new ConfigManager(this);
        configManager.setupValues();

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
}
