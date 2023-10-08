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


public class ApprovePatientAppointmentAndAddMedicalHistoryTest {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertyUtility propertyUtils= new PropertyUtility(FrameworkConstants.TEST_PROPERTY_FILE_PATH);
		ExcelUtilities excleUtils = new ExcelUtilities(FrameworkConstants.TEST_EXCEL_FILE_PATH);
		
		WebDriverUtilities webDriverUtils = new WebDriverUtilities();
		VerificationUtility verificationUtils = new VerificationUtility();
		DropDownUtility dropDownUtils = new DropDownUtility();
		JavaUtility javaUtils= new JavaUtility();
		
		
		//using commonData 
		String browser = propertyUtils.getPropertyData(PropertyKey.BROWSER);
		String url=propertyUtils.getPropertyData(PropertyKey.URL);
		String doctorUsername =javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.DOCTORUSERNAME));
		String doctorPassword = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.DOCTORPASSWORD));
		long timeUnit = Long.parseLong(propertyUtils.getPropertyData(PropertyKey.TIMEUNIT));

		
		
		//To Fetch Data From Excel
		String sheetName = ExcelSheet.DOCTOR.getSheetName();
		String expTestScriptName = "ApprovePatientAppointmentAndAddMedicalHistoryTest";
		Map<String, String> map = excleUtils.getData(expTestScriptName, sheetName);

		
		//launching browser
		WebDriver driver = webDriverUtils.launchApplication(browser);
		webDriverUtils.maximizeBrowser();
		
		WaitsUtility waitUtils = new WaitsUtility(driver);
		TakesScreenshotUtility takesScreenshotUtils = new TakesScreenshotUtility(driver);
		PopUpUtilities popUpUtils = new PopUpUtilities();
		
		waitUtils.waitForPageLod(timeUnit);
		
		webDriverUtils.getUrl(url);
		
		

		// user login
		driver.findElement(By.xpath("//h3[text()='Patients']/parent::div/child::div//a")).click();
		verificationUtils.partialyVerify("page", "User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(map.get("UserEmail"));
		driver.findElement(By.name("password")).sendKeys(map.get("UserPassword"));
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("Page", "User | Dashboard", webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);

		// user booking appointment
		driver.findElement(By.xpath("//a[contains(text(),'Book Appointment')]")).click();
		verificationUtils.partialyVerify("page", "User | Book Appointment", webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		WebElement docSpe = driver.findElement(By.name("Doctorspecialization"));
		dropDownUtils.handleDropDown(docSpe, map.get("Doctorspecialization"));
		WebElement doc = driver.findElement(By.id("doctor"));
		dropDownUtils.handleDropDown( map.get("DoctorName"),doc);
		driver.findElement(By.name("appdate")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+map.get("Date")+"')]")).click();
		driver.findElement(By.id("timepicker1")).clear();
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).clear();
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).sendKeys(map.get("Hour"));
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).clear();
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).sendKeys(map.get("Minute"));
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']")).clear();
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']")).sendKeys(map.get("Meridian"));
		driver.findElement(By.name("submit")).click();
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("popup", "User | Book Appointment Confirmation", webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);

		//user logout
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		verificationUtils.partialyVerify("page", "Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Doctor login
		driver.findElement(By.xpath("//a[@href='hms/doctor/']")).click();
		verificationUtils.partialyVerify("page", "Doctor Login", webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(doctorUsername);
		driver.findElement(By.name("password")).sendKeys(doctorPassword);
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Doctor | Dashboard", webDriverUtils.getTitle(), "Doctor | Dashboard", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//a[contains(text(),'View Appointment History')]")).click();
		String actualUsername = driver.findElement(By.xpath("//td[contains(text(),'"+map.get("UserName")+"')]")).getText();
		String expectedUsername = map.get("UserName");
		verificationUtils.partialyVerify("page", "Doctor | Appointment History", actualUsername, expectedUsername, takesScreenshotUtils, javaUtils);


		//Doctor adding patient
		driver.findElement(By.xpath("//i[@class='icon-arrow']")).click();
		driver.findElement(By.xpath("//span[text()=' Add Patient']")).click();
		verificationUtils.partialyVerify("page", "Doctor | Add Patient", webDriverUtils.getTitle(), "Doctor | Add Patient", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("patname")).sendKeys(map.get("UserName"));
		driver.findElement(By.name("patcontact")).sendKeys(map.get("PatientContactNo"));
		String patEmail= map.get("UserName")+javaUtils.getRandomNumber(100)+"@gmail.com";
		driver.findElement(By.id("patemail")).sendKeys(patEmail);
		driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
		driver.findElement(By.name("pataddress")).sendKeys(map.get("PatientAddress"));
		driver.findElement(By.name("patage")).sendKeys(map.get("PatientAge"));
		driver.findElement(By.name("medhis")).sendKeys(map.get("MedicalHistory"));
		driver.findElement(By.id("submit")).click();
		verificationUtils.partialyVerify("page", "Patient added and Doctor | Add Patient", webDriverUtils.getTitle(), "Doctor | Add Patient", takesScreenshotUtils, javaUtils);

		//Doctor adding medical details
		driver.findElement(By.xpath("//i[@class='icon-arrow']")).click();
		driver.findElement(By.xpath("//span[text()=' Manage Patient ']")).click();
		verificationUtils.partialyVerify("page", "Doctor | Manage Patients", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("(//i[@class='fa fa-eye'])[2]")).click();
		driver.findElement(By.xpath("//button[text()='Add Medical History']")).click();
		verificationUtils.partialyVerify("page", "Add Medical History", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("bp")).sendKeys(map.get("Blood Pleasure"));
		driver.findElement(By.name("bs")).sendKeys(map.get("BloodSugar"));
		driver.findElement(By.name("weight")).sendKeys(map.get("Weight"));
		driver.findElement(By.name("temp")).sendKeys(map.get("Temperature"));
		driver.findElement(By.name("pres")).sendKeys(map.get("Precription"));
		WebElement submitBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		waitUtils.waitForElement(submitBtn, timeUnit);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("Tc", "Patient medication added ", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);

		webDriverUtils.closeApplication();
	}
}

