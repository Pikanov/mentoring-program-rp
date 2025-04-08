package step;

import com.epam.reportportal.annotations.Step;
import model.User;
import page.LoginPage;

public class LoginStep {
    LoginPage loginPage = new LoginPage();

    @Step
    public Boolean verifyIsLoginPageLoaded() {
        return loginPage.isLoginPageLoaded() & loginPage.isTitlePresent();
    }

    @Step
    public void login(final User user) {
        loginPage.fillNameField(user.getUsername())
                .fillPasswordField(user.getPassword())
                .clickSubmitButton();
    }

    @Step
    public Boolean verifyIsErrorMessageOnBottomPresent() {
        return loginPage.isErrorMessageOnBottomPresent();
    }
}
