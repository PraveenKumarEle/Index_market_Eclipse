package Runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="",features="src/test/resources/Features",glue="StepDefinations",
plugin= {"pretty","html:target/htmlreports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends AbstractTestNGCucumberTests
{
	 /* @Override
	  
	  @DataProvider(parallel = true) public Object[][] scenarios() { return
	  super.scenarios();}*/
	  
	  
	 
}
