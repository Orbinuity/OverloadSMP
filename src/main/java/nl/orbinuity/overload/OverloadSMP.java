package nl.orbinuity.overload;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import nl.orbinuity.overload.config.OverloadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class OverloadSMP implements ModInitializer {
	public static final String MODID = "overload";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to the Overload SMP!");

		OverloadConfig.load();

		ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> !message.getString().equals("Hello from FTB Library!"));
	}

	public static boolean useNight() {
		boolean useNight;

		if (OverloadConfig.autoMode) {
			int hour = LocalTime.now().getHour();
			useNight = hour >= 18 || hour < 6;
		} else {
			useNight = OverloadConfig.nightMode;
		}

		return useNight;
	}
}