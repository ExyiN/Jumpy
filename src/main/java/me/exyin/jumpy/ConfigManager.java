package me.exyin.jumpy;

public class ConfigManager {
    private final Jumpy jumpy;

    private Double velocity;
    private Double velocityY;

    public ConfigManager(Jumpy jumpy) {
        this.jumpy = jumpy;
    }

    public void setupValues() {
        velocity = jumpy.getConfig().getDouble("velocity");
        velocityY = jumpy.getConfig().getDouble("velocityY");
    }

    public double getVelocity() {
        return velocity;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
