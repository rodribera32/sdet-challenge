package test.java.testCases.setup;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTestWeb {

	public static WebDriver driver;
	protected WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws MalformedURLException, InterruptedException, FileNotFoundException {
		WebSetup.prepareDevice();
		driver.navigate().to("http://www.google.com");
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
