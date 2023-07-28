package ai.iamneo.runner;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import com.aventstack.extentreports.ExtentTest;

import ai.iamneo.utils.EventHandler;

public class Base {
	
	WebDriver driver;
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	public Properties prop;
	public Properties dataProp;
	
	public Base() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"/src/main/java/ai/iamneo/config/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver openBrowser(ExtentTest test) throws MalformedURLException {
		String browserName = "chrome";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		if(browserName.equalsIgnoreCase("chrome")) {			
			try {
				driver = new RemoteWebDriver(new URL("http://34.150.207.180:443"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Base.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Base.PAGE_LOAD_TIME));
		
		WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return driver;
	}

	public void closeBrowser() {
		driver.close();
	}

	public void navigateToUrl(ExtentTest test) {
		driver.get(prop.getProperty("url"));
	}

	public void closeAllBrowser() {
		driver.quit();
	}
}
