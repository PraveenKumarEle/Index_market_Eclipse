package PageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Positive_Index_Listing_page {

	// By locators
    private By Index_list = By.xpath("//div[contains(@class,'owl-item')]");
    private By Next_btn = By.xpath("//div[@class='owl-nav']//button[2]");
    public Map<String, String> Positive_listedindex = new HashMap<>();
    public Map<String, String> Negative_listedindex = new HashMap<>();
    
    public Wait<WebDriver> wait ;
    public JavascriptExecutor executor ;
    private WebDriver driver;
    
    public Positive_Index_Listing_page (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        executor = (JavascriptExecutor)driver;
    }

	public void getAllIndexList () throws InterruptedException {
		List<WebElement> All_index  = driver.findElements(Index_list);
		int index_count = All_index.size();
		System.out.println("Total Number of Index Listed : " + index_count);
		if(index_count > 0) {
			for(int i=1; i<=index_count; i++) {
				WebElement Index_Name = driver.findElement(By.xpath("//div[contains(@class,'owl-item')]["+ i +"]//li//a//div//p[1]"));
				if(Index_Name.isDisplayed()) {
					WebElement Index_percentage = driver.findElement(By.xpath("//div[contains(@class,'owl-item')]["+ i +"]//li//a//div//p/span[2]"));
					System.out.println(Index_Name.getText() + " : " + Index_percentage.getText());
					if(Index_percentage.getText().contains("-")) {
						Negative_listedindex.put(Index_Name.getText(), Index_percentage.getText());
					}else {
						Positive_listedindex.put(Index_Name.getText(), Index_percentage.getText());
					}
				}else {
					WebElement btn_next = driver.findElement(Next_btn);
					driver.findElement(Next_btn).click();
					Thread.sleep(5000);
					WebElement Index_percentage = driver.findElement(By.xpath("//div[contains(@class,'owl-item')]["+ i +"]//li//a//div//p/span[2]"));
					System.out.println(Index_Name.getText() + " : " + Index_percentage.getText());
					if(Index_percentage.getText().contains("-")) {
						Negative_listedindex.put(Index_Name.getText(), Index_percentage.getText());
					}else {
						Positive_listedindex.put(Index_Name.getText(), Index_percentage.getText());
					}
				}
				
			}
			Assert.assertEquals(true, index_count>0);
			
		}else {
			System.out.println("Index Section not Found.");
			Assert.assertEquals(true, false);
		}
	}
	
	public void segregate_index () {
		
		System.out.println("Positive value Indexes : ");
		for (Map.Entry<String, String> entry : Positive_listedindex.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println("----------------------------");
		System.out.println("Negative value Indexes : ");
		for (Map.Entry<String, String> entry : Negative_listedindex.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		if(Positive_listedindex.size() > 0 || Negative_listedindex.size() > 0) {
			
			Assert.assertTrue(Positive_listedindex.size() > 0 || Negative_listedindex.size() > 0);
		}else {
			Assert.assertTrue(Positive_listedindex.size() > 0 || Negative_listedindex.size() > 0);
		}
	}
	
	public void getCurrentURL () {
		System.out.println(driver.getCurrentUrl());
		executor.executeScript("window.scrollBy(0, 500)");
		WebElement right_arrow = driver.findElement(Next_btn);
		if(right_arrow.isDisplayed()) {
			Assert.assertTrue(right_arrow.isDisplayed());
		}else {
			Assert.assertFalse(right_arrow.isDisplayed());
		}
	}
	
    public void waitForPageLoad() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }
	
}
