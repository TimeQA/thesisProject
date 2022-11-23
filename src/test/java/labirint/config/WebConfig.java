package labirint.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${platform}/${env}.properties"
})
public interface WebConfig extends Config {

    @Key("browserName")
    String browserName();

    @Key("browserSize")
    String browserSize();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("videoStorage")
    String videoStorage();

    @Key("baseUrl")
    String baseUrl();

    @Key("isRemote")
    Boolean isRemote();
}