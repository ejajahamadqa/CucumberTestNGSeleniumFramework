package pages;

import static java.lang.Boolean.valueOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Environment;

public class BasePage extends Environment {

    private static HashMap<String, List<String>> globalVars = new HashMap<>();

    public boolean click(By by) {
        WebElement webElement = waitForElementClickable(by);
        return click(webElement);
    }

    public boolean click(WebElement webElement) {
        waitForElementClickable(webElement);
        try {
            scrollTo(webElement);
            webElement.click();
            waitForjQueryDone();
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public boolean click(String css) {
        return click(By.cssSelector(css));
    }

    public boolean generalClick(By by) {
        waitForElementClickable(by);
        WebElement webElement = getDriver().findElement(by);
        webElement.click();
        return true;
    }

    public boolean generalClick(WebElement webElement) {
        waitForElementClickable(webElement);
        webElement.click();
        return true;
    }

    public boolean clickByXpath(String xpath) {
        return click(By.xpath(xpath));
    }

    public String executeJavascript(String javascript) {
        return String.valueOf(((JavascriptExecutor) getDriver()).executeScript(javascript));
    }

    public String getAttribute(By by, String attribute) {
        return waitForElement(by).getAttribute(attribute);
    }

    public String getAttribute(String cssSelector, String attribute) {
        return getAttribute(By.cssSelector(cssSelector), attribute);
    }

    public List<String> getAttributeList(By by, String attribute) {
        List<String> list = new ArrayList<>();
        for (WebElement e : waitForElements(by)) {
            String attr = e.getAttribute(attribute);
            if (attr != null) {
                list.add(attr.trim());
            }
        }
        return list;
    }

    public void switchToFrame(int value) {
        Environment.getDriver().switchTo().frame(value);
    }

    public void switchToDefaultContent() {
        Environment.getDriver().switchTo().defaultContent();
    }

    public boolean refreshBrowser() {
        boolean status = false;
        try {
            Environment.getDriver().navigate().refresh();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<WebElement> waitForElements(By by) {
        waitForjQueryDone();
        //return new WebDriverWait(getDriver(), FrameWorkConstants.EXPLICIT_WAIT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private WebElement waitForElement(By by) {
        waitForjQueryDone();
        //return new WebDriverWait(getDriver(), FrameWorkConstants.EXPLICIT_WAIT).until(ExpectedConditions.presenceOfElementLocated(by));
        return new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementClickable(WebElement webElement) {
        waitForjQueryDone();
        //return new WebDriverWait(getDriver(), FrameWorkConstants.EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(webElement));
        return new WebDriverWait(getDriver(), 10).until(ExpectedConditions.elementToBeClickable(webElement));
    }


    private WebElement waitForElementClickable(By by) {
        waitForjQueryDone();
        //return new WebDriverWait(getDriver(), FrameWorkConstants.EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(by));
        return new WebDriverWait(getDriver(), 10).until(ExpectedConditions.elementToBeClickable(by));
    }

    private boolean waitForjQueryDone() {
        boolean result = false;
        try {
            // waits for juery to stop animating & making ajax calls
            String jsScript = "if (typeof jQuery != 'undefined') {return ($.active == 0 && $(':animated').length == 0);} else {return true;};";
            long timeOut = System.currentTimeMillis() + (30 * 1000);
            do {
                result = valueOf(executeJavascript(jsScript));
                if (result) {
                    return result;
                } else {
                    wait(250);
                }
            } while (System.currentTimeMillis() < timeOut);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void wait(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void quitBrowser() {
        getDriver().quit();
    }

    public void luanchBrowser(String url) {
        getDriver().get(url);
    }

    private WebElement scrollTo(WebElement webElement) {
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        int x = location.getX() - (size.getWidth() / 2);
        int y = location.getY() - (size.getHeight() / 2);
        executeJavascript(String.format("window.scroll(%s, %s)", x, y));
        return webElement;
    }
}
