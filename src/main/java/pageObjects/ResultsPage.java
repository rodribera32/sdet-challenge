package main.java.pageObjects;

import main.java.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultsPage extends BasePage {

    private DialogPage dialogPage;

    // -- Selectors --

    @FindBy(className = "list-item")
    private List<WebElement> listOfItems;

    @FindBy(css = ".list-pagination")
    private WebElement listPagination;

    @FindBy(css = ".jump-aera .next-input input")
    private WebElement goToSpecificPageInput;

    @FindBy(className = "jump-btn")
    private WebElement goToSpecificPageButton;

    @FindBy(className = "next-overlay-wrapper")
    private WebElement overlayWrapper;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    // -- SETTERS --

    public ResultsPage setNumberOfPage (String numOfPage) {
        // Need to scroll to list pagination container. The app loads the elements while you are scrolling.
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", listPagination);

        waitForElement(goToSpecificPageInput);
        sendKeys(goToSpecificPageInput, numOfPage);
        return this;
    }

    // -- METHODS --

    public void waitUntilOverlayIsNotDisplayed () {
        waitForElement(overlayWrapper);
        // Doing a workaround here with locators due to 'invisibilityOfElement' is not working correctly
        // Using 'invisibilityOfElementLocated'
        waitUntilElementIsNotVisible(By.className("next-overlay-wrapper"));
    }

    public ResultsPage clickOnGoPage () {
        click(goToSpecificPageButton);
        return this;
    }

    public ResultsPage goToAnotherPage(String numberOfPage) {
        dialogPage = new DialogPage(driver);
        dialogPage.closeDialogResultsPage();
        setNumberOfPage(numberOfPage);
        clickOnGoPage();
        waitUntilOverlayIsNotDisplayed();
        return this;
    }

    public ItemDetailsPage openItemByNumber (Integer numberOfItem) {
        listOfItems.get(numberOfItem - 1).click();
        return new ItemDetailsPage(driver);
    }
}
