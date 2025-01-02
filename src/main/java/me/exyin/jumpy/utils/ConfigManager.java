package me.exyin.jumpy.utils;

import lombok.AccessLevel;
import lombok.Getter;
import me.exyin.jumpy.Jumpy;

@Getter
public class ConfigManager {
    @Getter(AccessLevel.NONE)
    private final Jumpy jumpy;

    private double velocity;
    private double velocityY;
    private long cooldown;
    private boolean isSoundEnabled;
    private String sound;
    private float soundVolume;
    private float soundPitch;
    private String messagePrefix;
    private String messageNoPerm;
    private String messageHelp;
    private String messageMustBePlayer;
    private String messageJumpAlreadyOn;
    private String messageJumpAlreadyOff;
    private String messageJumpOn;
    private String messageJumpOff;
    private String messageReload;

    public ConfigManager(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    public void setupValues() {
        velocity = jumpy.getConfig().getDouble("velocity");
        velocityY = jumpy.getConfig().getDouble("velocityY");
        cooldown = jumpy.getConfig().getInt("cooldown");
        isSoundEnabled = jumpy.getConfig().getBoolean("sound.enabled");
        sound = jumpy.getConfig().getString("sound.sound");
        soundVolume = (float) jumpy.getConfig().getDouble("sound.volume");
        soundPitch = (float) jumpy.getConfig().getDouble("sound.pitch");
        messagePrefix = jumpy.getConfig().getString("message.prefix");
        messageNoPerm = jumpy.getConfig().getString("message.noperm");
        messageHelp = jumpy.getConfig().getString("message.help");
        messageMustBePlayer = jumpy.getConfig().getString("message.mustbeplayer");
        messageJumpAlreadyOn = jumpy.getConfig().getString("message.jumpalreadyon");
        messageJumpAlreadyOff = jumpy.getConfig().getString("message.jumpalreadyoff");
        messageJumpOn = jumpy.getConfig().getString("message.jumpon");
        messageJumpOff = jumpy.getConfig().getString("message.jumpoff");
        messageReload = jumpy.getConfig().getString("message.reload");
    }
}
