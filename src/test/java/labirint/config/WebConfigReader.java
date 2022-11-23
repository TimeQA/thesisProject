package labirint.config;

import org.aeonbits.owner.ConfigFactory;

public enum WebConfigReader {
    Instance;

    private static final WebConfig WEB_CONFIG =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );

    public WebConfig read() {
        return WEB_CONFIG;
    }
}
