package test.java.utils.setup;

import java.net.MalformedURLException;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;

public class WebSetup extends BaseTestWeb {

	static WebDriver prepareDevice(String browser) throws MalformedURLException {
		switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("permissions.default.desktop-notification", 1);
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(firefoxOptions);
				break;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
}
