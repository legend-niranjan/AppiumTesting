package testing;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class VehicleInsuranceAppTest {

	public static void main(String[] args) {

		try {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability("appPackage", "com.example.vehicleinsuranceapplication");
			capabilities.setCapability("appActivity", ".SnapActivity");

			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement loginButton = driver.findElement(By.id("btnlogin"));
			loginButton.click();

			WebElement sinupText = driver.findElement(By.id("sinupText"));
			sinupText.click();
			Thread.sleep(2000);
			WebElement registerTitle = driver.findElement(By.xpath("//android.widget.EditText[@hint='Full Name']"));

			if (registerTitle.getText().equals("Full Name")) {
				System.out.println("Successfully navigated to Register Page");
			}
			Thread.sleep(2000);
			WebElement fullname = driver.findElement(By.xpath("//android.widget.EditText[@hint='Full Name']"));
			WebElement registerUsername = driver
					.findElement(By.xpath("//android.widget.EditText[@hint='set username']"));
			WebElement registerEmail = driver.findElement(By.xpath("//android.widget.EditText[@hint='Email Address']"));
			WebElement registerPassword = driver
					.findElement(By.xpath("//android.widget.EditText[@hint='set password']"));
			WebElement registerButton = driver.findElement(By.id("register"));

			fullname.sendKeys("Niranjan Suryawanshi");
			Thread.sleep(1000);
			registerUsername.sendKeys("Niranjan");
			Thread.sleep(1000);
			registerEmail.sendKeys("niranjansurywanshi318@gmail.com");
			Thread.sleep(1000);
			registerPassword.sendKeys("Niranjan123");
			Thread.sleep(1000);
			registerButton.click();
			Thread.sleep(1000);

			System.out.println("Registration Successfull!");

			WebElement loginUsernameField = driver.findElement(By.xpath("//android.widget.EditText[@hint='username']"));
			loginUsernameField.sendKeys("Niranjan");
			Thread.sleep(1000);

			WebElement loginPasswordField = driver.findElement(By.xpath("//android.widget.EditText[@hint='password']"));
			loginPasswordField.sendKeys("Niranjan123");
			Thread.sleep(1000);

			WebElement loginButtonElement = driver.findElement(By.id("Login"));
			loginButtonElement.click();
			Thread.sleep(1000);

			WebElement dashboardElement = driver.findElement(By.id("cardView"));

			if (dashboardElement.isDisplayed()) {
				System.out.println("Login successfull!");
				System.out.println("Navigated to the DashboardActivity");
			}
			System.out.println("..................................................................");
			Thread.sleep(2000);

			// submition task
			WebElement submitClaim = driver.findElement(By.id("submitCard"));
			submitClaim.click();

			WebElement vehicleNumber = driver
					.findElement(By.xpath("//android.widget.EditText[@hint='Enter Vehicle Number']"));
			if (vehicleNumber.isDisplayed()) {
				System.out.println("Successfully navigated to submitclaim page");
			}
			vehicleNumber.sendKeys("2510");
			WebElement details = driver
					.findElement(By.xpath("//android.widget.EditText[@hint='Enter claim description']"));
			details.sendKeys("Testing");
			WebElement claimAmount = driver.findElement(By.xpath("//android.widget.EditText[@hint='Claim Amount']"));
			claimAmount.sendKeys("10000");
			Thread.sleep(2000);
			WebElement submitButton = driver.findElement(By.id("btnSubmitClaim"));
			submitButton.click();
			Thread.sleep(2000);

			String alertText = driver.switchTo().alert().getText();
			if (alertText.contains("Submit Confirmation")) {
				System.out.println("Details saved successfully!");
				System.out.println("Alert dialog is displayed with the correct title.");
			}

			// Accept the alert dialog (click "Submit")
			driver.switchTo().alert().accept();

			WebElement dashboardElement1 = driver.findElement(By.id("cardView"));
			if (dashboardElement1.isDisplayed()) {
				System.out.println("successfully saved claim and navigated to dashboard again...");
			}

			System.out.println("..................................................................");

			// history
			WebElement history = driver.findElement(By.id("historyCard"));
			history.click();

			WebElement refreshbtn = driver.findElement(By.id("btnViewClaimHistory"));
			if (refreshbtn.isDisplayed()) {
				System.out.println("Successfully navigated to History page");
			}
			refreshbtn.click();
			WebElement backbtn = driver.findElement(By.id("backbtn"));
			backbtn.click();

			WebElement dashboardElement2 = driver.findElement(By.id("cardView"));
			if (dashboardElement2.isDisplayed()) {
				System.out.println("successfully see claim history and navigated to dashboard again...");
			}
			System.out.println("..................................................................");

			// Update
			WebElement updateCard = driver.findElement(By.id("updateCard"));
			updateCard.click();

			WebElement updatebtn = driver.findElement(By.id("btnUpdateStatus"));
			if (updatebtn.isDisplayed()) {
				System.out.println("Successfully navigated to Update page!!!");
			}
			Thread.sleep(2000);
			WebElement ClaimId = driver.findElement(By.xpath("//android.widget.EditText[@hint='Enter Claim ID']"));
			ClaimId.sendKeys("1");
			WebElement claimStatus = driver
					.findElement(By.xpath("//android.widget.EditText[@hint='Enter New Status']"));
			claimStatus.sendKeys("Approved");
			WebElement updateButton = driver.findElement(By.id("btnUpdateStatus"));
			updateButton.click();

			WebElement dashboardElement3 = driver.findElement(By.id("cardView"));
			if (dashboardElement3.isDisplayed()) {
				System.out.println("successfully updated claim status and navigated to dashboard again...");
			}
			System.out.println("..................................................................");
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
