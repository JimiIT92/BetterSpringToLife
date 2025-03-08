package org.hendrix.betterspringdrop.component.type;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.hendrix.betterspringdrop.block.FireflyJarBlock;

import java.util.function.Consumer;

/**
 * Record class for the Fireflies Data Component
 *
 * @param fireflies The {@link Integer Fireflies amount}
 */
public record FirefliesComponent(int fireflies) implements TooltipAppender {

    /**
     * The {@link Codec<FirefliesComponent> Fireflies Component Codec}
     */
    public static final Codec<FirefliesComponent> CODEC = Codec.INT.xmap(FirefliesComponent::new, FirefliesComponent::fireflies);
    /**
     * The {@link PacketCodec Fireflies Component Packet Codec}
     */
    public static final PacketCodec<ByteBuf, FirefliesComponent> PACKET_CODEC = PacketCodecs.INTEGER.xmap(FirefliesComponent::new, FirefliesComponent::fireflies);
    /**
     * The {@link FirefliesComponent Default Fireflies Component value}
     */
    public static final FirefliesComponent DEFAULT = new FirefliesComponent(1);

    /**
     * Show the amount of Fireflies in tooltip
     *
     * @param context The {@link Item.TooltipContext Item Tooltip Context}
     * @param textConsumer The {@link Consumer<Text> Text Consumer}
     * @param type The {@link TooltipType Tooltip Type}
     * @param components The {@link ComponentsAccess Components Access}
     */
    public void appendTooltip(final Item.TooltipContext context, final Consumer<Text> textConsumer, final TooltipType type, final ComponentsAccess components) {
        textConsumer.accept(Text.translatable("container.firefly_jar.fireflies", this.fireflies, FireflyJarBlock.MAX_FIREFLIES).formatted(Formatting.GRAY));
    }

}