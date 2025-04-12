package StepDefinations;

import PageObjects.SP500_Index_page;
import Utilities.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class SP500_Index_StepDef extends BaseClass {

    public SP500_Index_page indexHistoryPage = new SP500_Index_page(driver);


    @Given("User is on Home page and clicks index finder")
    public void user_is_on_home_page_and_clicks_index_finder() throws InterruptedException {
        indexHistoryPage.clickIndexFinder();
    }

    @Given("User clicks on Search box and search for {string} index")
    public void user_clicks_on_search_box_and_search_for_index(String IndexName)  {
        indexHistoryPage.indexSearch(IndexName);
    }

    @Then("User Should find {string} Index Page")
    public void user_should_find_index_page(String IndexPage) throws InterruptedException {
        indexHistoryPage.waitForPageLoad();
        indexHistoryPage.clickindexname();
        indexHistoryPage.clickDetails_data();
        indexHistoryPage.Switch_to_Index_window(IndexPage);
    }


    @When("User select graph view type")
    public void user_select_graph_view_type() {
        indexHistoryPage.clickgraph_view();

    }

    @When("User Clicks on time period in series")
    public void user_clicks_on_time_period_in_series() {
        indexHistoryPage.get_timePeriod_list();

    }

    @Then("User get change price value")
    public void user_get_change_price_value() {
        indexHistoryPage.get_Changeprice_percentage();
    }

}

