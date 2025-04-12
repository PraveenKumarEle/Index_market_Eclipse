package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.*;


public class SP500_Index_page {

    // By locators
    private By IndexFinder = By.xpath("(//a[@href='/spdji/en/index-finder/'])[1]");
    private By indexname = By.xpath("//a[@indexname='S&P 500®']");
    private By Details_data = By.xpath("(//a[contains(@href, '/spdji/en/indices/equity/sp-500')])[3]");
    private String url_SP_500 = "https://www.spglobal.com/spdji/en/indices/equity/sp-500/#overview";
    private By searchbox = By.xpath("//input[@placeholder='SEARCH INDICES BY KEYWORD']");
    private By searchbox_btn = By.xpath("//span[@class='search-input-btn']");
    private By graph_view = By.xpath("//li[@title='Graph View']");
    private By changeprice_percentage = By.xpath("//div[contains (@class, 'change price-return-percentage')]");
    private By time_list = By.xpath("//ul[@class='time-navigator secondary-nav']/li");


    public Wait<WebDriver> wait ;
    public JavascriptExecutor executor ;
    public Set<String> windows = null ;
    public List<WebElement> time_period_list = null;
    public String title = "No Title";
    private WebDriver driver;

    public SP500_Index_page (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        executor = (JavascriptExecutor)driver;
    }

    public void clickIndexFinder () throws InterruptedException {
        //Thread.sleep(10000);
        WebElement indexfinder_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(IndexFinder));
        indexfinder_btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchbox));
        String indexfinderURL = driver.getCurrentUrl();
        System.out.println(indexfinderURL);
        if (indexfinderURL.equalsIgnoreCase("https://www.spglobal.com/spdji/en/index-finder/")) {
            Assert.assertEquals(indexfinderURL, "https://www.spglobal.com/spdji/en/index-finder/");
        } else {
            Assert.fail();
        }
    }


    public void indexSearch(String IndexName) {
        System.out.println(driver.getTitle());
        WebElement searchbox_index = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbox));
        executor.executeScript("arguments[0].value='"+ IndexName +"';", searchbox_index);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchbox_btn)).click();

    }

    public void clickindexname() throws InterruptedException {
        WebElement indexname_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(indexname));
        executor.executeScript("arguments[0].click();", indexname_btn);
    }

    public void clickDetails_data(){
        WebElement Details_data_btn = driver.findElement(Details_data);
        executor.executeScript("arguments[0].click();", Details_data_btn);
    }

    public void waitForPageLoad() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public void Switch_to_Index_window( String IndexPage) throws InterruptedException{
    	Thread.sleep(10000);
        String ExpectedPage = IndexPage;
        windows = driver.getWindowHandles();
        for (String window : windows) {
            title = driver.switchTo().window(window).getTitle();
            if(title.equalsIgnoreCase(ExpectedPage)) {
                System.out.println("New Page Title: " + title);
            }
        }
    }

    public void clickgraph_view(){
        WebElement graph_view_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(graph_view));
       // WebElement graph_view_btn = driver.findElement(graph_view);
        executor.executeScript("arguments[0].click();", graph_view_btn);
    }

    public List get_timePeriod_list(){
        time_period_list = driver.findElements(time_list);
        for (WebElement time_period : time_period_list) {
            time_period.click();
            WebElement change_value = driver.findElement(changeprice_percentage);
            System.out.println(time_period.getText() + " : "+ change_value.getText());
        }
        return time_period_list;
    }

    public String get_Changeprice_percentage(){
        WebElement change_value = driver.findElement(changeprice_percentage);
        return change_value.getText();
    }
}

