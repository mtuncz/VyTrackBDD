package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "prependedInput")
    public WebElement username;

    @FindBy(id = "prependedInput2")
    public WebElement password;

    @FindBy(id = "_submit")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[@href='/user/reset-request']")
    public WebElement forgotPassword;

    @FindBy(name = "_remember_me")
    public WebElement rememberMeCheckBox;

    @FindBy(xpath = "//div[@class='title-box']//h2")
    public WebElement titleBox;

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginBtn.click();
        BrowserUtils.waitLoaderMask();
    }





}
