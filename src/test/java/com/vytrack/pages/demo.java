package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en_old.Ac;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class demo {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    VehiclesPage vehiclesPage = new VehiclesPage();

    @Test
    public void test() throws Exception{
        Driver.getDriver().get(ConfigurationReader.getProperty("web.app.url"));
        loginPage.login("user10", "UserUser123");

        BrowserUtils.waitLoaderMask();
        basePage.openVehiclesUnderFleet();

        vehiclesPage.pinPage();
        vehiclesPage.unPinPage();

        vehiclesPage.makeFavorite();
        vehiclesPage.makeUnFavorite();

        vehiclesPage.setViewPerPage("10");
        System.out.println(VehiclesPage.maxPages);

        vehiclesPage.setViewPerPage("25");
        System.out.println(VehiclesPage.maxPages);

        vehiclesPage.setViewPerPage("50");
        System.out.println(VehiclesPage.maxPages);

        vehiclesPage.setViewPerPage("100");
        System.out.println(VehiclesPage.maxPages);



        List<String> filters = new ArrayList<>(Arrays.asList("License Plate", "Driver", "Tags", "Model Year"));

        vehiclesPage.manageFilters(filters);
        vehiclesPage.clearFilters();

        vehiclesPage.setGridView(filters);

        vehiclesPage.exportAsCSV();
        System.out.println(vehiclesPage.getFlashMessages().get(0));

        Point point = vehiclesPage.exportGridMenu.getLocation();
        Dimension size = Driver.getDriver().manage().window().getSize();
        System.out.println("x location is " + point.getX());



    }
}
