package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.awt.AWTException;
import pages.ZeetechLogin;

public class ZeetecthLoginStep {

    ZeetechLogin zeetechLogin = new ZeetechLogin(Environment.getDriver());

    @Given("Enter UserName Password and Click on login Button")
    public void select_option_droppable_from_left_menu_under_interactions() {

        zeetechLogin.sendUserName();
        zeetechLogin.sendPassword();
        zeetechLogin.acceptCookies();
    }

    @When("User clicks on Login Button")
    public void user_clicks_on_login_button() throws InterruptedException, AWTException {
        zeetechLogin.clickLoginButton();
        /*
        zeetechLogin.waitAndClickViewDataFile();

        zeetechLogin.viewDataFile();

        Thread.sleep(1000);
        zeetechLogin.downloadIconClick();
        Thread.sleep(1000);

        List<String> excelData = ExcelDataReader.readExcelData();

        String applicationType = getApplicationType(excelData,"New Licence","Renewal");
        zeetechLogin.dlApplicationType(applicationType);

        String licenseNo = getLicenseNo(excelData);
        zeetechLogin.txtLicenseNo(licenseNo);

        String firmType = getFirmType(excelData,"Proprietary", "Partnership","NGO", "OPC Pvt. Ltd.",
            "Pvt Ltd", "Public Limited","Others");
        zeetechLogin.dlFirmType(firmType);

        String typeOwnership = getTypeOfOwnership(excelData,"On Rent", "Lease","Own Property");
        zeetechLogin.dlTypeOfOwnership(typeOwnership);

        String applicantName = getApplicantName(excelData);
        zeetechLogin.txtApplicantName(applicantName);

        String applicantFatherName = getApplicantFathersName(excelData);
        applicantFatherName = applicantFatherName.replace("  "," ").trim();
        zeetechLogin.txtFatherName(applicantFatherName);

        String mobileNumber = getMobileNumber(excelData);
        zeetechLogin.txtMobile(mobileNumber);

        String applicantAddress = getApplicantAddress(excelData);
        applicantAddress = applicantAddress.replace("  ", " ");
        zeetechLogin.txtApplicantAddress(applicantAddress.trim());

        String businessName = getBusinessName(excelData);
        zeetechLogin.txtBusinessName(businessName);

        String natureOfBusiness = getNatureOfBusiness(excelData);
        natureOfBusiness = natureOfBusiness.replace("  ", " ").replace("Premises. Firm","");
        zeetechLogin.txtNatureOfBusiness(natureOfBusiness.trim());

        String establishmentDate = getEstablishmentDate(excelData);
        zeetechLogin.txtEstablishmentDate(establishmentDate);

        String businessAddress = getBusinessAddress(excelData);
        businessAddress = businessAddress.replace("  ", " ");
        zeetechLogin.txtBusinessAddress(businessAddress.trim());

        String totalArea = getTotalArea(excelData);
        zeetechLogin.dlTotalArea(totalArea);

        //Data has been submitted successfully.
        Thread.sleep(50000);*/
    }

}
