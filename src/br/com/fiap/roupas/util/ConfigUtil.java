package br.com.fiap.roupas.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	public static String getProperty(String property) throws IOException {
		Properties prop = new Properties();
		prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("mq_config.properties"));
		return prop.getProperty(property);
	}
}
