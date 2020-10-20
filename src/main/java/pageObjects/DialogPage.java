package main.java.pageObjects;

import main.java.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialogPage extends BasePage {

  // --- SELECTORS ---
  // - Dialog Results Page -
  @FindBy(className = "next-dialog-body")
  private WebElement dialogResultsPage;

  @FindBy(className = "next-dialog-close")
  private WebElement closeDialogResultsPageButton;

  // -- Dialog Home Page --
  @FindBy(css = "body iframe[src*=campaign]")
  private WebElement homePageIframeDialog;

  @FindBy(css = ".rax-image[style*='top']")
  private WebElement closeDialogHomePageButton;

  // --- METHODS ---

  public DialogPage (WebDriver driver) {
    super(driver);
  }

  public boolean isDialogVisible () {
    return dialogResultsPage.isDisplayed();
  }

  public HomePage closeDialogHome () {
    switchToFrame(homePageIframeDialog);
    click(closeDialogHomePageButton);
    driver.switchTo().defaultContent();
    return new HomePage(driver);
  }

  public ResultsPage closeDialogResultsPage () {
    click(closeDialogResultsPageButton);
    return new ResultsPage(driver);
  }

}