package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import pages.ZeetchHomeScreen;
import pages.ZeetechLogin;
import utilities.ExcelDataReader;

public class ZeetechHomeScreenStep {

    ZeetechLogin zeetechLogin = new ZeetechLogin(Environment.getDriver());
    ZeetchHomeScreen zeetechHomeScreen = new ZeetchHomeScreen(Environment.getDriver());

    @Given("Wait for welcome screen to appear")
    public void wait_for_welcome_screen_to_appear() throws InterruptedException {
        zeetechHomeScreen.waitForMyProjectLink();
    }

    @When("User clicks on markAsPresent link")
    public void user_clicks_on_mark_as_present_link() {
        zeetechHomeScreen.markAsPresent();
    }

    @Then("User clicks on My Project Link")
    public void user_clicks_on_my_project_link() throws InterruptedException, AWTException {
        zeetechHomeScreen.clickMyProjectLink();

        zeetechLogin.waitAndClickViewDataFile();

        zeetechLogin.viewDataFile();

        //Thread.sleep(500);
        zeetechLogin.downloadPDF();
        //Thread.sleep(500);

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
        Thread.sleep(50000);

    }

    private String getBusinessAddress(List<String> excelData) {
        String[] value = new String[0];
        String resultString = "";
        String nextLineData = "" ;
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Street Name")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("Street Name", "").trim();
                nextLineData = nextLineData(excelData,i,"Street Name","Annual Turnover of Business");
                break;
            }
        }
        return resultString + nextLineData;
    }

    private String getEstablishmentDate(List<String> excelData) {
        String[] value = new String[0];
        String resultString = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Date of Establishment")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("PAN No(If any)", "").trim();
                break;
            }
        }
        return resultString;
    }
    //Name Of Owner of Business
    private String getNatureOfBusiness(List<String> excelData) {
        String[] value = new String[0];
        String nextLineData = "";
        String actualValue = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Name of Business")) {
                value = excelData.get(i).split(":");
                actualValue = value[2].replace("1.","").trim();
                //replaceAll("[\\d.]", "").trim();
                if(actualValue.equals("OTHERS")) {
                    return getBriefDescriptionData(excelData, i);
                }
                nextLineData = nextLineData(excelData, i, "Name of Business", "Name Of Owner");
                break;
            }
        }
        return actualValue + nextLineData;
    }

    public String getBriefDescriptionData(List<String> excelData, int index) {
        String nextLineData = "";
        String actualValue = "";
        String[] value = new String[3];
        for (int i = index + 1; i < excelData.size(); i++) {
            if(excelData.get(index+1).contains("Brief Description of Business")) {
                value = excelData.get(i).split(":");
                actualValue = value[2].trim();
                nextLineData = nextLineData(excelData, i, "Brief Description of Business", "Date of Establishment");
                //nextLineData.replace("Premises. Firm ","");
                break;
            }
        }
        return actualValue + nextLineData;
    }
    private String nextLineData(List<String> excelData, int index, String value1, String value2) {
        boolean flag = false;
        StringBuilder nextLineData = new StringBuilder();
        if(flag == false) {
            if (excelData.get(index).contains(value1)) {
                for (int j = index + 1; j < excelData.size(); j++) {
                    if (excelData.get(j).contains(value2)) {
                        flag = true;
                        break;
                    } else {
//                            if(excelData.get(j).equalsIgnoreCase("Premises. Firm")) {
//                                nextLineData.append("");
//                            } else {
                        nextLineData.append(" "+excelData.get(j).toString() + " ");
                        //}
                    }
                }
            }
        }
        return nextLineData.toString();
    }

    private String getBusinessName(List<String> excelData) {
        String[] value = new String[0];
        String resultString = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Name of Business")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("Nature of Business / Firm", "").trim();
                break;
            }
        }
        return resultString;
    }

    private String getApplicantName(List<String> excelData) {
        String[] value = new String[0];
        String resultString = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Name")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("Father Name", "").trim();
                break;
            }
        }
        return resultString;
    }

    private String getApplicantFathersName(List<String> excelData) {
        String[] value = new String[0];
        String nextLineData = "" ;
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Father Name")) {
                value = excelData.get(i).split(":");
                nextLineData = nextLineData(excelData,i,"Father Name","Ward No");
                break;
            }
        }
        return value[2].trim() + nextLineData;
    }

    private String getMobileNumber(List<String> excelData) {
        String[] value = new String[0];
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Mobile No")) {
                value = excelData.get(i).split(":");
                break;
            }
        }
        return value[2].trim();
    }

    private String getApplicantAddress(List<String> excelData) {
        String[] value = new String[0];
        String nextLineData = "" ;
        String resultString = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Address")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("Email Id", "").trim();
                nextLineData = nextLineData(excelData,i,"Address","Details Of Business");
                break;
            }
        }
        return resultString + nextLineData;
    }

    private String getLicenseNo(List<String> licenseList) {
        String[] license = new String[3];
        for(int i=0; i<licenseList.size(); i++) {
            if(licenseList.get(i).contains("License No")) {
                license = licenseList.get(i).split(":");
                break;
            }
        }
        return license[1].trim();
    };

    private String getApplicationType(List<String> excelData, String appTypeData1, String appTypeData2) {
        String value = "";
        for(int i=0; i<excelData.size(); i++) {
            if (excelData.get(i).contains(appTypeData1)) {
                value = "New License";
                break;
            } else if (excelData.get(i).contains(appTypeData2)){
                value = "Renewal";
                break;
            }
        }
        return value;
    }

    private String getFirmType(List<String> excelData, String firmType1,String firmType2,String firmType3,String firmType4, String firmType5,String firmType6, String firmType7) {
        String value = "";
        for(int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains(firmType1)) {
                value = "Proprietorship";
                break;
            } else if(excelData.get(i).contains(firmType2)) {
                value = "Partnership";
                break;
            } else if(excelData.get(i).contains(firmType3)) {
                value = "NGO";
                break;
            } else if(excelData.get(i).contains(firmType4)) {
                value = "OPC Pvt. Ltd.";
                break;
            } else if(excelData.get(i).contains(firmType5)) {
                value = "Private Limited";
                break;
            } else if(excelData.get(i).contains(firmType6)) {
                value = "Public Limited";
                break;
            } else if(excelData.get(i).contains(firmType7)) {
                value = "Others";
                break;
            }
        }
        return value;
    }

    private String getTypeOfOwnership(List<String> excelData, String typeOfOwnership1,String typeOfOwnership2, String typeOfOwnership3) {
        String value = "";
        for(int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains(typeOfOwnership1)) {
                value = "Rent";
                break;
            } else if(excelData.get(i).contains(typeOfOwnership2)) {
                value = "Lease";
                break;
            } else if(excelData.get(i).contains(typeOfOwnership3)) {
                value = "Own Premises";
                break;
            }
        }
        return value;
    }

    private String getTotalArea(List<String> excelData) {
        String[] value = new String[0];
        String resultString = "";
        String actualValue = "";
        for (int i=0; i<excelData.size(); i++) {
            if(excelData.get(i).contains("Total Area")) {
                value = excelData.get(i).split(":");
                resultString = value[1].replace("Total Area (Sq.Meter.)", "").trim();
                break;
            }
        }

        int areaInt = Integer.parseInt(resultString);
        if(areaInt >= 0  && areaInt <= 250) {
            actualValue = "0-250 Sq.Ft.";
        } if(areaInt >= 251 && areaInt <= 500) {
            //if(areaInt <= 500) {
            actualValue = "251-500 Sq.Ft.";
            //}
        } if(areaInt >= 501 && areaInt <= 750) {
            // if(areaInt <= 750) {
            actualValue = "501-750 Sq.Ft.";
            //}
        } if(areaInt >= 751 && areaInt <= 1000) {
            //if(areaInt <= 1000) {
            actualValue = "751-1000 Sq.Ft.";
            //}
        } if(areaInt >= 1001 && areaInt <= 1500) {
            //if(areaInt <= 1500) {
            actualValue = "1001-1500 Sq.Ft.";
            //}
        } if(areaInt >= 1501 && areaInt <= 2000) {
            //if(areaInt <= 2000) {
            actualValue = "1501-2000 Sq.Ft.";
            //}
        } if(areaInt >= 2001) {
            actualValue = "2001 and above";
        }

        return actualValue;
    }
}
