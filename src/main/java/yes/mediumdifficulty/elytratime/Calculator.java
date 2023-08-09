package yes.mediumdifficulty.elytratime;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public class Calculator {
    public static int timeRemaining(ItemStack item) {
        int unbreaking = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, item);
        return ((item.getMaxDamage() - item.getDamage()) * (unbreaking + 1)) - 1;
    }

    public static float fractionRemaining(ItemStack item) {
        int unbreaking = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, item);
        int timeRemaining = timeRemaining(item);
        int totalTime = (item.getMaxDamage() * (unbreaking + 1)) - 1;

        return (float)timeRemaining / (float)totalTime;
    }
}
