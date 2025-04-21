package utilityAllure_jvm_crossBrowser3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassAllure_jvm_crossBrowser3 {
	
	
	public static Properties allure_jvm_crossBrowser3_prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	
	//No need input stream if we fetch data from data table and no properties file presence 
	public  BaseClassAllure_jvm_crossBrowser3 () {
				
	try {
		FileInputStream file = new FileInputStream ("src/test/resource/allure_jvm_crossBrowser3.config/Allure_jvm_crossBrowser3.properties");
		
		allure_jvm_crossBrowser3_prop = new Properties();
		allure_jvm_crossBrowser3_prop.load(file);
		
	} catch (FileNotFoundException e) {
	    System.err.println("Properties file not found: " + e.getMessage());
	}
	  catch (IOException e) {
	    System.err.println("Error loading properties file: " + e.getMessage());
	}
	}
	
	//Method to highlight element
	public void highlightElement(WebElement element, String color) {
		
	try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].setAttribute('style', arguments[0].getAttribute('style') + '; border: 3px solid " + color + " !important;')", element);
			
		js.executeScript("arguments[0].style.border='3px solid " + color + "'", element);
		
		// Wait to make it visible
	    // Thread.sleep(1000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	
	
	public  static void allure_jvm_crossBrowser3_initializeDriver() {
		
	//	String browser = crossBrowser1_prop.getProperty("Browser");
		
		String browserName = System.getProperty("browser", allure_jvm_crossBrowser3_prop.getProperty("Browser"));
		
		switch (browserName.toLowerCase()) {
		
	    case "chrome":
	        System.out.println("Initializing Chrome browser...");
	        driver = new ChromeDriver();
	        break;
	        
	    case "edge":
	        System.out.println("Initializing Edge browser...");
	        driver = new EdgeDriver();
	        break;
	        
	    default:
	        throw new IllegalStateException("Unsupported browser: " + browserName);
	}
		
			
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait here
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		}

	public static void allure_jvm_crossBrowser3_launchURL() {
	
		driver.get(allure_jvm_crossBrowser3_prop.getProperty("base.url"));
}

	/*
	 public static void closeDriver() {
	        if (driver != null) {
	            driver.quit();
	        }
	
}*/


}
