package test.java.tests;

import io.qameta.allure.Description;
import main.java.pageObjects.ItemDetailsPage;
import main.java.pageObjects.ResultsPage;
import org.testng.Assert;
import test.java.utils.setup.BaseTestWeb;
import org.testng.annotations.Test;

public class SearchTest extends BaseTestWeb {
		
	@Test (priority = 0, description = "Check second product of second result page availability")
	@Description("As a Customer we want to see if the second ad from the second results page when searching for \"Iphone\" on www.aliexpress.com has at least 1 item to be bought.")
	private void search() throws Throwable {
		ResultsPage resultsPage = homePage.searchItem("iPhone");
		ItemDetailsPage itemDetailsPage =
				resultsPage
						.goToAnotherPage("2")
						.openItemByNumber(2);

		Assert.assertTrue(itemDetailsPage.isBuyNowButtonAvailable());
		Assert.assertTrue(itemDetailsPage.isNumberOfProductGreater(1));
		Assert.assertEquals(itemDetailsPage.getNumberOfProductToBuy(), "1");
	}
}