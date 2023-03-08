package pages;

import steps.Environment;

public class BasePage extends Environment {

    public void quitBrowser() {
        getDriver().quit();
    }

    public void luanchBrowser(String url) {
        getDriver().get(url);
    }
}
