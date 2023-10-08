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


public class AdhocDeleteDoctorTest {
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
		String userUsername = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.USERUSERNAME));
		String userPassword = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.USERPASSWORD));
		long timeUnit = Long.parseLong(propertyUtils.getPropertyData(PropertyKey.TIMEUNIT));
		String adminUsername =javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.ADMINUSERNAME));
		String adminPassword =javaUtils.decode( propertyUtils.getPropertyData(PropertyKey.ADMINPASSWORD));

		//To Fetch Data From Excel
		String sheetName = ExcelSheet.ADMIN.getSheetName();
		String expTestScriptName = "AdhocDeleteDoctorTest";
		Map<String, String> map = excleUtils.getData(expTestScriptName, sheetName);

		//launching browser
		WebDriver driver = webDriverUtils.launchApplication(browser);
		webDriverUtils.maximizeBrowser();

		WaitsUtility waitUtils = new WaitsUtility(driver);
		TakesScreenshotUtility takesScreenshotUtils= new TakesScreenshotUtility(driver);
		PopUpUtilities popUpUtils = new PopUpUtilities();

		waitUtils.waitForPageLod(timeUnit);
		webDriverUtils.getUrl(url);

		//Admin Login
		driver.findElement(By.xpath("//a[@href='hms/admin']")).click();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(adminUsername);
		driver.findElement(By.name("password")).sendKeys(adminPassword);
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);

		
		//Admin adding doctor
		driver.findElement(By.xpath("//span[text()=' Doctors ']/parent::div/i")).click();
		verificationUtils.partialyVerify("page", "Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//span[text()=' Add Doctor']")).click();
		WebElement docSpe = driver.findElement(By.name("Doctorspecialization"));
		dropDownUtils.handleDropDown(docSpe, map.get("Doctorspecilization"));
		driver.findElement(By.name("docname")).sendKeys(map.get("DoctorName"));
		driver.findElement(By.name("clinicaddress")).sendKeys(map.get("ClinicAddress"));
		driver.findElement(By.name("docfees")).sendKeys(map.get("DoctorFees"));
		driver.findElement(By.name("doccontact")).sendKeys(map.get("DoctorContact"));
		String doctorEmail= map.get("DoctorName")+javaUtils.getRandomNumber(100)+"@gmail.com";
		driver.findElement(By.id("docemail")).sendKeys(doctorEmail);
		driver.findElement(By.name("npass")).sendKeys(map.get("DoctorPassword"));
		driver.findElement(By.name("cfpass")).sendKeys(map.get("DoctorPassword"));
		driver.findElement(By.id("submit")).click();
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page", "Doctor added and Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);

		//Admin logout
		driver.findElement(By.xpath("//span[@class='username']")).click();
		verificationUtils.partialyVerify("page", "Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		verificationUtils.partialyVerify("page", "Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//User Login
		driver.findElement(By.xpath("//h3[text()='Patients']/parent::div/child::div//a")).click();
		verificationUtils.partialyVerify("page", "User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(userUsername);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "User | Dashboard", webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);


		// user booking appointment
		driver.findElement(By.xpath("//a[contains(text(),'Book Appointment')]")).click();
		verificationUtils.partialyVerify("page", "User | Book Appointment", webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		WebElement docSpe1 = driver.findElement(By.name("Doctorspecialization"));
		dropDownUtils.handleDropDown(docSpe1, map.get("Doctorspecilization") );
		WebElement doc = driver.findElement(By.id("doctor"));
		dropDownUtils.handleDropDown(map.get("DoctorName"),doc );
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
		verificationUtils.partialyVerify("popup", "User | Book Appointment confirmation", webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);

		//user logout
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		verificationUtils.partialyVerify("page", "Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Admin Login
		driver.findElement(By.xpath("//a[@href='hms/admin']")).click();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(adminUsername);
		driver.findElement(By.name("password")).sendKeys(adminPassword);
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);

		//Admin deleting doctor
		driver.findElement(By.xpath("//span[text()=' Doctors ']/parent::div/i")).click();
		verificationUtils.partialyVerify("page", "Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//span[text()=' Manage Doctors ']")).click();
		verificationUtils.partialyVerify("page", "Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//td[text()='"+map.get("DoctorName")+"']/following-sibling::td[2]//i[@class='fa fa-times fa fa-white']")).click();
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page", "Doctor Details deleted and Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);

		//admin logout
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		verificationUtils.partialyVerify("page", "Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Doctor Login
		driver.findElement(By.xpath("//a[@href='hms/doctor/']")).click();
		verificationUtils.partialyVerify("page", "Doctor Login", webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(map.get("DoctorEmail"));
		driver.findElement(By.name("password")).sendKeys(map.get("DoctorPassword"));
		driver.findElement(By.name("submit")).click();
		String actDocDelete = driver.findElement(By.xpath("//span[text()='Invalid username or password']")).getText();
		verificationUtils.partialyVerify("TC", "Doctor should not loggedin", actDocDelete, "Invalid username or password", takesScreenshotUtils, javaUtils);

		webDriverUtils.closeApplication();
	}
}
