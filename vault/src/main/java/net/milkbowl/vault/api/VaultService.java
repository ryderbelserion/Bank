package net.milkbowl.vault.api;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.jetbrains.annotations.ApiStatus;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApiStatus.Internal
public final class VaultService<T> {

    private final String name;
    private final Class<T> clazz;
    private final Function<T, String> providerNameFunc;

    @ApiStatus.Internal
    public VaultService(Class<T> clazz, Function<T, String> providerNameFunc) {
        this.name = clazz.getSimpleName();
        this.clazz = clazz;
        this.providerNameFunc = providerNameFunc;
    }

    @ApiStatus.Internal
    public String registeredMsg(Object provider) {
        T castedProvider = this.clazz.cast(provider);
        return "New " + this.name + " service registered: " + this.providerNameFunc.apply(castedProvider);
    }

    @ApiStatus.Internal
    public String unregisteredMsg(Object provider) {
        T castedProvider = this.clazz.cast(provider);

        return this.name + " service unregistered: " + this.providerNameFunc.apply(castedProvider);
    }

    @ApiStatus.Internal
    public String infoMsg(ServicesManager servicesManager) {
        RegisteredServiceProvider<T> registration = servicesManager.getRegistration(this.clazz);
        String provider = registration == null ? "None" : this.providerNameFunc.apply(registration.getProvider());

        String providers = servicesManager.getRegistrations(this.clazz).stream()
                .map(this.providerNameFunc.compose(RegisteredServiceProvider::getProvider))
                .collect(Collectors.joining(", ", "[", "]"));

        return this.name + ": " + provider + " " + providers;
    }
}