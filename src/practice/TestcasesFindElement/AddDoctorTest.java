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


public class AddDoctorTest {
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
		long timeUnit = Long.parseLong(propertyUtils.getPropertyData(PropertyKey.TIMEUNIT));
		String adminUsername =javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.ADMINUSERNAME));
		String adminPassword = javaUtils.decode(propertyUtils.getPropertyData(PropertyKey.ADMINPASSWORD));

		//To Fetch Data From Excel
		String sheetName = ExcelSheet.ADMIN.getSheetName();
		String expTestScriptName = "AddDoctorTest";
		Map<String, String> map = excleUtils.getData(expTestScriptName, sheetName);

		//launching browser
		WebDriver driver = webDriverUtils.launchApplication(browser);
		webDriverUtils.maximizeBrowser();

		WaitsUtility waitUtils = new WaitsUtility(driver);
		TakesScreenshotUtility takesScreenshotUtils= new TakesScreenshotUtility(driver);
		PopUpUtilities popUpUtils = new PopUpUtilities();

		waitUtils.waitForPageLod(timeUnit);
		webDriverUtils.getUrl(url);


		//Admin login
		driver.findElement(By.xpath("//a[@href='hms/admin']")).click();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		driver.findElement(By.name("username")).sendKeys(adminUsername);
		driver.findElement(By.name("password")).sendKeys(adminPassword);
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);



		//admin adding doctor specialization
		driver.findElement(By.xpath("//span[text()=' Doctors ']/parent::div/i")).click();
		verificationUtils.partialyVerify("page", "Doctor Specialization", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//span[text()=' Doctor Specialization ']")).click();
		driver.findElement(By.name("doctorspecilization")).sendKeys(map.get("Doctorspecilization"));
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Doctor Specialization added and Admin | Doctor Specialization", webDriverUtils.getTitle(), "Admin | Doctor Specialization", takesScreenshotUtils, javaUtils);

		//admin adding doctor
		driver.findElement(By.xpath("//span[text()=' Doctors ']/parent::div/i")).click();
		verificationUtils.partialyVerify("page", "Admin | Doctor Specialization", webDriverUtils.getTitle(), "Admin | Doctor Specialization", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//span[text()=' Add Doctor']")).click();
		verificationUtils.partialyVerify("page", "Admin | Add Doctor", webDriverUtils.getTitle(), "Admin | Add Doctor", takesScreenshotUtils, javaUtils);
		WebElement docSpe = driver.findElement(By.name("Doctorspecialization"));
		dropDownUtils.handleDropDown(docSpe, map.get("Doctorspecilization") );
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
		verificationUtils.partialyVerify("popup", "Add Doctor confirmation", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);


		//Admin modifying doctor details
		driver.findElement(By.xpath("(//i[@class='fa fa-pencil'])[last()]")).click();
		WebElement docSpe1 = driver.findElement(By.name("Doctorspecialization"));
		dropDownUtils.handleDropDown(docSpe1,map.get("NewDoctorspecilization") );
		driver.findElement(By.name("submit")).click();
		verificationUtils.partialyVerify("page", "Admin | Edit Doctor Details", webDriverUtils.getTitle(), "Admin | Edit Doctor Details", takesScreenshotUtils, javaUtils);


		//admin deleting doctor 
		driver.findElement(By.xpath("//span[text()=' Doctors ']/parent::div/i")).click();
		verificationUtils.partialyVerify("page", "Admin | Edit Doctor Details", webDriverUtils.getTitle(), "Admin | Edit Doctor Details", takesScreenshotUtils, javaUtils);
		driver.findElement(By.xpath("//span[text()=' Manage Doctors ']")).click();
		driver.findElement(By.xpath("//td[text()='"+map.get("DoctorName")+"']/following-sibling::td[2]//i[@class='fa fa-times fa fa-white']")).click();
		popUpUtils.alterAccept(driver);
		String actText = driver.findElement(By.tagName("p")).getText();
		String exptext = "data deleted !!";
		verificationUtils.partialyVerify("TC", "Doctor Details deleted", actText, exptext, takesScreenshotUtils, javaUtils);


		webDriverUtils.closeApplication();
	}
}
