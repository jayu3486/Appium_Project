package SignUp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class SignUpAssertionInKhanAcademy {
	AndroidDriver driver;
	
	@BeforeClass
	  public void beforeClass() throws MalformedURLException, InterruptedException 
	  {	  
		  DesiredCapabilities capability = new DesiredCapabilities();
			//capability.setCapability("deviceName", "Jayshree");	
			
			capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Jayshree");
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
			capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");
			
			 driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			Thread.sleep(5000);
	  }
	
	@Test
	public void loginValidation() throws InterruptedException, IOException 
	{
		Thread.sleep(5000);
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
		Thread.sleep(3000);
        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
        
        driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc=\"Continue with Facebook\"]/android.view.ViewGroup")).click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys("jayu1234");
       
        driver.findElement(By.xpath("//*[@resource-id='m_login_password']")).sendKeys("1234");        
        
        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log In\")")).click();
        
        try {
		    String errorMessage=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().textContains(\"Incorrect password.\")")).getText();
		    System.out.println("LOGIN IS FAILED & ERROR MESSAGE IS :"+errorMessage);
		    File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(file, new File("C:\\IBM laptpo backup\\SDET training\\Appium\\ScreenShot11.jpg"));
		}catch(NoSuchElementException e)
		{
		    System.out.println("LOGIN IS SUCCESS ");
		}   
        
	}
	
	
  

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}