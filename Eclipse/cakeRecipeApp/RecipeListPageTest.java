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

public class RecipeListPageTest {
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

            if (driver.findElement(By.id("recyclerView")).isDisplayed()) {
                System.out.println("RecyclerView is displayed.");
            }

            Thread.sleep(3000);

            List<WebElement> recipeImages = driver.findElements(MobileBy.className("android.widget.ImageView"));
            if (!recipeImages.isEmpty()) {
                recipeImages.get(0).click();
                System.out.println("Clicked on the first recipe image. \n Navigated to detail page");
            } else {
                System.out.println("No recipe images found in RecyclerView.");
            }

            Thread.sleep(2000);
            WebElement recipeName = driver.findElement(MobileBy.id("com.example.cakerecipeapp:id/recipeName"));
            System.out.println("\n Recipe Title: " + recipeName.getText());

            WebElement recipeDescription = driver.findElement(MobileBy.id("com.example.cakerecipeapp:id/recipeInstructions"));
            System.out.println("\n Recipe Description: " + recipeDescription.getText());
            
            driver.findElement(By.id("saveToFavorites")).click();

            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

