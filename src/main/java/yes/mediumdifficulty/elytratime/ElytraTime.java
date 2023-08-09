package yes.mediumdifficulty.elytratime;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElytraTime implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ElytraTime");

	public static Config config = Config.fromFileOrDefault();

	@Override
	public void onInitialize() {
		LOGGER.info("Initialised");
	}
}