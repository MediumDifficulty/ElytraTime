package yes.mediumdifficulty.elytratime;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigMenu {
    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.elytratime.config"))
                .setSavingRunnable(() -> ElytraTime.config.saveToFile());

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.elytratime.general"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        BooleanListEntry enableTooltipEntry = builder.entryBuilder().startBooleanToggle(Text.translatable("option.elytratime.enable_tooltip"), ElytraTime.config.tooltipEnabled)
                .setSaveConsumer(v -> ElytraTime.config.tooltipEnabled = v)
                .setTooltip(Text.translatable("tooltip.elytratime.enabled"))
                .setDefaultValue(true)
                .build();
        general.addEntry(enableTooltipEntry);

        general.addEntry(entryBuilder.startStrField(Text.translatable("option.elytratime.tooltip_format"), ClientTextUtils.getTooltipFormat())
                .setSaveConsumer(v -> ElytraTime.config.tooltipFormat = v)
                .setTooltip(Text.translatable("tooltip.elytratime.tooltip_format"))
                .setDefaultValue(ClientTextUtils.getValueFromKey("value.elytratime.tooltip_format"))
                .setRequirement(Requirement.isTrue(enableTooltipEntry))
                .build());

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.elytratime.alert_threshold"), ElytraTime.config.alertThreshold, 0, 100)
                .setSaveConsumer(v -> ElytraTime.config.alertThreshold = v)
                .setTooltip(Text.translatable("tooltip.elytratime.alert_threshold"))
                .setDefaultValue(10)
                .build());

        general.addEntry(entryBuilder.startStrField(Text.translatable("option.elytratime.time_format"), ClientTextUtils.getTimeFormat())
                .setSaveConsumer(v -> ElytraTime.config.timeFormat = v)
                .setTooltip(Text.translatable("tooltip.elytratime.time_format"))
                .setDefaultValue(ClientTextUtils.getValueFromKey("value.elytratime.time_format"))
                .build());

        general.addEntry(entryBuilder.startStrField(Text.translatable("option.elytratime.time_report_format"), ClientTextUtils.getTimeReportFormat())
                .setSaveConsumer(v -> ElytraTime.config.timeReportFormat = v)
                .setTooltip(Text.translatable("tooltip.elytratime.time_report_format"))
                .setDefaultValue(ClientTextUtils.getValueFromKey("value.elytratime.time_report_format"))
                .build());

        return builder.build();
    }
}
