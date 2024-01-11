package utils;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


public class AllureEnvironmentUtils {

    private static final Properties props;

    private AllureEnvironmentUtils() { }

    static {
        props = new Properties();
    }

    public static void createAllureEnvironmentFile(Map<String, String> propsConfig, String pathToAllureResultsDir) {
        propsConfig.forEach(props::setProperty);

        try(FileOutputStream fos = new FileOutputStream(new File("target/allure-results/environment.properties"))) {
            props.store(fos, "Environment properties data for Allure report");
            System.out.println("Allure environment.properties file has been created and stored successfully.");
        } catch (IOException e) {
            System.out.println("Allure environment.properties file was not created. \n");
            e.printStackTrace();
        }
    }

    public static void createAllureEnvironmentFile(Map<String, String> propsConfig) {
        createAllureEnvironmentFile(propsConfig, System.getProperty("user.dir") + "/target/allure-results/environment.properties");
    }

}
