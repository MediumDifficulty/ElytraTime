package yes.mediumdifficulty.elytratime;

import net.minecraft.item.ItemStack;

public class Util {
    public static String formatTimePercent(ItemStack item, String format, String timeFormat) {
        String timeLeft = formatTime(Calculator.timeRemaining(item), timeFormat);
        int percent = (int)(Calculator.fractionRemaining(item) * 100.0);

        return format
                .replaceAll("\\[TIME]", timeLeft)
                .replaceAll("\\[%]", String.valueOf(percent));
    }


    public static String formatTime(int time, String format) {
        return format
                .replaceAll("\\[M]", String.valueOf(time / 60))
                .replaceAll("\\[S]", String.valueOf(time % 60));
    }
}
