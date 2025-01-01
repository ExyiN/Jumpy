package me.exyin.jumpy;

public class ConfigManager {
    private final Jumpy jumpy;

    private double velocity;
    private double velocityY;
    private int cooldown;
    private boolean isSoundEnabled;
    private String sound;
    private float soundVolume;
    private float soundPitch;
    private String messagePrefix;
    private String messageNoPerm;
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
        messageMustBePlayer = jumpy.getConfig().getString("message.mustbeplayer");
        messageJumpAlreadyOn = jumpy.getConfig().getString("message.jumpalreadyon");
        messageJumpAlreadyOff = jumpy.getConfig().getString("message.jumpalreadyoff");
        messageJumpOn = jumpy.getConfig().getString("message.jumpon");
        messageJumpOff = jumpy.getConfig().getString("message.jumpoff");
        messageReload = jumpy.getConfig().getString("message.reload");
    }

    public double getVelocity() {
        return velocity;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public int getCooldown() {
        return cooldown * 20;
    }

    public boolean isSoundEnabled() {
        return isSoundEnabled;
    }

    public String getSound() {
        return sound;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public float getSoundPitch() {
        return soundPitch;
    }

    public String getMessagePrefix() {
        return messagePrefix;
    }

    public String getMessageNoPerm() {
        return messageNoPerm;
    }

    public String getMessageMustBePlayer() {
        return messageMustBePlayer;
    }

    public String getMessageJumpAlreadyOn() {
        return messageJumpAlreadyOn;
    }

    public String getMessageJumpAlreadyOff() {
        return messageJumpAlreadyOff;
    }

    public String getMessageJumpOn() {
        return messageJumpOn;
    }

    public String getMessageJumpOff() {
        return messageJumpOff;
    }

    public String getMessageReload() {
        return messageReload;
    }
}
