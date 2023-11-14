package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZeetchHomeScreen extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='btn_make_attendance']")
    WebElement markAsPresent;

    @FindBy(xpath = "//a[@href='MyProject.aspx']")
    WebElement myProjectLink;

    public ZeetchHomeScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void markAsPresent() {
        try {
            if (markAsPresent.isDisplayed()) {
                markAsPresent.click();
                Thread.sleep(5000);
                driver.switchTo().alert().accept();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getStackTrace();
        }
    }

    public void waitForMyProjectLink() throws InterruptedException {
        Thread.sleep(5000);
        waitForElementClickable(myProjectLink);
    }

    public void clickMyProjectLink() {
        //waitForElementClickable(myProjectLink);
        myProjectLink.click();
        //Thread.sleep(50000);
    }

}
