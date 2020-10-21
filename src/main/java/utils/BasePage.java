package main.java.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

	/**
	 * Leaves the given checkbox 'checked'.
	 * 
	 * @param checkbox - The checkbox we want to check.
	 * @throws Exception
	 */
	protected void selectCheckbox(WebElement checkbox) throws Exception {
		if (!checkbox.isSelected()) {
			click(checkbox);
		}
	}
	
	/**
	 * Leaves the given checkbox 'unchecked'.
	 * 
	 * @param checkbox - The checkbox we want to uncheck.
	 * @throws Exception
	 */
	protected void unselectCheckbox(WebElement checkbox) throws Exception {
		if (checkbox.isSelected()) {
			click(checkbox);
		}
	}

	protected void sendKeys(WebElement element, String text) {
		waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}
	
	protected WebElement getWebElementByXpath(String strlocator) {
		return driver.findElement(By.xpath(strlocator));
	}
	
	protected void sendKeys(WebElement element, Keys key) {
		waitForElement(element);
		element.clear();
		element.sendKeys(key);
	}
	
	protected void sendKeysNoClear(WebElement element, Keys key) {
		waitForElement(element);
		element.sendKeys(key);
	}

	protected void click(WebElement element) {
		waitForElement(element);
		try {
			element.click();
		} catch (WebDriverException exception) {
			try {
				Thread.sleep(10000);
			} catch (Exception waitException) {
				System.out.println("Error while thread.sleep");
			}

			Actions actions = new Actions(driver);

			actions.moveToElement(element).click().perform();
		}
	}
	
	protected void clickJavaScript(WebElement element) {
		waitForElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	protected void doubleClick(WebElement element) {
		waitForElement(element);

		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		action.doubleClick(element).perform();
	}

	protected void selectByVisibleText(WebElement element, String visibleText) {
		waitForElement(element);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	protected void selectByValue(WebElement element, String value) {
		waitForElement(element);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * We return true if we reach the given URL after 15 after a maximum of 15
	 * seconds.
	 * 
	 * @param expectedURL
	 * @return boolean : true if we reach the given URL.
	 * @throws Exception
	 */
	public boolean areWeOnURL(String expectedURL) throws Exception {
		String currentURL = driver.getCurrentUrl();
		int currentTry = 1;
		int maxTries = 3;

		while (!currentURL.contains(expectedURL) && currentTry <= maxTries) {
			Thread.sleep(30000);
			currentURL = driver.getCurrentUrl();
			currentTry++;
		}

		return currentTry <= maxTries;
	}

	protected String getText(WebElement element) {
		waitForElement(element);
		return element.getText();
	}

	protected String getValue(WebElement element) {
		waitForElement(element);
		return element.getAttribute("value");
	}

	public void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementIsNotVisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}


	public void switchToFrame(WebElement iframe) {
		waitForElement(iframe);
		driver.switchTo().frame(iframe);
	}

	public void switchTab (Integer tabIndex) throws InterruptedException {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		if (browserName.contains("chrome")) {
			driver.switchTo().window(tabs.get(tabIndex - 1));
		} else {
			// Thread.sleep is a bad practice but Firefox is opening a new windows.
			Thread.sleep(2000);
			driver.switchTo().window(tabs.get(tabIndex - 1));
		}
	}


	/**
	 * Returns true if the given element is enabled and visible.
	 * 
	 * @param element
	 *            - The WebElement.
	 * @return boolean - true if the element is enabled and visible.
	 */
	public boolean isElementClickable(WebElement element) {
		boolean isElementClickable = true;

		try {
			waitForElement(element);
			isElementClickable = element.isDisplayed() && element.isEnabled();
		} catch (WebDriverException exception) {
			isElementClickable = false;
		}

		return isElementClickable;
	}
	
}
