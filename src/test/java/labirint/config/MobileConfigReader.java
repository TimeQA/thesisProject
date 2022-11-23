package labirint.config;


import org.aeonbits.owner.ConfigFactory;

public enum MobileConfigReader {
    Instance;

    private static final MobileConfig MOBILE_CONFIG =
            ConfigFactory.create(
                    MobileConfig.class,
                    System.getProperties()
            );

    public MobileConfig read() {
        return MOBILE_CONFIG;
    }
}