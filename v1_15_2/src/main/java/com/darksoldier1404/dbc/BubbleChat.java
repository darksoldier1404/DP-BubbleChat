package com.darksoldier1404.dbc;

import com.darksoldier1404.dbc.commands.DBCCommand;
import com.darksoldier1404.dbc.events.DBCEvent;
import com.darksoldier1404.dbc.functions.DBCFunction;
import com.darksoldier1404.dppc.utils.DataContainer;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BubbleChat extends JavaPlugin {
    private static BubbleChat plugin;
    public static DataContainer data;

    public static Map<UUID, Boolean> enabled = new HashMap<>();

    public static double offset;
    public static double distance;

    public static BubbleChat getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        data = new DataContainer(plugin);
        offset = data.getConfig().getDouble("Settings.offset");
        distance = data.getConfig().getDouble("Settings.distance");
        plugin.getServer().getPluginManager().registerEvents(new DBCEvent(), plugin);
        getCommand("dbc").setExecutor(new DBCCommand());
    }

    @Override
    public void onDisable() {
        DBCFunction.bubbles.values().forEach(entities -> entities.forEach(Entity::remove));
    }
}
