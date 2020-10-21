package main.java.pageObjects;

import main.java.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  public DialogPage dialog;

  // -- SELECTORS --
  @FindBy(className = "index-page")
  private WebElement homePageContainer;

  // - Search Bar -
  @FindBy(className= "search-key-box")
  private WebElement searchField;

  @FindBy(id= "search-key")
  private WebElement searchInputField;

  @FindBy(className= "search-button")
  private WebElement searchButton;

  // -- SETTERS --

  public HomePage setSearchField (String value) {
    waitForElement(searchField);
    sendKeys(searchInputField, value);
    return this;
  }

  // -- METHODS --

  public HomePage (WebDriver driver) {
    super(driver);
  }

  public HomePage waitUntilHomePageIsVisible () throws InterruptedException {
    waitForElement(homePageContainer);
    dialog = new DialogPage(driver);
    dialog.closeDialogHome();
    return this;
  }

  public HomePage clickOnSearchButton () {
    waitForElement(searchButton);
    click(searchButton);
    return this;
  }

  public ResultsPage searchItem (String item) throws InterruptedException {
    waitUntilHomePageIsVisible();
    setSearchField(item);
    clickOnSearchButton();
    return new ResultsPage(driver);
  }
}