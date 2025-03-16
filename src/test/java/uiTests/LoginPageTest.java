package uiTests;

import baseTest.BaseTest;
import model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import step.LoginStep;
import util.ConfigUtil;
import util.TestDataReader;

import java.util.List;

public class LoginPageTest extends BaseTest {

    @Test
    public void verifyIsAppLoaded() {
        Assert.assertTrue(new LoginStep().verifyIsLoginPageLoaded(), "Login page wasn't open");
    }


    @Test(dataProvider = "invalidUserCredentialsDataProvider", threadPoolSize = 5)
    public void verifyIsUserCannotLoginWithWrongCredentials(User user) {
        final LoginStep loginStep = new LoginStep();

        new LoginStep().login(user);
        Assert.assertTrue(loginStep.verifyIsErrorMessageOnBottomPresent(), "Error message isn't appear");
    }

    @DataProvider(name = "invalidUserCredentialsDataProvider", parallel = true)
    public Object[][] provideInvalidUserData() {
        List<User> users =
                TestDataReader.getTestData(ConfigUtil.getTestDataProperty("invalidUserCredentials"), User[].class);
        return users.stream()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }
}
