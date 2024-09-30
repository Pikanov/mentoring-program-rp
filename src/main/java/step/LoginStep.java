package step;

import com.epam.reportportal.annotations.Step;
import model.User;
import page.DashboardPage;
import page.LoginPage;

public class LoginStep {
    LoginPage loginPage = new LoginPage();

    @Step
    public Boolean verifyIsLoginPageLoaded() {
        return loginPage.isLoginPageLoaded() & loginPage.isTitlePresent();
    }

    @Step
    public DashboardPage login(User user) {
        loginPage.fillNameField(user.getUsername())
                .fillPasswordField(user.getPassword())
                .clickSubmitButton();
        //TODO should be investigated which page will be open after success login.
        return new DashboardPage();
    }

    @Step
    public Boolean verifyIsErrorMessageOnBottomPresent() {
        return loginPage.isErrorMessageOnBottomPresent();
    }
}
