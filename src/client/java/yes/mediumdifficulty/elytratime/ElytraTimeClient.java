package yes.mediumdifficulty.elytratime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ElytraTimeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerKeybindings();

        ElytraTime.LOGGER.info("Initialised on client");
    }

    private void registerKeybindings() {
        KeyBinding printTime = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.elytratime.show",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F4,
                "category.elytratime.controls"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (printTime.wasPressed() && client.player != null) {
                ItemStack chestPlate = client.player.getInventory().getArmorStack(EquipmentSlot.CHEST.getEntitySlotId());

                if (chestPlate.getItem() instanceof ElytraItem) {
                    client.player.sendMessage(Text.translatable("message.elytratime.time_report", Calculator.formatTime(Calculator.timeLeft(chestPlate)))
                            .formatted(Formatting.GREEN));
                } else {
                    client.player.sendMessage(Text.translatable("message.elytratime.no_elytra").formatted(Formatting.RED));
                }
            }
        });
    }

}
