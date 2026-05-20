package utilities;

import com.aventstack.extentreports.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.get().pass(" Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        try {
            testBase.BaseClass base = (testBase.BaseClass) testClass;

            String screenshotPath = base.captureScreenshot(result.getMethod().getMethodName());

            test.get()
                    .fail(result.getThrowable())
                    .addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            test.get().fail("Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        test.get().skip(" Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
}