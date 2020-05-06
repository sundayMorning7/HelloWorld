package ObjectPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AutomationPracticePage {
    private final WebDriver driver;
    @FindBy(xpath = "//div[@id='gf-BIG']")
    WebElement footer;
    @FindBy(xpath = "//div[@id='radio-btn-example']//input")
    List<WebElement> radioBtns;
    @FindBy(xpath = "//input[@id='autocomplete']")
    WebElement suggestionBox;
    @FindBy(xpath = "//ul[@id='ui-id-1']")
    WebElement suggestionDropdown;
    @FindBy(xpath = "//ul[@id='ui-id-1']/li[@class='ui-menu-item']/div")
    List<WebElement> suggestionDropdownOptions;
    @FindBy(id = "dropdown-class-example")
    WebElement select;
    @FindBy(id = "opentab")
    private WebElement openNewTabLink;
    @FindBy(css = "#checkbox-example input")
    private List<WebElement> checkboxes;
    @FindBy(id = "openwindow")
    private WebElement openWindowBtn;
    @FindBy(id = "name")
    private WebElement nameSuggestionField;
    @FindBy(id = "alertbtn")
    private WebElement alertbtn;
    @FindBy(id = "confirmbtn")
    private WebElement confirmBtn;
    @FindBy(xpath = "//div[@class='mouse-hover-content']")
    private WebElement hoverContent;
    @FindBy(xpath = "//div[@class='mouse-hover']")
    private WebElement hoverBtn;
    @FindBy(xpath = "//a[contains(text(),'Dummy')]")
    private List<WebElement> dummyLinks;
    @FindBy(id = "displayed-text")
    private WebElement hideShowInput;
    @FindBy(id = "hide-textbox")
    private WebElement hideBtn;
    @FindBy(id = "show-textbox")
    private WebElement showBtn;
    @FindBy(id = "product")
    private WebElement productTable;
    @FindBy(id = "courses-iframe")
    private WebElement coursesIframe;

    public AutomationPracticePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getCoursesIframe() {
        return coursesIframe;
    }

    public WebElement getProductTable() {
        return productTable;
    }

    public WebElement getHideShowInput() {
        return hideShowInput;
    }

    public WebElement getHideBtn() {
        return hideBtn;
    }

    public WebElement getShowBtn() {
        return showBtn;
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public WebElement getFooter() {
        return footer;
    }

    public List<WebElement> getRadioBtns() {
        return radioBtns;
    }

    public WebElement getSuggestionBox() {
        return suggestionBox;
    }

    public WebElement getSuggestionDropdown() {
        return suggestionDropdown;
    }

    public List<WebElement> getSuggestionDropdownOptions() {
        return suggestionDropdownOptions;
//        return suggestionDropdown.findElements(By.xpath("//li[@class='ui-menu-item']/div"));
    }

    public Select getSelect() {
        return new Select(select);
    }

    public WebElement getpOpenNewTabLink() {
        return openNewTabLink;
    }

    public WebElement getOpenWindowBtn() {
        return openWindowBtn;
    }

    public WebElement getNameSuggestionField() {
        return nameSuggestionField;
    }

    public WebElement getAlertBtn() {
        return alertbtn;
    }

    public WebElement getConfirmBtn() {
        return confirmBtn;
    }

    public WebElement getHoverBtn() {
        return hoverBtn;
    }

    public WebElement getHoverContent() {
        return hoverContent;
    }

    public List<WebElement> getDummyLinks() {
        return dummyLinks;
    }
}
