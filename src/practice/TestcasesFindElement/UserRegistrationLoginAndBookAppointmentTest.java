package practice.TestcasesFindElement;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hms.nml.genericUtilities.FrameworkConstants;
import com.hms.nml.genericUtilities.JavaUtility;
import com.hms.nml.genericUtilities.VerificationUtility;
import com.hms.nml.genericUtilities.enums.ExcelSheet;
import com.hms.nml.genericUtilities.enums.ExcelSheet.PropertyKey;
import com.hms.nml.genericUtilities.externalResource.ExcelUtilities;
import com.hms.nml.genericUtilities.externalResource.PropertyUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.TakesScreenshotUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.WebDriverUtilities;
import com.hms.nml.genericUtilities.seleniumUtilities.seleniumUtilities.DropDownUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.seleniumUtilities.PopUpUtilities;
import com.hms.nml.genericUtilities.seleniumUtilities.seleniumUtilities.WaitsUtility;

import hms.nml.pageRepository.Hospital_Management_SystemPage;
import hms.nml.pageRepository.LoginPage;
import hms.nml.pageRepository.TabNames;
import hms.nml.pageRepository.patientPageRepository.BookAppointmentPage;
import hms.nml.pageRepository.patientPageRepository.RegistrationPage;
import hms.nml.pageRepository.patientPageRepository.UserCommonPage;



public class UserRegistrationLoginAndBookAppointmentTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertyUtility propertyUtils= new PropertyUtility(FrameworkConstants.TEST_PROPERTY_FILE_PATH);
		ExcelUtilities excelUtilits = new ExcelUtilities(FrameworkConstants.TEST_EXCEL_FILE_PATH);
		
		WebDriverUtilities webDriverUtils=new WebDriverUtilities();
		VerificationUtility VerificationUtils = new VerificationUtility();
		JavaUtility javaUtils= new JavaUtility();
		DropDownUtility dropdownUtils=new DropDownUtility();
		PopUpUtilities popUpUtils = new PopUpUtilities();

		
		//using commonData 
		String browser= propertyUtils.getPropertyData(PropertyKey.BROWSER);
		String url = propertyUtils.getPropertyData(PropertyKey.URL);
		String doctorUsername = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.DOCTORUSERNAME));
		String doctorPassword = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.DOCTORPASSWORD));
		long timeUnit = Long.parseLong(propertyUtils.getPropertyData(PropertyKey.TIMEUNIT));

		
		//ToFetchDataFromExcel
		String sheetName = ExcelSheet.USER.getSheetName();
		String expTestScriptName = "UserRegistrationLoginAndBookAppointmentTest";
		Map<String, String> map = excelUtilits.getData(expTestScriptName, sheetName);
		

		WebDriver driver = webDriverUtils.launchApplication(browser);
		webDriverUtils.maximizeBrowser();
		WaitsUtility waitUtils = new WaitsUtility(driver);
		TakesScreenshotUtility takesScreenshotUtils = new TakesScreenshotUtility(driver);
		waitUtils.waitForPageLod(timeUnit);
		webDriverUtils.getUrl(url);
		
		LoginPage userLogin=new LoginPage(driver);
		Hospital_Management_SystemPage hms= new Hospital_Management_SystemPage(driver);
		RegistrationPage registrationPage= new RegistrationPage(driver);
		UserCommonPage userCommonPage= new UserCommonPage(driver);
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		
		
		// User registration
		hms.patientLoginClickAction();
		//driver.findElement(By.xpath("//h3[text()='Patients']/parent::div/child::div//a")).click();
		VerificationUtils.partialyVerify("PAGE", "User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		userLogin.clickCreateAccountLinkAction();
		//driver.findElement(By.xpath("//a[contains(text(),'Create an account')]")).click();
		VerificationUtils.partialyVerify("PAGE", "User Registration", webDriverUtils.getTitle(), "User Registration", takesScreenshotUtils, javaUtils);
//		driver.findElement(By.name("full_name")).sendKeys(map.get("Name"));
//		driver.findElement(By.name("address")).sendKeys(map.get("Address"));
//		driver.findElement(By.name("city")).sendKeys(map.get("City"));
//		driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
		String userEmail = map.get("Name")+javaUtils.getRandomNumber(1000)+"@gmail.com";
//		driver.findElement(By.name("email")).sendKeys(userEmail);
//		driver.findElement(By.id("password")).sendKeys(map.get("Password"));
//		driver.findElement(By.id("password_again")).sendKeys(map.get("Password"));
//		driver.findElement(By.id("submit")).click();
		//registrationPage.userRegisteringAccountAction(map.get("Name"), map.get("Address"), map.get("City"), userEmail, map.get("Password"));
		popUpUtils.alterAccept(driver);
		VerificationUtils.partialyVerify("PAGE","User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		
		// user login
		driver.findElement(By.xpath("//a[contains(text(),'Log-in')]")).click();
		//driver.findElement(By.name("username")).sendKeys(userEmail);
		//driver.findElement(By.name("password")).sendKeys(map.get("Password"));
		//driver.findElement(By.name("submit")).click();
		userLogin.loginAction(userEmail, map.get("Password"));
		VerificationUtils.partialyVerify("page","User | Dashboard",webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);
		
		// user booking appointment
		userCommonPage.clickTab(TabNames.BOOKAPPOINTMENT);
		//driver.findElement(By.xpath("//a[contains(text(),'Book Appointment')]")).click();
		
		VerificationUtils.partialyVerify("page","User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		bookAppointmentPage.bookAppointmentAction(javaUtils,dropdownUtils, map.get("Doctorspecialization"), map.get("DoctorName"), map.get("Hour"), map.get("Minute"), map.get("Meridian"));
//		WebElement docSpe = driver.findElement(By.name("Doctorspecialization"));
//		dropdownUtils.handleDropDown(docSpe,map.get("Doctorspecialization"));
//		WebElement doc = driver.findElement(By.id("doctor"));
//		dropdownUtils.handleDropDown( map.get("DoctorName"),doc );
		//driver.findElement(By.name("appdate")).click();
		//driver.findElement(By.xpath("//td[contains(text(),'"+map.get("Date")+"')]")).click();
//		driver.findElement(By.id("timepicker1")).clear();
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).clear();
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).sendKeys(map.get("Hour"));
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).clear();
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).sendKeys(map.get("Minute"));
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']")).clear();
//		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']")).sendKeys(map.get("Meridian"));
//		driver.findElement(By.name("submit")).click();
		popUpUtils.alterAccept(driver);
		VerificationUtils.partialyVerify("page","User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);

		//user logout
		userCommonPage.clickLogoutAction();
//		driver.findElement(By.xpath("//span[@class='username']")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		VerificationUtils.partialyVerify("page","Hospital Management System",webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Doctor login
		hms.doctorLoginClickAction();
		//driver.findElement(By.xpath("//a[@href='hms/doctor/']")).click();
		VerificationUtils.partialyVerify("page","Doctor Login",webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		userLogin.loginAction(doctorUsername, doctorPassword);

//		driver.findElement(By.name("username")).sendKeys(doctorUsername);
//		driver.findElement(By.name("password")).sendKeys(doctorPassword);
//		driver.findElement(By.name("submit")).click(); 
		VerificationUtils.partialyVerify("page","Doctor | Dashboard",webDriverUtils.getTitle(), "Doctor | Dashboard", takesScreenshotUtils, javaUtils);
		userCommonPage.clickTab(TabNames.APPOINTMENTHISTORY);
		//driver.findElement(By. xpath("//a[contains(text(),'View Appointment History')]")).click(); 
		VerificationUtils.partialyVerify("page","Doctor | Appointment History",webDriverUtils.getTitle(), "Doctor | Appointment History", takesScreenshotUtils, javaUtils);
		String actualUsername = driver.findElement(By.xpath("//td[contains(text(),'"+map.get("Name")+"')]")).getText();
		String expectedUsername = map.get("Name");
		VerificationUtils.exactlyVerify("tc", "Schedule appointment displayed and ", actualUsername, expectedUsername, takesScreenshotUtils, javaUtils);
		webDriverUtils.closeApplication();

	}
}