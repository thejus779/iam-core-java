package fr.epita.iamcore.services.test;


import fr.epita.iamcore.logger.Logger_Core;
import fr.epita.logger.test.LoggerTest;

public class TestLogger {
	private static final Logger_Core LOGGER = new Logger_Core(LoggerTest.class);

	public static void main(String[] args) {
		LOGGER.info("info test from logger");
		
	}
}
