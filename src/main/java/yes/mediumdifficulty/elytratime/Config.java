package yes.mediumdifficulty.elytratime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public static class Alert {
        public String message;
        int time;

        public Alert(int Time) {
        }
    }

    public static final String CONFIG_FILE = "elytratime.json";

    public boolean tooltipEnabled = true;
    public int alertThreshold = 10;
    public String tooltipFormat = "";
    public String timeFormat = "";
    public String timeReportFormat = "";

    public void saveToFile() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String serialised = gson.toJson(this);

        File file = new File(FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE).toUri());

        try {
            if (file.createNewFile())
                ElytraTime.LOGGER.info("Created new config file");

            FileWriter writer = new FileWriter(file, false);
            writer.write(serialised);
            writer.close();
            ElytraTime.LOGGER.info("Saved config");
        } catch (Exception e) {
            ElytraTime.LOGGER.error("Error occurred while saving config");
        }
    }

    public static Config fromFileOrDefault() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE);

        if (new File(configPath.toUri()).exists()) {
            Gson gson = new GsonBuilder().create();
            try {
                String configString = new String(Files.readAllBytes(configPath));

                return gson.fromJson(configString, Config.class);
            } catch (IOException e) {
                ElytraTime.LOGGER.error("Error occurred while loading config");
            }
        }

        return new Config();
    }
}
