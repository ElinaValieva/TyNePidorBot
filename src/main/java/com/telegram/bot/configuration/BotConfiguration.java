package com.telegram.bot.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * author: ElinaValieva on 02.02.2019
 */
public enum BotConfiguration {

    BOT_CONFIGURATION_TOKEN("bot.token"),
    BOT_CONFIGURATION_NAME("bot.name");

    BotConfiguration(String configs) {
        properties = new Properties();
        try {
            properties.load(BotConfiguration.class.getResourceAsStream("/bot.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.configs = configs;
    }

    private String configs;
    private Properties properties;

    public String getConfigs() {
        return properties.getProperty(configs);
    }
}
