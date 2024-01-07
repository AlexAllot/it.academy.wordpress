package data.constants;

import lombok.Getter;

@Getter
public enum ApplicationIdentifier {

    WORDPRESS("Wordpress", "wordpress.properties");

    @Getter
    private final String appName;
    private final String propertiesPath;

    ApplicationIdentifier(String appName, String propertiesPath){
        this.appName = appName;
        this.propertiesPath = propertiesPath;
    }
}
