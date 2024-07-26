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
}
