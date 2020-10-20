package main.java.pageObjects;

import main.java.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class ItemDetailsPage extends BasePage {
    @FindBy(className = "product-info")
    private WebElement productInfo;

    @FindBy(css = ".product-number-picker input")
    private WebElement numberToBuy;

    @FindBy(css = ".buy-now-wrap .buynow")
    private WebElement buyNowButton;

    @FindBy(className = "product-quantity-tip")
    private WebElement productNumberAvailable;

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilProductPageIsVisible () {
        waitForElement(productInfo);
    }

    public String getNumberOfProductToBuy () {
        waitUntilProductPageIsVisible();
        String numberOfProducts =  getValue(numberToBuy);
        return numberOfProducts;
    }

    public boolean isNumberOfProductGreater (Integer numberOfProduct) {
        String productNumber = getText(productNumberAvailable).split(" ")[0];
        return Integer.parseInt(productNumber) > numberOfProduct;
    }

    public boolean isBuyNowButtonAvailable () throws InterruptedException {
        waitUntilProductPageIsVisible();
        switchTab(2);
        Boolean buyNow = buyNowButton.isEnabled();
        return  buyNow;
    }
}
