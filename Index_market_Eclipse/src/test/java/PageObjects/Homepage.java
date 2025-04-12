package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Homepage {

    // By locators
    private By Register_login = By.xpath("//a[@id='user-login']//label[text()='Register / Login']");
    private By loginButton = By.id("login-button");
    private By username = By.id("email");
    private By password = By.name("password");
    private By cust_name = By.xpath("//a[@id='loggedIn']//div[2]/span[text()='Praveen Kumar']");
    private By login_error = By.xpath("//p[@class='error' and text()='Invalid login credentials. Please try again.']");
    private By cookie_Accept = By.xpath("//button[contains(@id, 'accept')]");
    public Wait<WebDriver> wait ;
    public JavascriptExecutor executor ;

    private WebDriver driver;
    public Homepage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        executor = (JavascriptExecutor)driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
    public void clickSignin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Register_login));

        WebElement signinButton = driver.findElement(Register_login);
            executor.executeScript("arguments[0].click();", signinButton);

    }

    public void enterUsername (){
        //WebElement UserID = driver.findElement(username);
        //executor.executeScript("arguments[0].value='"+ "ele.shan0810@gmail.com" +"';", UserID);
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys("ele.shan0810@gmail.com");
    }

    public void enterpassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys("ele.shan0810@Spdji");
    }

    public void clickSubmit(){
        WebElement submit = driver.findElement(loginButton);
        submit.click();
    }

    public String getCustName () {

        if(driver.findElement(cookie_Accept).isDisplayed()){
            driver.findElement(cookie_Accept).click();
        }

        return wait.until(ExpectedConditions.visibilityOfElementLocated(cust_name)).getText();
    }

    public String getloginErrorMessage () {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(login_error)).getText();
    }
}

