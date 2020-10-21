package test.java.tests;

import main.java.pageObjects.ItemDetailsPage;
import main.java.pageObjects.ResultsPage;
import test.java.utils.setup.BaseTestWeb;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class SearchTest extends BaseTestWeb {
		
	@Test (priority = 0, description = "Check second product of second result page availability")
	@Description("As a Customer we want to see if the second ad from the second results page when searching for \"Iphone\" on www.aliexpress.com has at least 1 item to be bought.")
	@Parameters({ "itemToSearch" })
	private void search(String itemToSearch) throws Throwable {
		ResultsPage resultsPage = homePage.searchItem(itemToSearch);
		ItemDetailsPage itemDetailsPage =
				resultsPage
						.goToSpecificPage("2")
						.openItemByNumber(2);

		Assert.assertTrue(itemDetailsPage.isBuyNowButtonAvailable(), "Buy now button is not available");
		Assert.assertTrue(itemDetailsPage.isNumberOfProductGreater(1), "There is no product available");
		Assert.assertEquals(itemDetailsPage.getNumberOfProductToBuy(), "1", "Number of product displayed is wrong");
	}
}