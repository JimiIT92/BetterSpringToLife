package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.AbstractCowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.entity.ButterflyEntity;
import org.hendrix.betterspringtolife.entity.MoobloomEntity;
import org.hendrix.betterspringtolife.entity.MuddyPigEntity;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link EntityType Entity Types}
 */
public final class BSTLEntities {

    //#region Entity Types

    public static final EntityType<MoobloomEntity> MOOBLOOM = registerEntity(
            "moobloom",
            EntityType.Builder.create(MoobloomEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .maxTrackingRange(10)
    );

    public static final EntityType<MuddyPigEntity> MUDDY_PIG = registerEntity(
            "muddy_pig",
            EntityType.Builder.create(MuddyPigEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 0.9F)
                    .passengerAttachments(0.86875F)
                    .maxTrackingRange(10)
    );

    public static final EntityType<ButterflyEntity> BUTTERFLY = registerEntity(
            "butterfly",
            EntityType.Builder.create(ButterflyEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.3F, 0.25F)
                    .dropsNothing()
                    .eyeHeight(0.19F)
                    .maxTrackingRange(8)
    );

    //#endregion

    /**
     * Register an {@link Entity entity}
     *
     * @param name The {@link String Entity name}
     * @param entityBuilder The {@link EntityType.Builder Entity Builder instance}
     * @return The {@link EntityType registered Entity Type}
     * @param <T> The {@link T Entity Type class}
     */
    private static <T extends Entity> EntityType<T> registerEntity(final String name, final EntityType.Builder<T> entityBuilder) {
        final Identifier identifier = IdentifierUtils.modIdentifier(name);
        return Registry.register(Registries.ENTITY_TYPE, identifier, entityBuilder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier)));
    }

    /**
     * Register all {@link EntityType Entities}
     */
    public static void register() {
        FabricDefaultAttributeRegistry.register(MOOBLOOM, AbstractCowEntity.createCowAttributes());
        FabricDefaultAttributeRegistry.register(MUDDY_PIG, PigEntity.createPigAttributes());
        FabricDefaultAttributeRegistry.register(BUTTERFLY, ButterflyEntity.createButterflyAttributes());
    }

}