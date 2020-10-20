package test.java.utils.setup;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import main.java.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTestWeb {

	public static WebDriver driver;
	protected WebDriverWait wait;
	public HomePage homePage;

	@BeforeSuite
	public void setUp() throws MalformedURLException, InterruptedException, FileNotFoundException {
		WebSetup.prepareDevice();
		driver.navigate().to("http://www.aliexpress.com");
		homePage = new HomePage(driver);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
