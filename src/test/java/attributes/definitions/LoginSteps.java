package attributes.definitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.testng.Assert;
import page.LoginPage;
import util.ConfigUtil;
import util.TestDataReader;

import java.util.List;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final TestContext testContext;

    public LoginSteps() {
        this.testContext = new TestContext();
    }

    @Then("the login page should be displayed")
    public void verifyLoginPageIsLoaded() {
        Assert.assertTrue(loginPage.isLoginPageLoaded() && loginPage.isTitlePresent(),
                "Login page was not loaded");
    }

    @When("they enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginPage.fillNameField(username)
                .fillPasswordField(password)
                .clickSubmitButton();
    }

    @Then("an error message should be displayed")
    public void verifyErrorMessageIsDisplayed() {
        Assert.assertTrue(loginPage.isErrorMessageOnBottomPresent(), "Error message was not displayed");
    }

    @Given("the user has invalid credentials")
    public void loadInvalidUserCredentials() {
        List<User> users = TestDataReader.getTestData(
                ConfigUtil.getTestDataProperty("invalidUserCredentials"), User[].class);
        if (!users.isEmpty()) {
            testContext.setCurrentUser(users.get(0));
        }
    }

    @When("they attempt to log in with invalid credentials")
    public void loginWithInvalidCredentials() {
        User user = testContext.getCurrentUser();
        if (user != null) {
            loginPage.fillNameField(user.getUsername())
                    .fillPasswordField(user.getPassword())
                    .clickSubmitButton();
        } else {
            throw new IllegalStateException("No invalid user credentials available");
        }
    }
}