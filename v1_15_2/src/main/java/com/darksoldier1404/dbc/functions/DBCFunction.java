package com.darksoldier1404.dbc.functions;

import com.darksoldier1404.dbc.BubbleChat;
import com.darksoldier1404.dppc.utils.DataContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class DBCFunction {
    private static final BubbleChat plugin = BubbleChat.getInstance();
    private static final DataContainer data = plugin.data;

    public static final Map<UUID, ArrayList<Entity>> bubbles = new HashMap<>();

    public static void setEnabled(Player p, boolean enabled) {
        plugin.enabled.put(p.getUniqueId(), enabled);
        p.sendMessage(data.getPrefix() + "You have " + (enabled ? "enabled" : "disabled") + " the bubble chat.");
    }

    public static void sendBubble(Player p, String message) {
        Location loc = p.getLocation();
        ArrayList<Entity> entities = bubbles.get(p.getUniqueId()) == null ? new ArrayList<>() : bubbles.get(p.getUniqueId());
        for (Entity e : entities) {
            e.teleport(e.getLocation().add(0, plugin.distance, 0));
        }
        ArmorStand ar = p.getWorld().spawn(loc.add(0, plugin.offset, 0), ArmorStand.class);
        ar.setVisible(false);
        ar.setMarker(true);
        ar.setCustomName(message);
        ar.setCustomNameVisible(true);
        ar.setGravity(false);
        entities.add(ar);
        bubbles.put(p.getUniqueId(), entities);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(entities.size() > 0) {
                entities.get(0).remove();
                entities.remove(0);
                bubbles.put(p.getUniqueId(), entities);
            }
        }, 20 * 5);
    }
}