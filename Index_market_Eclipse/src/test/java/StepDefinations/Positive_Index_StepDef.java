package StepDefinations;

import PageObjects.Positive_Index_Listing_page;
import Utilities.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Positive_Index_StepDef extends BaseClass {

	public Positive_Index_Listing_page pilp = new Positive_Index_Listing_page(driver);
	
	@Given("User is on Home page")
	public void user_is_on_home_page() {
		pilp.waitForPageLoad();
	    pilp.getCurrentURL();
	    
	}

	@When("User finds listed index with Percentage value")
	public void user_finds_listed_index_with_percentage_value() throws InterruptedException {
		pilp.getAllIndexList();
	    
	}


	@Then("User print the positive listed indexes finally")
	public void user_print_the_positive_listed_indexes_finally() {
		pilp.segregate_index();
	}
}
