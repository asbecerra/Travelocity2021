package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



/**
 * BasePage Object
 */
public class BasePage {
    private WebDriver driver;
    private static WebDriverWait wait;
    /**
     * Default constructor to create instance of BP class using Page Factory
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    /**
     * Return the driver
     * @return
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Verifies if an element is clickable before clicking on it
     * @param element
     * @return
     */
    public void isClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * verifies if an element is visible before interacting with it
     * @param element
     * @return
     */
    public void isVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * click on an element
     * @param element
     */
    public void clickOn(WebElement element) {
        isClickable(element);
        element.click();
    }

    /**
     * Allows to send keys to a Webelement
     * @param element
     * @param keys
     */
    public void sendKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    /**
     * Verifies if a WebElement is selected
     * @param element
     * @return
     */
    public boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    /**
     * Verifies if a WebElement is selected using the Attribute "Aria-Selected"
     * @param element
     * @return
     */
    public boolean isAriaSelected(WebElement element) {
        return element.getAttribute("aria-selected").equals("true");
    }

    /**
     * Verifies that all elements of a List are visible
     * @param elements
     */
    public void allElementsVisible(List<WebElement> elements) {
         wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Verifies that element is not visible
     * @param element
     * @return
     */
    public boolean isInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

}
