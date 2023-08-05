package yes.mediumdifficulty.elytratime;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Calculator {
    public static int timeLeft(ItemStack item) {
        int unbreaking = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, item);
        return ((item.getMaxDamage() - item.getDamage()) * (unbreaking + 1)) - 1;
    }

    public static Text getCalculatedString(ItemStack item) {
        return
                Text.translatable("text.elytratime.remaining", formatTime(timeLeft(item)))
                .formatted(Formatting.GREEN);
    }

    public static String formatTime(int seconds) {
        return String.format("%sm %ss", seconds / 60, seconds % 60);
    }
}
