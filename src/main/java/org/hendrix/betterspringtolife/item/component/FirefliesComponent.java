package org.hendrix.betterspringtolife.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.block.FireflyJarBlock;

import java.util.function.Consumer;

public record FirefliesComponent(int fireflies) implements TooltipProvider {

    public static final Codec<FirefliesComponent> CODEC = Codec.INT.xmap(FirefliesComponent::new, FirefliesComponent::fireflies);
    public static final StreamCodec<ByteBuf, FirefliesComponent> PACKET_CODEC = ByteBufCodecs.INT.map(FirefliesComponent::new, FirefliesComponent::fireflies);
    public static final FirefliesComponent DEFAULT = new FirefliesComponent(1);

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(Component.translatable("tooltips." + BetterSpringToLife.MOD_ID + ".fireflies", this.fireflies, FireflyJarBlock.MAX_FIREFLIES).withStyle(ChatFormatting.GRAY));
    }
}
