package util;


import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;


public class ListenerRP extends ReportPortalTestNGListener {

    public void onTestFailure(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            Object currentClass = testResult.getInstance().getClass();
            LoggingUtil.log(currentClass.toString());

            String throwableMessage = testResult.getThrowable().toString();
            String methodName = testResult.getMethod().getMethodName();
            String screenshot = ((TakesScreenshot) DriverCreator.getDriver()).getScreenshotAs(OutputType.BASE64);
            LoggingUtil.logBase64(screenshot, throwableMessage + methodName);

            super.onTestFailure(testResult);
        }
    }
}

