package pageLocatorAllure_jvm_crossBrowser3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityAllure_jvm_crossBrowser3.BaseClassAllure_jvm_crossBrowser3;

public class HomepageLocatorAllure_jvm_crossBrowser3 extends BaseClassAllure_jvm_crossBrowser3 {
	
	public HomepageLocatorAllure_jvm_crossBrowser3() {
		
		PageFactory.initElements(driver, this);
		
	}
	
	//@FindBy(css=".slick-slide.slick-current.slick-active img")
	//@FindBy (css=".anywhereslider-slide.slick-active")
//	public WebElement activeImage;

}
