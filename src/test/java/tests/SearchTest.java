package test.java.tests;

import io.qameta.allure.Description;
import main.java.pageObjects.ItemDetailsPage;
import main.java.pageObjects.ResultsPage;
import org.testng.Assert;
import test.java.utils.setup.BaseTestWeb;
import org.testng.annotations.Test;

public class SearchTest extends BaseTestWeb {
		
	@Test (priority = 0, description = "Search an item in aliexpress")
	@Description("Search an item in aliexpress")
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