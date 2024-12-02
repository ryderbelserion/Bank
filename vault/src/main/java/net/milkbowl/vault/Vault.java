package net.milkbowl.vault;

import com.google.common.collect.ImmutableMap;
import net.milkbowl.vault.api.VaultService;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.listeners.ServiceListeners;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import java.util.Map;

@ApiStatus.Internal
public class Vault extends JavaPlugin {

    private final Map<Class<?>, VaultService<?>> VAULT_SERVICES = ImmutableMap.<Class<?>, VaultService<?>>builder()
            .put(Economy.class, new VaultService<>(Economy.class, Economy::getName))
            .put(Permission.class, new VaultService<>(Permission.class, Permission::getName))
            .put(Chat.class, new VaultService<>(Chat.class, Chat::getName))
            .build();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ServiceListeners(this), this);

        getComponentLogger().info("<red>Vault compatibility layer is enabled.");
    }

    @ApiStatus.Internal
    public final Map<Class<?>, VaultService<?>> getVaultServices() {
        return this.VAULT_SERVICES;
    }
}