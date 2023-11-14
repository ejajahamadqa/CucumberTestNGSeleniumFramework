package pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import utilities.DeleteFiles;
import utilities.PdfToExcelConverter;


public class ZeetechLogin extends BasePage {

    Select select;

    WebDriver driver;

    @FindBy(xpath = "//input[@id='txt_user']")
    WebElement userName;

    @FindBy(xpath = "//input[@id='txt_pass']")
    WebElement password;

    @FindBy(xpath = "//input[@id='btn_log']")
    WebElement loginButton;
    @FindBy(xpath = "//input[@id='chk_accept_cookie_policy']")
    WebElement acceptCookies;

    @FindBy(id = "grdv_project_link_view_file_0")
    WebElement viewDataFile;

    @FindBy(xpath = "//a[text()='View File']")
    List<WebElement> viewFile;

    @FindBy(xpath = "//select[@id='dl_application_type']")
    WebElement dlApplicationType;

    @FindBy(xpath = "//input[@id='txt_license_no']")
    WebElement txtLicenseNo;

    @FindBy(xpath = "//select[@id='dl_firm_type']")
    WebElement dlFirmType;

    @FindBy(xpath = "//select[@id='dl_type_of_ownership']")
    WebElement dlTypeOfOwnership;

    @FindBy(xpath = "//input[@id='txt_applicant_name']")
    WebElement txtApplicantName;

    @FindBy(xpath = "//input[@id='txt_father_name']")
    WebElement txtFatherName;

    @FindBy(xpath = "//input[@id='txt_mobile']")
    WebElement txtMobile;

    @FindBy(xpath = "//textarea[@id='txt_applicant_address']")
    WebElement txtApplicantAddress;

    @FindBy(xpath = "//input[@id='txt_business_name']")
    WebElement txtBusinessName;

    @FindBy(xpath = "//input[@id='txt_nature_of_business']")
    WebElement txtNatureOfBusiness;

    @FindBy(xpath = "//input[@id='txt_establishment_date']")
    WebElement txtEstablishmentDate;

    @FindBy(xpath = "//textarea[@id='txt_business_address']")
    WebElement txtBusinessAddress;

    @FindBy(xpath = "//select[@id='dl_total_area']")
    WebElement dlTotalArea;

    @FindBy(xpath = "//input[@id='btn_save_bottom']")
    WebElement btnSaveBottom;

    @FindBy(xpath = "//a[contains(@href,'/Candidate/ReadPDFStream')]")
    WebElement pdfLinktoDownload;

    @FindBy(id = "div_pdf_box")
    WebElement pdfPath;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='picker-switch']")
    WebElement getMonthAndYear;

    @FindBy(xpath = "//div[@class='datepicker-months']//th[@class='picker-switch']")
    WebElement clickOnYear;

    @FindBy(xpath = "//div[@class='datepicker-years']//th[@data-action='previous']")
    WebElement getYearPrev;

    @FindBy(xpath = "//div[@class='datepicker-years']//th[@data-action='next']")
    WebElement getYearNext;

    @FindBy(xpath = "//div[@class='datepicker-months']//th[@data-action='previous']")
    WebElement getMonth;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@data-action='previous']")
    WebElement getDays;

    String yearToBeSelected = "//span[text()='replaceText']";


    public ZeetechLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendUserName() {
        click(userName);
        userName.sendKeys("8792936416");
    }

    public void sendPassword() {
        password.sendKeys("Fangadi@89");
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void acceptCookies() {
        acceptCookies.click();
    }

    public void waitAndClickViewDataFile() {
        waitForElementClickable(viewDataFile);
        viewDataFile.click();
    }

    public void viewDataFile() throws InterruptedException {
        waitForElementClickable(viewFile.get(0));
        viewFile.get(0).click();
        /*for(int i=0; i<viewFile.size(); i++) {
            viewFile.get(i).click();
            Thread.sleep(10000);
        }*/
    }

    public void dlApplicationType(String applicationType) {
        waitForElementClickable(dlApplicationType);
        dlApplicationType.click();
        select = new Select(dlApplicationType);
        List<WebElement> applicationTypeList = select.getOptions();
        for (int i = 0; i < applicationTypeList.size(); i++) {
            if (applicationTypeList.get(i).getText().equals(applicationType)) {
                select.selectByValue(applicationType);
            }
        }
    }

    public void txtLicenseNo(String licenseNo) {
        waitForElementClickable(txtLicenseNo);
        txtLicenseNo.clear();
        txtLicenseNo.sendKeys(licenseNo);
    }

    public void dlFirmType(String firmType) {
        waitForElementClickable(dlFirmType);
        dlFirmType.click();
        select = new Select(dlFirmType);
        List<WebElement> firmList = select.getOptions();
        for (int i = 0; i < firmList.size(); i++) {
            if (firmList.get(i).getText().equals(firmType)) {
                select.selectByValue(firmType);
            }
        }
    }

    public void dlTypeOfOwnership(String typeOfOwner) {
        waitForElementClickable(dlTypeOfOwnership);
        dlTypeOfOwnership.click();
        select = new Select(dlTypeOfOwnership);
        select.selectByValue(typeOfOwner);
    }

    public void txtApplicantName(String applicantName) {
        waitForElementClickable(txtApplicantName);
        txtApplicantName.clear();
        txtApplicantName.sendKeys(applicantName);
    }

    public void txtFatherName(String fatherName) {
        waitForElementClickable(txtFatherName);
        txtFatherName.clear();
        txtFatherName.sendKeys(fatherName);
    }

    public void txtMobile(String mobile) {
        waitForElementClickable(txtMobile);
        if (mobile.contains("X") || mobile.contains("x")) {
            System.out.println("Skip the file");
        } else if (mobile.length() != 10) {
            System.out.println("Skip the file");
        } else {
            txtMobile.clear();
            txtMobile.sendKeys(mobile);
        }
    }

    public void txtApplicantAddress(String applicantAddress) {
        waitForElementClickable(txtApplicantAddress);
        txtApplicantAddress.clear();
        txtApplicantAddress.sendKeys(applicantAddress);
    }

    public void txtBusinessName(String businessName) {
        waitForElementClickable(txtBusinessName);
        txtBusinessName.clear();
        txtBusinessName.sendKeys(businessName);
    }

    public void txtNatureOfBusiness(String natureOfBusiness) {
        waitForElementClickable(txtNatureOfBusiness);
        txtNatureOfBusiness.clear();
        txtNatureOfBusiness.sendKeys(natureOfBusiness);
    }

    public void txtEstablishmentDate(String establishmentData) {
        waitForElementClickable(txtEstablishmentDate);
        //txtEstablishmentDate.click();
        //Thread.sleep(3000);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1];", driver.findElement(By.id("txt_establishment_date")), establishmentData);
    }

    private String getActualDateFormatted(String establishmentData) {
        String[] splittedDate = establishmentData.split("-");
        int month = Integer.parseInt(splittedDate[1]);
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        map.put(4, "April");
        map.put(5, "May");
        map.put(6, "June");
        map.put(7, "July");
        map.put(8, "August");
        map.put(9, "September");
        map.put(10, "October");
        map.put(11, "November");
        map.put(12, "December");

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        // Create an iterator to iterate over the entry set
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        String returnMonth = "";
        // Iterate through the entries
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            if (key == month) {
                returnMonth = entry.getValue();
                break;
            }
            // System.out.println("Key: " + key + ", Value: " + value);
        }
        return returnMonth + " " + splittedDate[2];
    }

    public void txtBusinessAddress(String businessAddress) {
        waitForElementClickable(txtBusinessAddress);
        txtBusinessAddress.clear();
        txtBusinessAddress.sendKeys(businessAddress);
    }

    public void dlTotalArea(String totalArea) {

        waitForElementClickable(dlFirmType);
        dlFirmType.click();
        select = new Select(dlTotalArea);
        List<WebElement> totalAreaList = select.getOptions();
        for (int i = 0; i < totalAreaList.size(); i++) {
            totalAreaList.get(i).getText();
            if (totalAreaList.get(i).getText().equals(totalArea)) {
                select.selectByValue(totalArea);
            }
        }
    }

    public void downloadPDF() throws InterruptedException, AWTException {
        DeleteFiles.deletePDFFile();
        DeleteFiles.deleteExcelFile();

        waitForElementClickable(driver.findElement(By.id("div_pdf_box")));
        WebElement element = driver.findElement(By.id("div_pdf_box"));
        Robot robot = new Robot();
        robot.mouseMove(element.getLocation().getX() + 300, element.getLocation().getY() + 300);
        Thread.sleep(1000);
        robot.mousePress(InputEvent.BUTTON3_MASK);  // Right-click
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        Thread.sleep(1000);
        // Use keyboard shortcuts to save the element
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        robot.mouseMove(element.getLocation().getX() + 400, element.getLocation().getY() + 400);
        Thread.sleep(1000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);  // Right-click
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

        PdfToExcelConverter.pdfToExcelConverter();
    }

    public void btnSaveBottom() {
        waitForElementClickable(btnSaveBottom);
        btnSaveBottom.click();
    }
}
