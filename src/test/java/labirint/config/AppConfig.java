package labirint.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {

    @Key("cookieName")
    String cookieName();

    @Key("cookieValue")
    String cookieValue();

    @Key("data[login]")
    String login();
}