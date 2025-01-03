package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Config;
import util.DriverCreator;


public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@placeholder='Login']")
    private WebElement nameField;

    @FindBy(xpath = "//*[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(@class,'loginPage__logo')]")
    private WebElement title;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(@class,'notificationItem__error')]")
    private WebElement errorMessage;

    public LoginPage() {
        super();
    }

    public Boolean isLoginPageLoaded() {
        return DriverCreator.getDriver().getCurrentUrl().equals(Config.getProperties("host"));
    }

    public LoginPage fillNameField(final String name) {
        if (name != null) {
            waitForElementToAppear(nameField);
            nameField.sendKeys(name);
        }
        return this;
    }

    public LoginPage fillPasswordField(final String password) {
        if (password != null) {
            waitForElementToAppear(passwordField);
            passwordField.sendKeys(password);
        }
        return this;
    }

    public void clickSubmitButton() {
        waitForElementToAppear(submitButton);
        submitButton.click();
    }

    public Boolean isTitlePresent() {
        waitForElementToAppear(title);
        return title.isDisplayed();
    }

    public Boolean isErrorMessageOnBottomPresent() {
        waitForElementToAppear(errorMessage);
        return errorMessage.isDisplayed();
    }
}
