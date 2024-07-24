package yes.mediumdifficulty.elytratime;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class Calculator {
    public static int timeRemaining(ItemStack item, World world) {
        int unbreaking = getUnbreakingLevel(item, world);
        return ((item.getMaxDamage() - item.getDamage()) * (unbreaking + 1)) - 1;
    }

    public static float fractionRemaining(ItemStack item, World world) {
        int unbreaking = getUnbreakingLevel(item, world);
        int timeRemaining = timeRemaining(item, world);
        int totalTime = (item.getMaxDamage() * (unbreaking + 1)) - 1;

        return (float)timeRemaining / (float)totalTime;
    }

    private static int getUnbreakingLevel(ItemStack item, World world) {
        // Worst thing about the 1.21 update tbh
        RegistryEntry<Enchantment> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.UNBREAKING).orElseThrow();
        return EnchantmentHelper.getLevel(entry, item);
    }
}
