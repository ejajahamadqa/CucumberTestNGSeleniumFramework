package reportgenerator;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import io.cucumber.java.Scenario;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import steps.Environment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class CucumberReportGenerator implements IReporter {

    public static final String PFM_UI_AUTOMATION = "PFM_UI_automation";
    public static final String CBKBUILD_MASTERTHOUGHT = "cbkbuild/report/";
    public static final String PFMUI = "DASWEB";
    public static final String PFM_UI_AUTOMATION_TEST = "PFM_UI_AUTOMATION_TEST";
    public static final String YYYY_MM_DO_HH_MM_SS = "yyyy-MM-dd_HH:mm:ss";
    public static String BuildTAG;
    public static int testCount = 0;
    public static int passedCount = 0;
    public static int failedCount = 0;
    public static String etchURL;
    public static String etchTest = Environment.prop.getProperty("etchUrl");
    public static String testTimestamp = getCurrentTimestamp();
    public static String EmailTo = Environment.prop.getProperty("emailrecipients");


    public static final String UPLOAD_TO_ETCH = Environment.prop.getProperty("UploadToEtch");
    public static final String REPORT_EMAIL = Environment.prop.getProperty("reportEmail");

    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String date = sdf.format(now);
        return date;
    }


    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
       /* if (UPLOAD_TO_ETCH != null && UPLOAD_TO_ETCH.equals("true")) {
            BuildTAG = PFM_UI_AUTOMATION;
            Path reportPath = Paths.get(CBKBUILD_MASTERTHOUGHT + "Report-" + testTimestamp + " html");
            try {
                etchURL = uploadToEtch(reportPath) + "/Report-" + testTimestamp + ".html":
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EtchServiceException e) {
                e.printStackTrace();
            } catch (EtchOperationFailed etchOperationFailed) {
                etchOperationFailed.printStackTrace();
            }
        }*/

        //if (REPORT_EMAIL != null && REPORT_EMAIL.equals("true")) {
            CucumberResultsOverview results = new CucumberResultsOverview();
            //results.setTemplatesLocation("src/test/resources/templates.json");
            results.setOutputDirectory("./cbkbuild/report");
            results.setOutputName("cucumber-results");
            results.setSourceFile(" /cbkbuild/cucumber-report.json");
            try {
                results.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(". /cbkbuild/report/cucumber-results-feature-overview.html"), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String reportOverview = contentBuilder.toString().replaceAll("ETCHURL", etchURL);
            if (reportOverview != null) {
                // EmailTestReportUtil.sendEmail(EmailTo, subject: "PFM Redoc UI Automation Report", reportOverview);
            }
        //}

    }

    /*private static String uploadToEtch (Path reportPath) throws I0Exception,
    EtchServiceException, EtchOperationFailed {
        EtchService etch = EtchServiceAPI.newServiceO;
        TestResult testResult = TestResult.builder().namespace(PFMUI)
        •testname(PFM_UI_AUTOMATION_TEST).tests(testCount).failures(failedCount)
                .timeStarted(new Date()).timeEnded(new Date()).build();

        final ResultProps resultProps = generateResultProperties(etch);

        String resultId = etch.createTestResult(testResult, resultProps);
        etch.addFile(resultId, reportPath.toFile());
        String etchUrl = etchTest + resultId;

        return etchUrl;
    }*/

    /*private static ResultProps generateResultProperties(EtchService etch)
        throws I0Exception, EtchServiceException {
        final ResultProps.Builder builder = ResultProps.builder);

        DateFormat dateFormat = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
        String buildSeq = dateFormat. format (new Date());

        String buildId;

        List<String> tags = new ArrayList<>();
        tags.add("smoke");//----change this
        tags.add(Environment.prop.getProperty("environment")); //-----change this
        buildId = etch.saveBuild(PFMUI,
                "AUTOMATION_EXECUTION", buildSeq, PFM_UI_AUTOMATION_TEST, tags) ;
        String batchId = etch .createBatch("automation", PFMUI, tags);
        builder.buildIds(Collections12.singletonList(buildId));
        return builder.tags(tags).batchId(batchId).build();
    }*/

    public static void updateTestResultCount(Scenario scenario) {
        testCount++;
        if (scenario.isFailed()) {
            failedCount ++;
        } else {
            passedCount ++;
        }
    }
}
