package testcases;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Imdb_class {

	public static WebDriver driver;
	public static String vUrl,timeStamp;
	
	@BeforeTest
	public void initialization()
	{
		vUrl="http://www.imdb.com/";
		DateFormat df=new SimpleDateFormat("YYYY_MM_DD HH_MM_SS ");
		Date d=new Date();
		timeStamp=df.format(d);	
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	public void imdbTest() throws IOException
	{
		try {
	
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/Browserexe/chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(vUrl);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			driver.findElement(By.linkText("Top Rated Movies")).click();
			
			
			 Connection c = null;
			    Statement stmt = null;
			  
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:test.db");
			      c.setAutoCommit(false);
			      System.out.println("Opened database successfully");
	     	
	     	
			
			for (int i = 1; i <=250; i++) {
				
					
				
				String movie_strings=driver.findElement(By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr["+i+"]/td[2]")).getText();
				System.out.println(movie_strings);
				String movie[]=movie_strings.split("\\(");
				String movie_rank[]=movie[0].split("\\.");
				String rank=movie_rank[0];
				System.out.println("Rank: "+rank);
				String movie_name=movie_rank[1].substring(1);
				System.out.println("Movie: "+movie_name);
				
				String year=movie[1].substring(0, 4);
				System.out.println("Year: "+year);
				String rating=driver.findElement(By.xpath(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
				System.out.println("Rating: "+rating);
				
				 
				int iRank=Integer.parseInt(rank);  
				int iYear=Integer.parseInt(year);
				float iRating=Float.parseFloat(rating);
				
				if(movie_name.contains("'")){
					String movie_name1=movie_name.replace("'" ," ");
					movie_name=movie_name1;
				}
				System.out.println(iRank+" "+iYear+" "+rating+" "+movie_name);
				System.out.println("==================================");
			      stmt = c.createStatement();
			      String sql = "INSERT INTO IMDB_MOVIE (RANK,NAME,YEAR,RATING) " +
			                   "VALUES ("+iRank+", '"+movie_name+"', "+iYear+", "+iRating+");"; 
			      	stmt.executeUpdate(sql);
			      	 stmt.close();
				     
	
				
				 
		      
			}
			c.commit();
		      c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getScreenshots();
			e.printStackTrace();
		}
		
		
	  }

	
	public void getScreenshots() throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    //The below method will save the screen shot in d drive with name "screenshot.png"
	       FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots"+"__"+timeStamp+".jpg"));
	}
	
	}
	

	
	

