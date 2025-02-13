package E_Commerce;


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
 
public class LoginPage {
	public static void main(String[] args) throws InterruptedException {
		 // Setup desired capabilities
       DesiredCapabilities caps = new DesiredCapabilities();
       caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
       caps.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
       caps.setCapability("appPackage", "com.example.ecommerceshoppingapp");
       caps.setCapability("appActivity", "com.example.ecommerceshoppingapp.LoginActivity");
       caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

       try {
           // Initialize the Appium driver
       	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

       	if(driver.findElement(By.id("signUpButton")).isDisplayed()) {
    		System.out.println("successfully found signup button");
    	}
    	if(driver.findElement(By.id("forgotPassword")).isDisplayed()) {
    		System.out.println("successfully found forgot password link");
    	}
    	Thread.sleep(2000);
    	
    	driver.findElement(By.id("username")).sendKeys("niranjan");
    	driver.findElement(By.id("password")).sendKeys("niranjan");
    	driver.findElement(By.id("loginButton")).click();
    	Thread.sleep(2000);
    	
        if(driver.findElement(By.id("productListView")).isDisplayed()) {
        	System.out.println("successfully login and navigated to ProductList page");
        }else {
        	System.out.println(" Navigation failed");
        }
    	
    	
    	Thread.sleep(2000);
    	List<WebElement> products = driver.findElements(By.xpath("//android.widget.ListView/android.widget.TextView")); 
    	System.out.println("Total products found: " + products.size());
    	Thread.sleep(2000);

    	if(products.size() > 3) {
    	    driver.findElement(MobileBy.AndroidUIAutomator(
    	        "new UiScrollable(new UiSelector().scrollable(true))" +
    	        ".scrollIntoView(new UiSelector().textContains(\"Kotlin\"))"
    	    ));
    	    System.out.println("Successfully scrolled!");
    	    Thread.sleep(2000);

    	    boolean isProductVisible = driver.findElement(MobileBy.AndroidUIAutomator(
    	        "new UiSelector().textContains(\"Kotlin\")")).isDisplayed();

    	    Assert.assertTrue("Scrolling failed, last product not visible.", isProductVisible);
    	    System.out.println(" last Kotlin product is visible after scrolling.");
    	}

    	Thread.sleep(2000);
//    	List<WebElement> productList= driver.findElements(By.id("productListView"));
//    	for (WebElement item : productList){
//    	     System.out.println("List Item: " + item.getText());
    	
    	Thread.sleep(2000);
		driver.findElement(By.id("sortSpinner")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Low to High\")")).click();
		
		String firstProduct = products.get(0).getText().replace(" - ", "-").trim();
		System.out.println("First product text: " + firstProduct);

		Assert.assertEquals("T-Shirt-₹20.0", firstProduct);
		System.out.println("Successfully sorted list in Low to High price order.");

    	
       	 
           driver.quit();

       } catch (MalformedURLException e) {
           e.printStackTrace();
       }

	}

 
    
}

