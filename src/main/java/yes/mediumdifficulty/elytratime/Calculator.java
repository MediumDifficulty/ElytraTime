package yes.mediumdifficulty.elytratime;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class Calculator {
    public static Text getCalculatedString(ItemStack item) {
        int unbreaking = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, item);

        return Text.literal(
                String.format("%s remaining", formatTime((item.getMaxDamage() - item.getDamage()) * (unbreaking + 1)))
        );
    }

    private static String formatTime(int seconds) {
        return String.format("%sm %ss", seconds / 60, seconds % 60);
    }
}
