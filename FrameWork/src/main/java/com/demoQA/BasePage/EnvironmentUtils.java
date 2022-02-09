package com.demoQA.BasePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class EnvironmentUtils {

	static String configPath = System.getProperty("user.dir") + "/Configuration/config.properties";

	public static Properties getConfig() throws IOException {

		Properties config = null;
		try {
			config = new Properties();
			FileInputStream fi = new FileInputStream(configPath);
			config.load(fi);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return config;
	}
}
