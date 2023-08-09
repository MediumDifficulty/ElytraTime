package yes.mediumdifficulty.elytratime.mixin.client;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import yes.mediumdifficulty.elytratime.ClientTextUtils;
import yes.mediumdifficulty.elytratime.ElytraTime;
import yes.mediumdifficulty.elytratime.Util;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", shift = At.Shift.AFTER, ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void getTooltipInject(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, List list) {
        if (this.getItem() instanceof ElytraItem && ElytraTime.config.tooltipEnabled)
            list.add(Text.literal(Util.formatTimePercent((ItemStack)(Object)this, ClientTextUtils.getTooltipFormat(), ClientTextUtils.getTimeFormat())).formatted(Formatting.GREEN));
    }
}
