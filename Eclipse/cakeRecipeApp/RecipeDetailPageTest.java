package cakeRecipeApp;

import java.net.MalformedURLException; 

import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class RecipeDetailPageTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
        caps.setCapability("appPackage", "com.example.cakerecipeapp");
        caps.setCapability("appActivity", "com.example.cakerecipeapp.ui.RecipeListActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        try {
            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
            Thread.sleep(3000);
            List<WebElement> recipeImages = driver.findElements(MobileBy.className("android.widget.ImageView"));
            recipeImages.get(0).click();
            Thread.sleep(2000);
            
            WebElement recipeName = driver.findElement(MobileBy.id("com.example.cakerecipeapp:id/recipeName"));
            System.out.println("\n Recipe Title: " + recipeName.getText());
            
            Thread.sleep(2000);
            WebElement recipeDescription = driver.findElement(MobileBy.id("com.example.cakerecipeapp:id/recipeInstructions"));
            System.out.println("\n Recipe Description: " + recipeDescription.getText());
            
            Thread.sleep(2000);
            driver.findElement(By.id("saveToFavorites")).click();
            
            WebElement findText = driver.findElement(By.id("textTitle"));
            if(findText.isDisplayed()) {
            	System.out.println("Navigated to Favorite Cate Page");
            	System.out.println("Found fovorite page text");
            }
            
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

