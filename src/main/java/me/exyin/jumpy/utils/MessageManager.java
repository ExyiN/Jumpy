package me.exyin.jumpy.utils;

import me.exyin.jumpy.Jumpy;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessageManager {
    private final Jumpy jumpy;
    private final MiniMessage miniMessage;

    public MessageManager(Jumpy jumpy) {
        this.jumpy = jumpy;
        miniMessage = MiniMessage.miniMessage();
    }

    public void sendMessage(Audience audience, String message) {
        if (message.isBlank()) {
            return;
        }
        audience.sendMessage(miniMessage.deserialize(jumpy.getConfigManager().getMessagePrefix() + message));
    }
}
