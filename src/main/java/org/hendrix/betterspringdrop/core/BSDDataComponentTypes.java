package org.hendrix.betterspringdrop.core;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.component.type.FirefliesComponent;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

import java.util.function.UnaryOperator;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link ComponentType Data Component Types}
 */
public final class BSDDataComponentTypes {

    //#region Data Component Types

    public static final ComponentType<FirefliesComponent> FIREFLIES = register("fireflies", (builder) -> builder.codec(FirefliesComponent.CODEC).packetCodec(FirefliesComponent.PACKET_CODEC));

    //#endregion

    /**
     * Register a {@link ComponentType<T> Data Component Type}
     *
     * @param name The {@link String Component Type Name}
     * @param builderOperator The {@link ComponentType.Builder Data Component Type Builder}
     * @return The {@link ComponentType<T> registered Data Component Type}
     * @param <T> The {@link T Data Component Type class}
     */
    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, IdentifierUtils.modIdentifier(name), builderOperator.apply(ComponentType.builder()).build());
    }

    /**
     * Register all {@link ComponentType Data Component Types}
     */
    public static void register() {

    }

}
