package StepDefinations;

import org.testng.Assert;

import PageObjects.Homepage;
import Utilities.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginStepDef extends BaseClass {
    public Homepage homepage = new Homepage(driver);

    @Given("User is on Spdji login page")
    public void user_is_on_spdji_login_page() {
        String title = homepage.getPageTitle();

        if(title.equalsIgnoreCase("S&P Dow Jones Indices")){
            System.out.println("Page Title is: "+title);
            Assert.assertEquals("S&P Dow Jones Indices" , title);
        }else{
            System.out.println("User not on login page");
            Assert.fail();
        }
    }

    @When("User entered valid Username and Password and clicks on login")
    public void user_entered_valid_username_and_password_and_clicks_on_login() throws InterruptedException {
        homepage.clickSignin();
        homepage.enterUsername();
        homepage.enterpassword();
        homepage.clickSubmit();
    }

    @Then("User Should be logged Successfully")
    public void user_should_be_logged_successfully() {
        if(homepage.getCustName()!=null){
            System.out.println("User Logged in Successfully. Cust Name: "+homepage.getCustName());
            Assert.assertEquals("Praveen Kumar", homepage.getCustName());
        }else{
            System.out.println("Log in Failed. error: " + homepage.getloginErrorMessage());
            Assert.fail();
        }
    }


}
