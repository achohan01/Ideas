package selenium;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LangaraProgramRequirements {
	WebDriver driver = null;
	WebDriverWait wait = null;
    String actualUrl, expectedUrl;
	String programName = "Computer Science";
	String degreeName = "Associate of Science Degree in Computer Science";
	@BeforeClass
	public void BeforeClass() 
	{
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();  
		driver.manage().window().maximize();
		actualUrl = "https://langara.ca/programs-and-courses/index.html"; 
	    driver.get(actualUrl);
		expectedUrl = driver.getCurrentUrl();
		Assert.assertTrue(expectedUrl.equals(actualUrl));
		//System.out.println(expectedUrl.equals(actualUrl));
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	}
	@Test(priority = 1)
	public void PullRequiredCourses() {
		wait = new WebDriverWait(driver,2);
		WebElement programname = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(programName)));
		programname.click();
		// <h1>Computer Science</h1>
		// /html/body/div[3]/div[3]/div[3]/div/div/div[1]/h1
		WebElement h1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[3]/div[3]/div/div/div[1]/h1")));
		Assert.assertTrue(h1.getText().equals(programName));
		//System.out.println("Am I at the right program requirements: "+h1.getText().equals(programName));
		// <a class="btn-box-light" href="program-curriculum.html">Program Curriculum</a>
		WebElement programcurriculum = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Program Curriculum")));
	    programcurriculum.click();
	    //<a class="accordion-title" href="#">Associate of Science Degree in Computer Science</a>
	    WebElement degreename = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(degreeName)));
	    degreename.click();
	    /*
	    <div class="course-selection-title">All of</div>
	    <div class="course-group">
	    <table class="course-details">
	    <td class="course-number">
	    <a class="course-toggler"> CPSC 1050
	    <td class="course-name">
	    <a class="course-toggler"> Introduction to Computer Science
	    <td class="course-credit">
	    <a class="course-toggler"> 3
	    */
	    /*So I want to create a list of certain courses.
	      If it's All : Just insert into a list incrementing each add
	      If it's Two of: 
	     */
	}
	@AfterClass
	public void afterClass()
	{		
		//driver.quit();	
	}
	public class Course{
		private String subject;
		private int no;
		private String title;
		private int credit;
		private String grade;
		private int gpa;		
		public Course(String subject,int no, String title,int credit,String grade, int gpa)
		{
			this.subject = subject;
			this.no = no;
			this.title = title;
			this.credit = credit;
			this.grade = grade;
			this.gpa = gpa;
		}
		public String ToString()
		{
			return this.subject+this.no+this.title+this.credit+this.grade+this.gpa;
		}
	}
	public class Courses{
		private int howManyNeedtoPass = 0;
	    private ArrayList<Course> courses = new ArrayList<Course>();
	    public Courses(Course c)
	    {
	    	courses.add(c);
	    }
	    public void IncrementPass(int size)
	    {
	    	howManyNeedtoPass+=size;
	    }
	    public Object ReturnCourses()
	    {
	    	return courses;
	    }
	    public int ReturnPassNumber()
	    {
	    	return howManyNeedtoPass;
	    }
		
	}
	
	 //Would like to pull out the information and store it into a class with Subject, No, Title, Credit, Grade, GPA. 

}
