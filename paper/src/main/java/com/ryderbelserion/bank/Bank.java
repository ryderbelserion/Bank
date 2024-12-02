package com.ryderbelserion.bank;

import org.bukkit.plugin.java.JavaPlugin;

public class Bank extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("Vault-1.7.3.jar", false);

        getComponentLogger().info("<red>Bank is now enabled!");
    }

    @Override
    public void onDisable() {
        getComponentLogger().info("<red>Bank is now disabled!");
    }
}