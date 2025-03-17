package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final SelenideElement nameField = $(By.xpath("//*[@placeholder='Login']"));
    private final SelenideElement passwordField = $(By.xpath("//*[@placeholder='Password']"));
    private final SelenideElement title = $(By.xpath("//*[contains(@class,'loginPage__logo')]"));
    private final SelenideElement submitButton = $(By.xpath("//*[@type='submit']"));
    private final SelenideElement errorMessage = $(By.xpath("//*[contains(@class,'notificationItem__error')]"));

    public LoginPage() {
        super();
    }

    public LoginPage fillNameField(final String name) {
        if (name != null) {
            waitForElementToAppear(nameField);
            nameField.setValue(name);
        }
        return this;
    }

    public LoginPage fillPasswordField(final String password) {
        if (password != null) {
            waitForElementToAppear(passwordField);
            passwordField.setValue(password);
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
