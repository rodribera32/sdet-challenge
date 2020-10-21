package main.java.pageObjects;

import main.java.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage extends BasePage {

    private DialogPage dialogPage;

    // -- SELECTORS --

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

    // -- SETTERS --

    public ResultsPage setNumberOfPage (String numOfPage) throws InterruptedException {
        // Need to scroll and click list pagination container. The app loads the elements while the user is scrolling.
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", listPagination);
        click(listPagination);
        waitForElement(goToSpecificPageInput);
        sendKeys(goToSpecificPageInput, numOfPage);
        return this;
    }

    // -- METHODS --
   
    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilOverlayIsNotDisplayed () {
        waitForElement(overlayWrapper);
        // Doing a workaround here with locators due to 'invisibilityOfElement' is not working correctly.
        // Using 'invisibilityOfElementLocated'
        waitUntilElementIsNotVisible(By.className("next-overlay-wrapper"));
    }

    public ResultsPage clickOnGoPage () {
        click(goToSpecificPageButton);
        return this;
    }

    public ResultsPage goToSpecificPage(String numberOfPage)  throws InterruptedException  {
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
