package net.milkbowl.vault.listeners;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.api.VaultService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ServiceListeners implements Listener {

    private final Vault vault;
    private final ComponentLogger logger;

    @ApiStatus.Internal
    public ServiceListeners(final Vault vault) {
        this.vault = vault;
        this.logger = vault.getComponentLogger();
    }

    @EventHandler
    public void onServiceRegister(ServiceRegisterEvent event) {
        final RegisteredServiceProvider<?> provider = event.getProvider();
        final VaultService<?> vaultService = this.vault.getVaultServices().get(provider.getService());

        if (vaultService != null) {
            this.logger.info(vaultService.registeredMsg(provider.getProvider()));
        }
    }

    @EventHandler
    public void onServiceUnregister(ServiceUnregisterEvent event) {
        final RegisteredServiceProvider<?> provider = event.getProvider();
        final VaultService<?> vaultService = this.vault.getVaultServices().get(provider.getService());

        if (vaultService != null) {
            this.logger.info(vaultService.unregisteredMsg(provider.getProvider()));
        }
    }
}