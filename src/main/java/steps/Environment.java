package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Environment {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    protected void initialization() {
        String chromeDriverPath = Thread.currentThread().getContextClassLoader().getResource("chromedriver").getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        setDriver(new ChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("environment." + Environment.prop.getProperty("environment") + "-base-url"));

    }

    private static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static Properties prop = new Properties();

    static {
        loadEnvironmentProperties();
    }

    private static void loadEnvironmentProperties() {
        setEnvironment();
        prop.putAll(getProperties("config/common.properties"));
        prop.putAll(getProperties("config/" + prop.getProperty("environment") + "properties"));
        overrideSystemProperties();
    }

    public static String getEnv() {
        return prop.getProperty("environment");
    }

    private static void setEnvironment() {
        String env = System.getProperty("environment") != null ? System.getProperty(
                "environment") : getEnv() != null ? getEnv() : "qa";
        prop.put("environment", env);
    }

    private static void overrideSystemProperties() {
        Set<Object> sysPropKeys = System.getProperties().keySet();
        Set<Object> localPropKeys = prop.keySet();
        for (Object localKey : localPropKeys) {
            sysPropKeys.forEach((sysKey) -> {
                if (localKey.equals(sysKey)) {
                    prop.put(localKey, System.getProperty(sysKey.toString()));
                }
            });
        }
    }

     /*
     * helper method to read properties from a file.
     * Params: filename - name of the file to be read.
     * Returns: properties.
     */
    public static Properties getProperties(String filename) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Properties p = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(filename)) {
            p.load(resourceStream);
        } catch (Exception e) {
            System.out.println("Unable to open [" + filename + "]");
        }
        return p;
    }
}
