package com.vytrack.stepdefinitions;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.LoginPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VehiclesPageStepDefs {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    VehiclesPage vehiclesPage = new VehiclesPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Assert.assertTrue("Login page does not match", loginPage.titleBox.getText().contains("Login"));
    }

    @When("user enters {string} and {string} and logs in")
    public void user_enters_and_and_logs_in(String username, String password) {
        loginPage.login(username, password);
    }

    @When("user lands on homepage")
    public void user_lands_on_homepage() {
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Dashboard"));
    }

    @Then("user opens vehicles under fleet dropdown menu")
    public void user_opens_vehicles_under_fleet_dropdown_menu() {
        basePage.openVehiclesUnderFleet();
    }

    @Then("user should be able to click on export grid dropdown")
    public void user_should_be_able_to_click_on_export_grid_dropdown() {
        try {
            String string = ExpectedConditions.elementToBeClickable(vehiclesPage.exportGridMenu).toString();
            Assert.assertTrue(string.contains("clickable"));
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
    }


    @Then("export grid dropdown button should be on the left of the page")
    public void export_grid_dropdown_button_should_be_on_the_left_of_the_page() {
        // get the window size
        Dimension windowDimension = Driver.getDriver().manage().window().getSize();
        int windowWidth = windowDimension.getWidth();

        // grid menu point on screen
        Point exportGridButton = vehiclesPage.exportGridMenu.getLocation();
        int exportGridButtonXPos = exportGridButton.getX();

        Assert.assertTrue("Export button is not on the left of the page", exportGridButtonXPos < windowWidth / 10);
    }

    @Then("user should be able to click refresh button")
    public void user_should_be_able_to_click_refresh_button() {
        try {
            String string = ExpectedConditions.elementToBeClickable(vehiclesPage.refreshButton).toString();
            Assert.assertTrue(string.contains("clickable"));
        } catch(Exception e) {
            Assert.assertTrue(false);
        }

    }


    @Then("user should be able to click reset button")
    public void user_should_be_able_to_click_reset_button() {
        try {
            String string = ExpectedConditions.elementToBeClickable(vehiclesPage.resetButton).toString();
            Assert.assertTrue(string.contains("clickable"));
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
        
    }
    @Then("user should be able to click Grid Settings button")
    public void user_should_be_able_to_click_grid_settings_button() {
        try {
            String string = ExpectedConditions.elementToBeClickable(vehiclesPage.gridSettingsButton).toString();
            Assert.assertTrue(string.contains("clickable"));
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Then("refresh button should be on the left side of reset button")
    public void refresh_button_should_be_on_the_left_side_of_reset_button() {
        Point refreshButtonLocation = vehiclesPage.refreshButton.getLocation();

        Point resetButtonLocation = vehiclesPage.resetButton.getLocation();

        int refreshButtonXPos = refreshButtonLocation.getX();
        int resetButtonXPos = resetButtonLocation.getX();

        Assert.assertTrue("Refresh is not on the left of reset button", refreshButtonXPos < resetButtonXPos);

    }


    @Then("grid settings should be on the right side of the reset button")
    public void grid_settings_should_be_on_the_right_side_of_the_reset_button() {
        Point resetButtonLocation = vehiclesPage.resetButton.getLocation();

        Point gridSettingsButtonLocation = vehiclesPage.gridSettingsButton.getLocation();

        int resetButtonXPos = resetButtonLocation.getX();
        int gridSettingButtonXPos = gridSettingsButtonLocation.getX();

        Assert.assertTrue("Grid Settings button is not on the right of reset button", gridSettingButtonXPos > resetButtonXPos);
    }


    @Then("grid setting button is on the right of page")
    public void grid_setting_button_is_on_the_right_of_page() {
        Dimension windowDimension = Driver.getDriver().manage().window().getSize();
        int windowWidth = windowDimension.getWidth();

        Point gridSettingButtonLocation = vehiclesPage.gridSettingsButton.getLocation();
        int gridSettingButtonXPos = gridSettingButtonLocation.getX();

        Assert.assertTrue("Grid setting button is not on the right side of the screen", gridSettingButtonXPos > windowWidth / 10);
    }



    @Then("user logs out from the application")
    public void user_logs_out_from_the_application() {
        vehiclesPage.clickLogOutUnderUserMenu();
    }
}
