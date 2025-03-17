package util;

import com.codeborne.selenide.Selenide;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

public class ListenerRP extends ReportPortalTestNGListener {

    @Override
    public void onTestFailure(final ITestResult testResult) {
        if (!testResult.isSuccess()) {
            String throwableMessage = testResult.getThrowable().toString();
            String methodName = testResult.getMethod().getMethodName();
            String screenshot = Selenide.screenshot(OutputType.BASE64);
            LoggingUtil.logBase64(screenshot, throwableMessage + " " + methodName);

            super.onTestFailure(testResult);
        }
    }
}
