package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link EntityModelLayer Entity Model Layers}
 */
@Environment(EnvType.CLIENT)
public final class BSTLEntityModelLayers {

    //#region Entity Model Layers

    public static final EntityModelLayer BUTTERFLY = new EntityModelLayer(IdentifierUtils.modIdentifier("butterfly"), "main");

    //#endregion

}