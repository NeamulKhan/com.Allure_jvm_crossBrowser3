package testRunnerAllure_jvm_crossBrowser3;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilityAllure_jvm_crossBrowser3.AllureEnvironmentWriter;
import utilityAllure_jvm_crossBrowser3.BaseClassAllure_jvm_crossBrowser3;


@CucumberOptions(features= {"src/test/resource/featureFolderAllure_jvm_crossBrowser3"},
plugin={"json:target/cucumber.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
glue="stepDefinationAllure_jvm_crossBrowser3", tags= "@ImgSlider" , 

//dryRun = true,
monochrome = true,
publish = true
		)

public class TestRunnerAllure_jvm_crossBrowser3 extends AbstractTestNGCucumberTests {
	
		
	 private BaseClassAllure_jvm_crossBrowser3   test; // Create an instance variable
	 
	 
		@BeforeTest
		
		public void setUp() {
			
			
		//	AllureEnvironmentWriter.writeEnvironmentProperties();
			test = new BaseClassAllure_jvm_crossBrowser3  (); // Initialize the instance variable
	        test.allure_jvm_crossBrowser3_initializeDriver();
	     //   AllureEnvironmentWriter.writeEnvironmentProperties(); // <-- after driver initialized
	        
	        
	        
		}
		
		@AfterTest
		
		public void tearDown() {
			
		//	BaseClasscrossBrowser1.closeDriver(); // Use the static closeDriver method
			BaseClassAllure_jvm_crossBrowser3.driver.quit();
		}


}
