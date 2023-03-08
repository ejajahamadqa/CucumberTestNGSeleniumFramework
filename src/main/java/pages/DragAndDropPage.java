package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DragAndDropPage {

    WebDriver driver;

    @FindBy(xpath = "//a[text()='Droppable']")
    WebElement droppable;

    @FindBy(id = "draggable")
    WebElement source;

    @FindBy(id = "droppable")
    WebElement destination;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyTitleOfPage(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    public void waitForDroppablePage() {
      //  waitForWebElementToBeClickable(droppable);
    }

    public void switchToFrame() {
        driver.switchTo().frame(0);
    }

    public void clickOnDroppableElement() {
        droppable.click();
    }

    public void dragAndDrop() {
       // dragAndDrop(source,destination);
    }

    public void verifyDropped() {
        String textTo = destination.getText();

        if (textTo.equals("Dropped!")) {
            System.out.println("PASS: Source is dropped to target as expected");
        } else {
            System.out.println("FAIL: Source couldn't be dropped to target as expected");
        }
    }

    public void switchToDefault() {
       // switchToDefaultContent();
    }
}
