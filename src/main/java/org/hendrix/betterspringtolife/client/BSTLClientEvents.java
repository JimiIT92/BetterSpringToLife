package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.core.BSTLDataComponentTypes;

import java.util.List;

/**
 * {@link BetterSpringToLife Better Spring to Life} Client Events
 */
@Environment(EnvType.CLIENT)
public final class BSTLClientEvents {

    //#region Events

    /**
     * Append the modded Data Component Tooltips to Items
     *
     * @param itemStack The {@link ItemStack current Item Stack}
     * @param tooltipContext The {@link Item.TooltipContext Item Tooltip Context}
     * @param tooltipType The {@link TooltipType Tooltip Type}
     * @param tooltips The {@link List<Text> current Tooltips}
     */
    private static void appendComponentTooltips(final ItemStack itemStack, final Item.TooltipContext tooltipContext, final TooltipType tooltipType, final List<Text> tooltips) {
        final TooltipDisplayComponent tooltipDisplayComponent = itemStack.getOrDefault(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplayComponent.DEFAULT);
        itemStack.appendComponentTooltip(BSTLDataComponentTypes.FIREFLIES, tooltipContext, tooltipDisplayComponent, tooltips::add, tooltipType);
    }

    //#endregion

    /**
     * Register all Events
     */
    public static void register() {
        ItemTooltipCallback.EVENT.register(BSTLClientEvents::appendComponentTooltips);
    }

}