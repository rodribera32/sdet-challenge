package test.java.utils.setup;

import java.net.MalformedURLException;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebSetup extends BaseTestWeb {

	static WebDriver prepareDevice() throws MalformedURLException {
		String browser = "chrome";

		if (browser == "chrome") {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else {
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("permissions.default.desktop-notification", 1);
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(firefoxOptions);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
}
