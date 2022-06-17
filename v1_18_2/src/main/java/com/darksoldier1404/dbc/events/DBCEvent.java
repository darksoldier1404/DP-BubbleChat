package com.darksoldier1404.dbc.events;

import com.darksoldier1404.dbc.BubbleChat;
import com.darksoldier1404.dbc.functions.DBCFunction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("ALL")
public class DBCEvent implements Listener {
    private final BubbleChat plugin = BubbleChat.getInstance();

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        DBCFunction.sendBubble(e.getPlayer(), e.getMessage());
    }
}
