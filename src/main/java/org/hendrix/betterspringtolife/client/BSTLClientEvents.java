package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.hendrix.betterspringtolife.core.BSTLDataComponentTypes;

import java.util.List;

@Environment(EnvType.CLIENT)
public class BSTLClientEvents {

    //#region Events

    private static void appendComponentTooltips(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> components) {
        final TooltipDisplay tooltipDisplayComponent = itemStack.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        itemStack.addToTooltip(BSTLDataComponentTypes.FIREFLIES, tooltipContext, tooltipDisplayComponent, components::add, tooltipFlag);
    }

    //#endregion

    public static void register() {
        ItemTooltipCallback.EVENT.register(BSTLClientEvents::appendComponentTooltips);
    }
}