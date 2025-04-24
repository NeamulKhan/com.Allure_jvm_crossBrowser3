package pageActionAllure_jvm_crossBrowser3;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.io.ByteArrayInputStream;
import io.qameta.allure.Allure;
import pageLocatorAllure_jvm_crossBrowser3.HomepageLocatorAllure_jvm_crossBrowser3;
import utilityAllure_jvm_crossBrowser3.BaseClassAllure_jvm_crossBrowser3;

public class HomepageActionAllure_jvm_crossBrowser3 extends BaseClassAllure_jvm_crossBrowser3{
	
	HomepageLocatorAllure_jvm_crossBrowser3 homepageLocatorAllure_jvm_crossBrowser3 = new HomepageLocatorAllure_jvm_crossBrowser3();
	
	
	public void slider_automatically_transitions_multiple_times() {
		
	
		  try {
		        String previousSlideText = "";
		        int transitions = 0;
		        int maxTransitions = 5;

		        while (transitions < maxTransitions) {
		            WebElement activeSlide = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.cssSelector(".slick-slide.slick-current.slick-active img")
		            ));

		            String currentSlideText = activeSlide.getAttribute("alt").trim();

		            if (!currentSlideText.equals(previousSlideText)) {
		                System.out.println("Slide changed: " + currentSlideText);
		                Allure.step("Slide changed: " + currentSlideText);

		               byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		               Allure.addAttachment("Slide Screenshot: " + currentSlideText, new ByteArrayInputStream(screenshot));

		                previousSlideText = currentSlideText;
		                transitions++;

		                // Wait until it changes to next slide
		                String finalPreviousSlideText = previousSlideText;
		                wait.until(driver -> {
		                    WebElement newSlide = driver.findElement(By.cssSelector(".slick-slide.slick-current.slick-active img"));
		                    return !newSlide.getAttribute("alt").trim().equals(finalPreviousSlideText);
		                });
		            }
		        }

		        Assert.assertTrue(transitions >= 5, "Slider did not transition the expected number of times.");
		        Allure.step("✅ Slider transitioned successfully " + transitions + " times.");

		    } catch (Exception e) {
		        e.printStackTrace();
		        Allure.step("❌ Exception occurred: " + e.getMessage());
		        Assert.fail("Exception during slider test: " + e.getMessage());
		    }
		}
	
	
	}
	


