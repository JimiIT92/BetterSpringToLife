package org.hendrix.betterspringtolife.core;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import org.hendrix.betterspringtolife.entity.Butterfly;
import org.hendrix.betterspringtolife.item.component.FirefliesComponent;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.function.UnaryOperator;

public final class BSTLDataComponentTypes {

    //#region Data Component Types

    public static final DataComponentType<FirefliesComponent> FIREFLIES = register("fireflies", (builder) -> builder
            .persistent(FirefliesComponent.CODEC)
            .networkSynchronized(FirefliesComponent.PACKET_CODEC));

    public static final DataComponentType<Butterfly.Variant> BUTTERFLY_VARIANT = register("butterfly/variant", (builder) -> builder
            .persistent(Butterfly.Variant.CODEC)
            .networkSynchronized(Butterfly.Variant.PACKET_CODEC));

    //#endregion

    private static <T> DataComponentType<T> register(final String name, final UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, IdentifierUtils.modded(name), builder.apply(DataComponentType.builder()).build());
    }

    public static void register() {

    }

}