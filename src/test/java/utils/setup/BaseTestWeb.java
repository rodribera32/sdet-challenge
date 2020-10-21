package test.java.utils.setup;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import main.java.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTestWeb {

	public static WebDriver driver;
	protected WebDriverWait wait;
	public HomePage homePage;

	@Parameters({ "browserType", "appURL" })
	@BeforeSuite
	public void setUp(String browserType, String appURL) throws MalformedURLException, InterruptedException, FileNotFoundException {
		WebSetup.prepareDevice(browserType);
		driver.navigate().to(appURL);
		homePage = new HomePage(driver);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
