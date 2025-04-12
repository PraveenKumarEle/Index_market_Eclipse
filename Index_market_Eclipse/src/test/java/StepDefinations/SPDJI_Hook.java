package StepDefinations;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

import Utilities.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class SPDJI_Hook 
{
	BaseClass bc;
	
	@Before
	public void DriverIntialisation()
	{
		bc=new BaseClass();
		bc.Setup();
		
	}

	@After
	public void AfterCompletionOfScenarios(Scenario scenario) throws IOException
	{
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new 
				Date());
	String	ScenarioName=scenario.getName().replaceAll(" ", "_")+timeStamp;
	if(scenario.isFailed())
	{
		TakesScreenshot ts=(TakesScreenshot)BaseClass.driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File tgt=new File("C:/Users/Praveen Kumar Ele/eclipse-workspace/TestProject/ScreenShots/"+ScenarioName+".jpg");
		Files.copy(src, tgt);
		
	}
	BaseClass.driver.quit();	
	}

}
