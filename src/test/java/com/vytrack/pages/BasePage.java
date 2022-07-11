package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @FindBy(xpath = "(//a[@title='Fleet Management'])[2]")
    public WebElement logo;

    @FindBy(xpath = "//span[normalize-space()='Fleet']")
    public WebElement mainMenuFleet;

    @FindBy(xpath = "(//a[@href='entity/Extend_Entity_Carreservation'])[1]")
    public WebElement vehiclesUnderFleet;

    @FindBy(xpath = "(//a[@href='/entity/Extend_Entity_VehiclesOdometer'])[1]")
    public WebElement vehicleOdometerUnderFleet;

    @FindBy(xpath = "(//a[@href='/entity/Extend_Entity_VehicleCosts'])[3]")
    public WebElement vehicleCostsUnderFleet;

    @FindBy(xpath = "(//a[@href='/entity/Extend_Entity_VehicleContract'])[3]")
    public WebElement vehicleContractsUnderFleet;

    @FindBy(xpath = "(//a[@href='/entity/Extend_Entity_VehicleFuelLogs'])[1]")
    public WebElement vehicleFuelLogsUnderFleet;

    @FindBy(xpath = "(//a[@href='entity/Extend_Entity_VehicleServicesLogs'])[1]")
    public WebElement vehicleServiceLogsUnderFleet;

    @FindBy(xpath = "(//a[@href='/entity/Extend_Entity_VehiclesModel'])[3]")
    public WebElement vehicleModelUnderFleet;

    @FindBy(xpath = "//ul[@class='nav pull-right user-menu']")
    public WebElement navPullRightUserMenu;


    public boolean userMenuOpen = false;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void openVehiclesUnderFleet() {
        BrowserUtils.hover(mainMenuFleet);
        vehiclesUnderFleet.click();
        BrowserUtils.waitLoaderMask();
    }

    public void clickUserMenu(){
        WebElement userMenu = navPullRightUserMenu.findElement(By.xpath(".//li[@id='user-menu']"));
        if(!userMenuOpen) {
            userMenuOpen = true;
        } else {
            userMenuOpen = false;
        }
        userMenu.click();

    }

    public void clickLogOutUnderUserMenu(){
        if(!userMenuOpen) {
            clickUserMenu();
        }
        WebElement logoutButton = Driver.getDriver().findElement(By.xpath("//div//ul//li//a[@href='/user/logout']"));
        logoutButton.click();
    }

    public void goBackToHome() {
        logo.click();
    }

}
