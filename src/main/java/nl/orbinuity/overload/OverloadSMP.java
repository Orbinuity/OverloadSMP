package nl.orbinuity.overload;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverloadSMP implements ModInitializer {
	public static final String MODID = "overload";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to the Overload SMP!");
	}
}