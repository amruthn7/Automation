package practice.TestCasesWithVerification;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.WebDriver;

import com.hms.nml.genericUtilities.enums.ExcelSheet;
import com.hms.nml.genericUtilities.enums.ExcelSheet.PropertyKey;
import com.hms.nml.genericUtilities.externalResource.ExcelUtilities;
import com.hms.nml.genericUtilities.externalResource.PropertyUtility;
import com.hms.nml.genericUtilities.miscellaneous.FrameworkConstants;
import com.hms.nml.genericUtilities.miscellaneous.JavaUtility;
import com.hms.nml.genericUtilities.miscellaneous.VerificationUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.DropDownUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.PopUpUtilities;
import com.hms.nml.genericUtilities.seleniumUtilities.TakesScreenshotUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.WaitsUtility;
import com.hms.nml.genericUtilities.seleniumUtilities.WebDriverUtilities;

import hms.nml.pageRepository.Hospital_Management_SystemPage;
import hms.nml.pageRepository.LoginPage;
import hms.nml.pageRepository.TabNames;
import hms.nml.pageRepository.adminPageRepository.AddDoctorPage;
import hms.nml.pageRepository.adminPageRepository.AdminCommonPage;
import hms.nml.pageRepository.adminPageRepository.ManageDoctorPage;
import hms.nml.pageRepository.doctorPageRepository.DoctorCommonPage;
import hms.nml.pageRepository.patientPageRepository.BookAppointmentPage;
import hms.nml.pageRepository.patientPageRepository.UserCommonPage;


public class AdhocDeleteDoctorTest {

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

		//POM objects
		LoginPage loginPage=new LoginPage(driver);
		Hospital_Management_SystemPage hmsHomePage= new Hospital_Management_SystemPage(driver);
		DoctorCommonPage doctorCommonPage= new DoctorCommonPage(driver);
		UserCommonPage userCommonPage= new UserCommonPage(driver);
		AdminCommonPage adminCommonPage= new AdminCommonPage(driver);
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		AddDoctorPage addDoctorPage = new AddDoctorPage(driver);
		ManageDoctorPage manageDoctorPage= new ManageDoctorPage(driver);
		
		//Admin Login
		hmsHomePage.adminLoginClickAction();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(adminUsername, adminPassword);
		verificationUtils.partialyVerify("page", "Admin logged in and Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);

		
		//Admin adding doctor
		doctorCommonPage.clickTab(TabNames.DOCTORS);
		doctorCommonPage.clickTab(TabNames.ADDDOCTOR);
		String doctorEmail= map.get("DoctorName")+javaUtils.getRandomNumber(1000)+"@gmail.com";
		addDoctorPage.addDoctorAction(dropDownUtils,map.get("Doctorspecilization") , excleUtils.getData("doctorDetails", "Data"), doctorEmail);
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page", "Doctor added and Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);

		//Admin logout
		adminCommonPage.clickLogoutAction();
		verificationUtils.partialyVerify("page", "Admin logged out and Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//User Login
		hmsHomePage.patientLoginClickAction();
		verificationUtils.partialyVerify("page", "User-Login", webDriverUtils.getTitle(), "User-Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(userUsername, userPassword);
		verificationUtils.partialyVerify("page", "User logged in and User | Dashboard", webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);


		// user booking appointment
		userCommonPage.clickTab(TabNames.BOOKAPPOINTMENT);
		verificationUtils.partialyVerify("page","User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		bookAppointmentPage.bookAppointmentAction(javaUtils,dropDownUtils, map.get("Doctorspecilization"), map.get("DoctorName"), map.get("Hour"), map.get("Minute"), map.get("Meridian"));
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page","User booked appointment and User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);


		//user logout
		userCommonPage.clickLogoutAction();
		verificationUtils.partialyVerify("page","User logged out and Hospital Management System",webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);
		verificationUtils.partialyVerify("page", "Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Admin Login
		hmsHomePage.adminLoginClickAction();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(adminUsername, adminPassword);
		verificationUtils.partialyVerify("page", "Admin logged in and Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);

		//Admin deleting doctor
		adminCommonPage.clickTab(TabNames.DOCTORS);
		adminCommonPage.clickTab(TabNames.MANAGEDOCTORS);
		verificationUtils.partialyVerify("page", "Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);
		manageDoctorPage.clickDoctorToDelete(map.get("DoctorName"));
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("page", "Doctor Details deleted and Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);

		//admin logout
		adminCommonPage.clickLogoutAction();
		verificationUtils.partialyVerify("page", "Admin logged out and Hospital Management System", webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);

		//Doctor Login
		hmsHomePage.doctorLoginClickAction();
		verificationUtils.partialyVerify("page", "Doctor Login", webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(map.get("DoctorEmail"), map.get("DoctorPassword"));
		String actualInvalidLoginMessage = loginPage.getWarningMessage();
		verificationUtils.partialyVerify("TC", "Doctor should not logged in", actualInvalidLoginMessage, "Invalid username or password", takesScreenshotUtils, javaUtils);

		webDriverUtils.closeApplication();
		
	}
}
