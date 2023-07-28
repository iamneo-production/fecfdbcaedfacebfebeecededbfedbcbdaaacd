package ai.iamneo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import ai.iamneo.uistore.HomepageLocators;
import ai.iamneo.utils.Screenshot;

public class Homepage {

    Screenshot screenshot = new Screenshot();

    public void hoverToProducts(WebDriver driver, ExtentTest test) {
        Actions action = new Actions(driver);
        try {
            WebElement element = driver.findElement(By.xpath(HomepageLocators.products));
            action.moveToElement(element).build().perform();
            test.log(Status.PASS, "Hovered Products");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to hover products",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "hover-products"))
                            .build());
        }
    }

    public void clickBrands(WebDriver driver, ExtentTest test) {
        try {
            driver.findElement(By.xpath(HomepageLocators.mach3)).click();
            test.log(Status.PASS, "Clicked MACH3");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to click MACH3",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "click-brands"))
                            .build());
        }
    }

    public void clickExplore(WebDriver driver, ExtentTest test) {
        try {
            driver.findElement(By.xpath(HomepageLocators.explore)).click();
            test.log(Status.PASS, "Clicked Explore");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to click Explore",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "click-explore"))
                            .build());
        }
    }

}
