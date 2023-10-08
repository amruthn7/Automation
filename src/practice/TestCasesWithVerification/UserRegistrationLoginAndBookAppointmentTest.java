package practice.TestCasesWithVerification;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

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
import hms.nml.pageRepository.doctorPageRepository.DoctorAppointmentHistoryPage;
import hms.nml.pageRepository.patientPageRepository.BookAppointmentPage;
import hms.nml.pageRepository.patientPageRepository.RegistrationPage;
import hms.nml.pageRepository.patientPageRepository.UserCommonPage;


public class UserRegistrationLoginAndBookAppointmentTest  {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertyUtility propertyUtils= new PropertyUtility(FrameworkConstants.TEST_PROPERTY_FILE_PATH);
		ExcelUtilities excelUtilits = new ExcelUtilities(FrameworkConstants.TEST_EXCEL_FILE_PATH);
		
		WebDriverUtilities webDriverUtils=new WebDriverUtilities();
		VerificationUtility verificationUtils = new VerificationUtility();
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
		
		//launching browser
		WebDriver driver = webDriverUtils.launchApplication(browser);
		webDriverUtils.maximizeBrowser();
		WaitsUtility waitUtils = new WaitsUtility(driver);
		TakesScreenshotUtility takesScreenshotUtils = new TakesScreenshotUtility(driver);
		waitUtils.waitForPageLod(timeUnit);
		webDriverUtils.getUrl(url);
		
		//POM objects
		LoginPage loginPage=new LoginPage(driver);
		Hospital_Management_SystemPage hmsHomePage= new Hospital_Management_SystemPage(driver);
		RegistrationPage registrationPage= new RegistrationPage(driver);
		UserCommonPage userCommonPage= new UserCommonPage(driver);
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		DoctorAppointmentHistoryPage doctorAppointmentHistoryPage = new DoctorAppointmentHistoryPage(driver);
		
		// User registration
		hmsHomePage.patientLoginClickAction();
		verificationUtils.partialyVerify("PAGE", "User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		loginPage.clickCreateAccountLinkAction();
		verificationUtils.partialyVerify("PAGE", "User Registration", webDriverUtils.getTitle(), "User Registration", takesScreenshotUtils, javaUtils);
		String userEmail = map.get("Email")+javaUtils.getRandomNumber(1000)+"@gmail.com";
		registrationPage.userRegisteringAccountAction(excelUtilits.getData("userDetials", "Data"), userEmail);
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("POPUP", "User Registered and confirmation", webDriverUtils.getTitle(), "User Registration", takesScreenshotUtils, javaUtils);

		
		// user login
		registrationPage.clickLoginLinkAction();
		verificationUtils.partialyVerify("PAGE","User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(userEmail, map.get("Password"));
		verificationUtils.partialyVerify("page","User | Dashboard",webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);
		
		// user booking appointment
		userCommonPage.clickTab(TabNames.BOOKAPPOINTMENT);
		verificationUtils.partialyVerify("page","User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		bookAppointmentPage.bookAppointmentAction(javaUtils,dropdownUtils, map.get("Doctorspecialization"), map.get("DoctorName"), map.get("Hour"), map.get("Minute"), map.get("Meridian"));
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page","User Booked Appointment and Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);

		//user logout
		userCommonPage.clickLogoutAction();
		verificationUtils.partialyVerify("page","Hospital Management System",webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Doctor login
		hmsHomePage.doctorLoginClickAction();
		verificationUtils.partialyVerify("page","Doctor Login",webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(doctorUsername, doctorPassword);
		verificationUtils.partialyVerify("page","Doctor | Dashboard",webDriverUtils.getTitle(), "Doctor | Dashboard", takesScreenshotUtils, javaUtils);
		userCommonPage.clickTab(TabNames.APPOINTMENTHISTORY);
		verificationUtils.partialyVerify("page","Doctor | Appointment History",webDriverUtils.getTitle(), "Doctor | Appointment History", takesScreenshotUtils, javaUtils);
		String actualUsername = doctorAppointmentHistoryPage.getUserAppointment(map.get("Name"));
		String expectedUsername = map.get("Name");
		verificationUtils.exactlyVerify("tc", "Schedule appointment displayed and ", actualUsername, expectedUsername, takesScreenshotUtils, javaUtils);
		
		webDriverUtils.closeApplication();

	}
}