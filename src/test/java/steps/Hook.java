package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BasePage;
import reportgenerator.CucumberReportGenerator;

import java.io.File;
import java.io.IOException;

public class Hook extends Environment {

    BasePage basePage = new BasePage();

    @AfterStep
    public void tearDownStep(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            scenario.attach(getScreenshotByte(), "image/png", scenario.getName() + ".png");
        }
    }

    public byte[] getScreenshotByte() throws IOException {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        return FileUtils.readFileToByteArray(src);
    }

    @Before
    public void beforeTest() {
        initialization();
    }

    @After
    public void afterTest(Scenario scenario) {
        CucumberReportGenerator.updateTestResultCount(scenario);
        //tearDownStep(scenario);
        basePage.quitBrowser();
    }


}
