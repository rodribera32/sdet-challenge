package test.java.testCases.setup;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebSetup extends BaseTestWeb {

	static WebDriver prepareDevice() throws MalformedURLException {
		
		System.out.println(System.getProperty("os.name"));
		if (System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
}
