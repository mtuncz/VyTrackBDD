package com.vytrack.utilities;

import io.cucumber.java.et.Ja;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    public static void switchWindowAndVerify(String expectedInUrl, String expectedTitle) {
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for(String handle : allWindowHandles) {
            Driver.getDriver().switchTo().window(handle);
            if(Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }

        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue("Title verification failed!", actualTitle.contains(expectedTitle));
    }

    public static void verifyTitle(WebDriver driver, String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    public static void waitForInvisibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void verifyURLContains(String expectedInURL) {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInURL));
    }

    /**
     * accepts select
     * @param dropdownElement
     * @return
     */

    public static List<String> selectDropdownOptionsAsList(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);

        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        List<String> actualOptionsAsString = new ArrayList<>();

        for(WebElement each : actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());
        }

        return actualOptionsAsString;
    }

    public static List<String> divDropDownOptionsAsList(WebElement dropdownElement) {
        dropdownElement.click();

        WebElement parentOfDropDown = dropdownElement.findElement(By.xpath(".."));
        WebElement dropDownList = parentOfDropDown.findElement(By.xpath(".//ul"));

        List<WebElement> options = dropDownList.findElements(By.xpath(".//li"));

        List<String> actualOptionsAsString = new ArrayList<>();

        for(WebElement element : options) {
            actualOptionsAsString.add(element.getText());
        }

        return actualOptionsAsString;
    }


    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue) {
        for(WebElement button : radioButtons) {
            if(button.getAttribute("value").equalsIgnoreCase(attributeValue)) {
                button.click();
                break;
            }
        }
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();

        for(String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if(Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(element).perform();
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for(WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static List<String> getElementsText(By locator) {
        List<WebElement> elements = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for(WebElement element : elements) {
            elemTexts.add(element.getText());
        }
        return elemTexts;
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                return ((JavascriptExecutor)Driver.getDriver()).executeScript("return document.readyState").equals("complete");
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch(Throwable error) {
            error.printStackTrace();
        }
    }

    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue("Element not visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);
        }
    }

    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse("Element should not be visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static void verifyElementDisplated(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public static void clickWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public static void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red;');", element);
        waitFor(1);
        js.executeScript("arguments[0].removeAttribute('style','background: yellow; border: 2px solid red;');", element);
    }

    public static void selectCheckBox(WebElement element, boolean check) {
        if(check) {
            if(!element.isSelected()) {
                element.click();
            }
        } else {
            if(element.isSelected()) {
                element.click();
            }
        }
    }


    public static void clickWithTimeOut(WebElement element, int timeout) {
        for(int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch(WebDriverException e) {
                e.printStackTrace();
            }
        }
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript(command, element);
    }

    public static void executeJScommand(String command) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript(command);
    }

    public static void clickWithWait(By by, int attempts) {
        int counter = 0;

        while(counter < attempts) {
            try {
                clickWithJS(Driver.getDriver().findElement(by));
                break;
            } catch (WebDriverException e) {
                e.printStackTrace();
                ++counter;
                waitFor(1);
            }
        }
    }


    public static void waitForPresenceOfElement(By by, long time) {
        new WebDriverWait(Driver.getDriver(), time).until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void waitLoaderMask() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.getDriver()).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoreAll(new ArrayList<Class<? extends Throwable>>() {
            {
                add(java.util.NoSuchElementException.class);
                add(StaleElementReferenceException.class);
                add(ElementClickInterceptedException.class);
                add(ElementNotVisibleException.class);
                add(ElementNotSelectableException.class);
            }
        });
        By loaderXPath = By.xpath("//div[@id='progressbar']");
        By loaderMask = By.xpath("//div[@class='loader-mask shown']");

        WebElement progressbar = Driver.getDriver().findElement(loaderXPath);

        if(progressbar.isDisplayed()) {
            wait.until(ExpectedConditions.invisibilityOf(progressbar));
        }


        try {
            WebElement mask = Driver.getDriver().findElement(loaderMask);
            if (mask.isDisplayed()) {
                wait.until(ExpectedConditions.invisibilityOf(mask));
            }
        } catch(Exception e) {}
    }


    /**
     * TODO: probably not working see the above method, waitforloader mask
     */
    public static void waitForDashboardToLoad() {
        WebElement dashboard = Driver.getDriver().findElement(By.xpath("//div[@class='app-page__main']"));
        waitForVisibility(dashboard,10);
        waitForClickability(dashboard, 10);
    }


























}
