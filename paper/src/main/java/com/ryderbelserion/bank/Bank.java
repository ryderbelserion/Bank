package com.ryderbelserion.bank;

import org.bukkit.plugin.java.JavaPlugin;

public class Bank extends JavaPlugin {

    @Override
    public void onEnable() {
        getComponentLogger().info("<red>Bank is now enabled!");
    }

    @Override
    public void onDisable() {
        getComponentLogger().info("<red>Bank is now disabled!");
    }
}