package org.example;

import ObjectPage.AutomationPracticePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VladPC\\IdeaProjects\\MavenTest\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
//        options.setCapability("unhandledPromptBehavior ", "dismiss");
//        options.setCapability("getWindowRect", "maximized");
        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
    }

    @Test
    public void footerLinksCountEquals20() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        List<WebElement> links = page.getFooter().findElements(By.tagName("a"));
        Assert.assertEquals(links.size(), 20);
    }

    @Test(groups = {"RadioTests"})
    public void check2ndRadioBtn() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        List<WebElement> radioBtns = page.getRadioBtns();
        radioBtns.get(1).click();
        for (WebElement radio : radioBtns) {
            if (radio.equals(radioBtns.get(1))) {
                if (!radio.isSelected()) {
                    fail("2nd radio btn is not selected");
                }
            } else if (radio.isSelected()) {
                fail(radioBtns.indexOf(radio) + " is selected");
            }
        }
    }

    @Test(groups = {"RadioTests"})
    public void allRadioBtnsAreUnchecked() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        page.getRadioBtns().stream().filter(WebElement::isSelected).forEach(radio -> fail());
    }

    @Test
    public void suggestion2Results() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        WebElement suggestionBox = page.getSuggestionBox();
        suggestionBox.sendKeys("uk");

        List<WebElement> options = page.getSuggestionDropdownOptions();
        Assert.assertEquals(options.size(), 2);
        Assert.assertEquals(options.get(0).getText(), "Ukraine");
        Assert.assertEquals(options.get(1).getText(), "United Kingdom (UK)");

/*
        String a = suggestionBox.getAttribute("value");
        suggestionBox.sendKeys(Keys.ARROW_DOWN);
        a = suggestionBox.getAttribute("value");
        suggestionBox.sendKeys(Keys.ARROW_DOWN);
        a = suggestionBox.getAttribute("value");
        suggestionBox.sendKeys(Keys.ENTER);
        Assert.assertEquals(suggestionBox.getAttribute("value"), "United Kingdom (UK)");*/
    }

    @Test
    public void selectOption2() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        Select select = page.getSelect();
        select.selectByIndex(2);
        for (WebElement option : select.getOptions()) {
            String sel = option.isSelected() ? "selected" : "not selected";
            System.out.println(option.getText() + " is " + sel);
        }
        System.out.println(select.getFirstSelectedOption().getText());
    }

    @Test
    public void openNewTab() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        String originalUrl = driver.getCurrentUrl();
        WebElement tabLink = page.getpOpenNewTabLink();
        tabLink.click();
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        Assert.assertNotEquals(driver.getCurrentUrl(), originalUrl);
        driver.switchTo().window(windows.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), originalUrl);
    }

    @Test
    public void openNewWindow() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        String originalUrl = driver.getCurrentUrl();
        WebElement newWindowBtn = page.getOpenWindowBtn();
        newWindowBtn.click();
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        Assert.assertNotEquals(driver.getCurrentUrl(), originalUrl);
        driver.switchTo().window(windows.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), originalUrl);
    }

    @Test
    public void check2Checkboxes() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        List<WebElement> checkboxes = page.getCheckboxes();
        Assert.assertEquals(checkboxes.size(), 3);

        checkboxes.forEach(cb -> Assert.assertFalse(cb.isSelected()));

        checkboxes.get(0).click();
        checkboxes.get(2).click();

        Assert.assertTrue(checkboxes.get(0).isSelected());
        Assert.assertFalse(checkboxes.get(1).isSelected());
        Assert.assertTrue(checkboxes.get(2).isSelected());
    }

    @Test
    public void alertName() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        WebElement nameSuggestionField = page.getNameSuggestionField();
        nameSuggestionField.sendKeys("Vladyslav");
        WebElement alertBtn = page.getAlertBtn();
        Actions action = new Actions(driver);
        action.moveToElement(alertBtn);
        action.click().build().perform();
        Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Vladyslav, share this practice page and share your knowledge");
        driver.switchTo().alert().accept();
    }

    @Test
    public void dismissConfirmWithName() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        WebElement nameSuggestionField = page.getNameSuggestionField();
        nameSuggestionField.sendKeys("Vladyslav");
        WebElement confirmBtn = page.getConfirmBtn();
        confirmBtn.click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Vladyslav, Are you sure you want to confirm?");
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void showHoverContent() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        WebElement hoverBtn = page.getHoverBtn();
        Assert.assertFalse(page.getHoverContent().isDisplayed());
        new Actions(driver).moveToElement(hoverBtn).perform();
        Assert.assertTrue(page.getHoverContent().isDisplayed());
    }

    @Test
    public void eightDummyLinks() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        Assert.assertEquals(page.getDummyLinks().size(), 8);
    }

    @Test
    public void hideInputAndShow() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        Assert.assertTrue(page.getHideShowInput().isDisplayed());
        page.getHideBtn().click();
        Assert.assertFalse(page.getHideShowInput().isDisplayed());
        page.getShowBtn().click();
        Assert.assertTrue(page.getHideShowInput().isDisplayed());
    }

    @Test
    public void scrapeProductTable() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        for (WebElement row : page.getProductTable().findElements(By.xpath(".//tr/td/parent::node()"))) {
            List<WebElement> tds = row.findElements(By.xpath(".//td"));
            String name = tds.get(0).getText();
            String course = tds.get(1).getText();
            String price = tds.get(2).getText();
            System.out.println("Name: " + name + ", Course: " + course + ", Price: " + price);
        }
    }

    @Test
    public void iframeContactUrl() {
        AutomationPracticePage page = new AutomationPracticePage(driver);
        WebElement coursesIframe = page.getCoursesIframe();
        driver.switchTo().frame(coursesIframe);
        WebElement contactLink = driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
        Assert.assertEquals(contactLink.getAttribute("href"), "https://www.rahulshettyacademy.com/#/contact-us");
        driver.switchTo().defaultContent();
    }
    /*//Store the ID of the original window
    String originalWindow = driver.getWindowHandle();

    //Check we don't have other windows open already
    assert driver.getWindowHandles().size() == 1;

    //Click the link which opens in a new window
    driver.findElement(By.linkText("new window")).click();

    //Wait for the new window or tab
    wait.until(numberOfWindowsToBe(2));

    //Loop through until we find a new window handle
    for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

    //Wait for the new tab to finish loading content
    wait.until(titleIs("Selenium documentation"));*/
}
