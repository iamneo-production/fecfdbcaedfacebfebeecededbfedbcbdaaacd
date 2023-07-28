package ai.iamneo.runner;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ai.iamneo.pages.Homepage;
import ai.iamneo.utils.LoggerHandler;
import ai.iamneo.utils.Reporter;

public class Runner {

    ExtentReports reporter;
    ExtentSparkReporter sparkReporter;
    Base base = new Base();
    Homepage homePage = new Homepage();
    Logger log = LoggerHandler.log;

    @BeforeTest
    public void extentReportSetup() {
        reporter = Reporter.generateExtentReport();
    }

    @Test
    public void hoverProducts() throws MalformedURLException {
        ExtentTest test = reporter.createTest("Homepage", "Executing step 1");
        WebDriver driver = base.openBrowser(test);
        base.navigateToUrl(test);
        homePage.hoverToProducts(driver, test);
        log.info("Hovered");

        ExtentTest test2 = reporter.createTest("Homepage", "Executing step 2");
        homePage.clickBrands(driver, test2);
        log.info("Clicked MACH3");

        ExtentTest test3 = reporter.createTest("Homepage", "Executing step 3");
        homePage.clickExplore(driver, test3);
        log.info("Clicked Explore");
    }
    
    @AfterMethod
    public void closeBrowser() {
        base.closeBrowser();
    }

    @AfterTest
    public void tearDown() {
        base.closeAllBrowser();
        reporter.flush();
    }


}
