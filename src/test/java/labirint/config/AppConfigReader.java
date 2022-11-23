package labirint.config;

import org.aeonbits.owner.ConfigFactory;

public enum AppConfigReader {
    Instance;

    private static final AppConfig APP_CONFIG =
            ConfigFactory.create(
                    AppConfig.class,
                    System.getProperties()
            );

    public AppConfig read() {
        return APP_CONFIG;
    }
}