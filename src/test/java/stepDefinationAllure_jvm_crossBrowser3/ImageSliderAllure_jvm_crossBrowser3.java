package stepDefinationAllure_jvm_crossBrowser3;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageActionAllure_jvm_crossBrowser3.HomepageActionAllure_jvm_crossBrowser3;
import utilityAllure_jvm_crossBrowser3.BaseClassAllure_jvm_crossBrowser3;

public class ImageSliderAllure_jvm_crossBrowser3 extends BaseClassAllure_jvm_crossBrowser3{
	
	HomepageActionAllure_jvm_crossBrowser3 homepageActionAllure_jvm_crossBrowser3 = new HomepageActionAllure_jvm_crossBrowser3();
	
	@Given("User is on the Always Fashion homepage")
	public void user_is_on_the_always_fashion_homepage() {
	   
		allure_jvm_crossBrowser3_launchURL();
	}

	@When("The slider automatically transitions multiple times")
	public void the_slider_automatically_transitions_multiple_times() {
	 
		homepageActionAllure_jvm_crossBrowser3.slider_automatically_transitions_multiple_times();
	}

	@Then("The slider should repeat the transitions in a loop")
	public void the_slider_should_repeat_the_transitions_in_a_loop() {
	   
		
	}


	

}
