package com.vytrack.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VehiclesPage extends BasePage {
    @FindBy(css = ".btn.favorite-button")
    public WebElement favoriteButton;

    @FindBy(css = ".btn.minimize-button")
    public WebElement pinButton;

    @FindBy(xpath = "//a[@title='Create Car']")
    public WebElement createCarButton;

    @FindBy(css = "a[title*='With this']")
    public WebElement exportGridMenu;

    @FindBy(css = "a[title='CSV']")
    public WebElement exportCSVButton;

    @FindBy(css = "a[title='XLSX']")
    public WebElement exportXLSXButton;

    @FindBy(css = "input[class='input-widget']")
    public WebElement pageTextInput;

    @FindBy(xpath = "(//label[@class='dib'])[2]")
    public WebElement totalPages;

    @FindBy(xpath = "(//label[@class='dib'])[3]")
    public WebElement totalRecords;

    @FindBy(css = "button[class='btn dropdown-toggle ']")
    public WebElement viewPerPage;

    @FindBy(xpath = "//a[@title='Filters']")
    public WebElement filtersButton;

    @FindBy(xpath = "//a[@title='Refresh']")
    public WebElement refreshButton;

    @FindBy(xpath = "//a[@title='Reset']")
    public WebElement resetButton;

    @FindBy(xpath = "//div[@class='column-manager dropdown']")
    public WebElement gridSettingsButton;

    @FindBy(xpath = "//div[@id='flash-messages']")
    public WebElement flashMessagesContainer;

    public static int maxPages = 0;
    public static int recordsCount = 0;
    public static int currentPage = 1;

    public static boolean favorite = false;
    public static boolean pinned = false;
    public static boolean exportMenuOpen = false;

    public VehiclesPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void pinPage() {
        if(!pinned) {
            pinned = true;
            pinButton.click();
        }
    }

    public void unPinPage() {
        if(pinned) {
            Driver.getDriver().findElement(By.cssSelector(".btn.minimize-button.gold-icon")).click();
            pinned = false;
        }
    }

    public void makeFavorite() {
        if(!favorite) {
            favorite = true;
            favoriteButton.click();
        }
    }

    public void makeUnFavorite() {
        if(favorite) {
            Driver.getDriver().findElement(By.cssSelector(".btn.favorite-button.gold-icon")).click();
            favorite = false;
        }
    }

    public void setRecordsCount(){
        String totalRecordsAsString = totalRecords.getText();
        totalRecordsAsString = totalRecordsAsString.replaceAll("[^0-9]", "");
        recordsCount = Integer.parseInt(totalRecordsAsString);
    }

    public void setMaxPages() {
        String pagesAsString = totalPages.getText();
        pagesAsString = pagesAsString.replaceAll("[^0-9]","");
        maxPages = Integer.parseInt(pagesAsString);
    }

    public void goToFirstPageOnRecords() {

        BrowserUtils.setAttribute(pageTextInput, "value", ("1"));
        pageTextInput.sendKeys(Keys.ENTER);
    }

    public void goToLastPageOnRecords() {
        setMaxPages();
        BrowserUtils.setAttribute(pageTextInput, "value", (maxPages+""));
        pageTextInput.sendKeys(Keys.ENTER);
    }

    public void nextPageOnRecords() {
        currentPage++;
        String keys = Keys.chord(Keys.SHIFT, Keys.CONTROL, Keys.ARROW_LEFT);
        if(currentPage <= maxPages) {
            BrowserUtils.setAttribute(pageTextInput, "value", (currentPage+""));
            pageTextInput.sendKeys(Keys.ENTER);
        } else {
            goToFirstPageOnRecords();
        }
    }

    public void previousPageOnRecords() {
        currentPage--;
        if(currentPage >= 1) {
            BrowserUtils.setAttribute(pageTextInput, "value", (currentPage+""));
            pageTextInput.sendKeys(Keys.ENTER);
        } else {
            goToFirstPageOnRecords();
        }
    }

    private void openExportMenu() {
        if(!exportMenuOpen) {
            exportGridMenu.click();
            exportMenuOpen = true;
        }
    }

    public void exportAsCSV(){
        if(!exportMenuOpen) {
            openExportMenu();
        }
        exportCSVButton.click();
        exportMenuOpen = false;
    }

    public void exportAsXLSX() {
        if(!exportMenuOpen) {
            openExportMenu();
        }
        exportXLSXButton.click();
        exportMenuOpen = false;
    }

    public void setViewPerPage(String val) {
        viewPerPage.click();
        List<WebElement> perPageViewCountOptions = Driver.getDriver().findElements(By.xpath("//ul//li//a[@class='dropdown-item']"));
        for(WebElement option : perPageViewCountOptions) {
            if(option.getText().contains(val)) {
                option.click();
                break;
            }
        }
        setMaxPages();
    }

    public void manageFilters(List<String> list) {
        filtersButton.click();
        BrowserUtils.waitForVisibility(By.xpath("//div[@class='filter-box oro-clearfix-width']"), 10);
        WebElement manageFiltersMenu = Driver.getDriver().findElement(By.xpath("//a[@class='add-filter-button']"));
        manageFiltersMenu.click();

        for(String string : list) {
            WebElement searchBox = Driver.getDriver().findElement(By.xpath("//input[@type='search']"));
            searchBox.clear();
            searchBox.sendKeys(string);
            String xPathExpression = "//label[@title='"+ string +"']//input";
            BrowserUtils.clickWithJS(Driver.getDriver().findElement(By.xpath(xPathExpression)));
        }
        manageFiltersMenu.click();
    }

    public void clearFilters() {
        reset();
    }

    public void closeFilters() {
        try {
            WebElement filtersContainer = Driver.getDriver().findElement(By.xpath("//div[@class='filter-box oro-clearfix-width']"));
            if(filtersContainer.isDisplayed()) {
                filtersButton.click();
            }
        } catch (Exception e){}
    }

    public void refresh() {
        refreshButton.click();
    }

    public void reset() {
        resetButton.click();
    }

    public void setGridView(List<String> gridList) {
        List<String> userPreferences = gridList;

        gridSettingsButton.click();

        //get the dropdown menu
        WebElement dropDownMenu = Driver.getDriver().findElement(By.xpath("//div[@class='dropdown-menu']"));

        //selectAll first
        Driver.getDriver().findElement(By.linkText("Select All"));

        //get the table
        WebElement optionsTable = dropDownMenu.findElement(By.xpath("//table[@class='grid table-hover table table-condensed']"));

        List<WebElement> gridOptions = optionsTable.findElements(By.xpath("//tr[@class=''] | //tr[@class='renderable']"));

        for(int i = 0; i < gridOptions.size(); i++) {
            List<WebElement> rowOptions = gridOptions.get(i).findElements(By.tagName("td"));
            WebElement rowTitle = rowOptions.get(0);
            WebElement label = rowTitle.findElement(By.tagName("label"));
            String labelText = label.getText();
            WebElement rowCheck = rowOptions.get(2).findElement(By.cssSelector("input[id*='column']"));

            if(!(userPreferences.indexOf(labelText) >= 0)) {
                rowCheck.click();
            }
        }
        Driver.getDriver().findElement(By.xpath("//span[@class='close']")).click();
    }

    public List<String> getFlashMessages() {
        List<WebElement> messages = flashMessagesContainer.findElements(By.xpath("//div[@class='message']"));
        List<String> messagesInString = new ArrayList<>();
        for(WebElement message : messages) {
            messagesInString.add(message.getText());
        }
        return messagesInString;
    }

    public void resetGrid() {
        reset();
    }

}



