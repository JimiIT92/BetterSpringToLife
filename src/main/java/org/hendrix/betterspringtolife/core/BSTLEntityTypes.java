package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.hendrix.betterspringtolife.entity.Butterfly;
import org.hendrix.betterspringtolife.entity.Moobloom;
import org.hendrix.betterspringtolife.entity.MuddyPig;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public class BSTLEntityTypes {

    //#region Entity Types

    public static final EntityType<Moobloom> MOOBLOOM = register(
            "moobloom",
            EntityType.Builder.of(Moobloom::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10)
    );

    public static final EntityType<MuddyPig> MUDDY_PIG = register(
            "muddy_pig",
            EntityType.Builder.of(MuddyPig::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.9F)
                    .passengerAttachments(0.86875F)
                    .clientTrackingRange(10)
    );

    public static final EntityType<Butterfly> BUTTERFLY = register(
            "butterfly",
            EntityType.Builder.of(Butterfly::new, MobCategory.CREATURE)
                    .sized(0.3F, 0.25F)
                    .eyeHeight(0.19F)
                    .clientTrackingRange(8)
    );

    //#endregion

    /**
     * Register an entity
     *
     * @param name The entity name
     * @param builder The entity builder
     * @return The registered entity
     * @param <T> The entity type
     */
    private static <T extends Entity> EntityType<T> register(final String name, final EntityType.Builder<T> builder) {
        final ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, IdentifierUtils.modded(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    /**
     * Register all entities
     */
    public static void register() {
        FabricDefaultAttributeRegistry.register(MOOBLOOM, Moobloom.createAttributes());
        FabricDefaultAttributeRegistry.register(MUDDY_PIG, MuddyPig.createAttributes());
        FabricDefaultAttributeRegistry.register(BUTTERFLY, Butterfly.createAttributes());
    }

}
