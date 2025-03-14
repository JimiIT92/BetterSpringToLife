package org.hendrix.betterspringdrop.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link EntityModelLayer Entity Model Layers}
 */
@Environment(EnvType.CLIENT)
public final class BSDEntityModelLayers {

    //#region Entity Model Layers

    public static final EntityModelLayer BUTTERFLY = new EntityModelLayer(IdentifierUtils.modIdentifier("butterfly"), "main");

    //#endregion

}