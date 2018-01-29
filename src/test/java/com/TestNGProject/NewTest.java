package com.TestNGProject;

import org.testng.annotations.Test;

import com.AppModule.Signin_Action;
import com.TestBase.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class NewTest extends TestBase{
	private static WebDriver driver=null;
  @Test
  @Parameters ({"username","password"})
  public void f(String username, String password) throws InterruptedException {
	  
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath(".//*[@id='loginFormContainer']/tbody/tr[1]/td/table/tbody/tr[2]/td/input")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='loginButton']/div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='enterTTMainContent']/table[1]/tbody/tr[3]/td[1]/table/tbody/tr/td[7]/nobr/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='createTasksPopup_customerSelector']/table/tbody/tr/td[2]/em/button")).click();
		driver.findElement(By.xpath("//*[contains(text(),'- New Customer -')]")).click();
		driver.findElement(By.xpath(".//*[@id='createTasksPopup_newCustomer']")).sendKeys("DemoCustomer");
		driver.findElement(By.xpath(".//*[@id='createTasksPopup_newProject']")).sendKeys("DemoProject");
		
		int count=driver.findElements(By.xpath(".//*[@id='createTasksPopup_createTasksTableContainer']/table/tbody/tr")).size();
		System.out.println(count);
		
		//
		for (int i=1;i<=count;i++) {
			
			String selectdate="01/15/2018";
			Date d=new Date(selectdate);
			SimpleDateFormat ADF=new SimpleDateFormat("MMMM/dd/YYYY");
			String newdate=ADF.format(d);
			String[] split=newdate.split("/");
			String Month=split[0];
			String day=split[1];
			String year=split[2];
			String datetext=Month+" "+year;
			System.out.println(Month+day+year);
			String firstpart=".//*[@id='createTasksPopup_createTasksTableContainer']/table/tbody/tr[";
			String secondpart="]";
			String finalpart=firstpart+i+secondpart;
			String taskname=finalpart+"/td[1]/input";
			driver.findElement(By.xpath(taskname)).sendKeys("Task"+i);
			String notneeded=finalpart+"/td[1]/following-sibling::td[2]/input";
			driver.findElement(By.xpath(notneeded)).sendKeys(i+"Hours");
			String deadline=finalpart+"/td[1]/following-sibling::td[3]/div/table/tbody/tr/td[2]/em/button";
			driver.findElement(By.xpath(deadline)).click();
          //int count1=driver.findElements(By.xpath(".//*[@class='x-date-middle']/table/tbody/tr/td[2]/em")).size();
			/*driver.findElement(By.xpath(".//*[@id='ext-gen246']/td[2]/em")).click();
			driver.findElement(By.linkText(Month)).click();
			WebElement element2=driver.findElements(By.xpath(".//*[@class='x-date-mp']/table/tbody/tr[7]/td/button[1]")).get(1);
			element2.click();*/
			//driver.findElement(By.xpath("//*contains[text(),'"+datetext+"']")).isDisplayed();
			driver.findElement(By.linkText(day)).click();	
		
		//*********	
			String typeofwotk=finalpart+"/td[1]/following-sibling::td[4]/div/table/tbody/tr/td[2]/em/button";
			driver.findElement(By.xpath(typeofwotk)).click();
			if(i==1) {
			WebElement element=driver.findElements(By.xpath(".//*[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(2);
			element.click();
			}else if(i==2){
				WebElement element=driver.findElements(By.xpath(".//*[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(12);
				element.click();

			}else if(i==3){
				WebElement element=driver.findElements(By.xpath(".//*[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(21);
				element.click();

			}
		}
  }
  @BeforeMethod
  public void beforeMethod() {
	 
	  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://online.actitime.com/areddy/login.do");
  }

  @AfterMethod
  public void afterMethod() {
	driver.quit();
  }

}
