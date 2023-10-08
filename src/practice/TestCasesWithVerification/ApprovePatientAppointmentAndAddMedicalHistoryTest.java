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
import hms.nml.pageRepository.doctorPageRepository.AddMedicalHistoryPage;
import hms.nml.pageRepository.doctorPageRepository.AddPatientPage;
import hms.nml.pageRepository.doctorPageRepository.DoctorAppointmentHistoryPage;
import hms.nml.pageRepository.doctorPageRepository.DoctorCommonPage;
import hms.nml.pageRepository.doctorPageRepository.ManagePatientsPage;
import hms.nml.pageRepository.patientPageRepository.BookAppointmentPage;
import hms.nml.pageRepository.patientPageRepository.UserCommonPage;


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

		//POM objects
		LoginPage loginPage=new LoginPage(driver);
		Hospital_Management_SystemPage hmsHomePage= new Hospital_Management_SystemPage(driver);
		DoctorCommonPage doctorCommonPage= new DoctorCommonPage(driver);
		UserCommonPage userCommonPage= new UserCommonPage(driver);
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		DoctorAppointmentHistoryPage doctorAppointmentHistoryPage= new DoctorAppointmentHistoryPage(driver);
		AddPatientPage addPatientPage= new AddPatientPage(driver);
		ManagePatientsPage managePatientsPage = new ManagePatientsPage(driver);
		AddMedicalHistoryPage addMedicalHistoryPage = new AddMedicalHistoryPage(driver);
		
		// user login
		hmsHomePage.patientLoginClickAction();
		loginPage.loginAction(map.get("UserEmail"), map.get("UserPassword"));
		verificationUtils.partialyVerify("Page", "User loggedin and User | Dashboard", webDriverUtils.getTitle(), "User | Dashboard", takesScreenshotUtils, javaUtils);


		// user booking appointment
		userCommonPage.clickTab(TabNames.BOOKAPPOINTMENT);
		verificationUtils.partialyVerify("page","User | Book Appointment",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);
		bookAppointmentPage.bookAppointmentAction(javaUtils,dropDownUtils, map.get("Doctorspecialization"), map.get("DoctorName"), map.get("Hour"), map.get("Minute"), map.get("Meridian"));
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("popup","User Booked Appointment and Confirmation",webDriverUtils.getTitle(), "User | Book Appointment", takesScreenshotUtils, javaUtils);


		//user logout
		userCommonPage.clickLogoutAction();
		verificationUtils.partialyVerify("page","User logged out and Hospital Management System",webDriverUtils.getTitle(), "Hospital Management System", takesScreenshotUtils, javaUtils);


		//Doctor login
		hmsHomePage.doctorLoginClickAction();
		verificationUtils.partialyVerify("page", "Doctor Login", webDriverUtils.getTitle(), "Doctor Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(doctorUsername, doctorPassword);
		verificationUtils.partialyVerify("page", "Doctor logged in and Doctor | Dashboard", webDriverUtils.getTitle(), "Doctor | Dashboard", takesScreenshotUtils, javaUtils);

		
		//Doctor checking appointment history
		doctorCommonPage.clickTab(TabNames.APPOINTMENTHISTORY);
		String actualUsername = doctorAppointmentHistoryPage.getUserAppointment(map.get("UserName"));
		String expectedUsername = map.get("UserName");
		verificationUtils.partialyVerify("page", "Doctor | Appointment History", actualUsername, expectedUsername, takesScreenshotUtils, javaUtils);


		//Doctor adding patient
		doctorCommonPage.clickTab(TabNames.PATIENTS);
		doctorCommonPage.clickTab(TabNames.ADDPATIENT);
		verificationUtils.partialyVerify("page", "Doctor | Add Patient", webDriverUtils.getTitle(), "Doctor | Add Patient", takesScreenshotUtils, javaUtils);
		String patEmail= map.get("UserName")+javaUtils.getRandomNumber(1000)+"@gmail.com";
		addPatientPage.addPatientAction(excleUtils.getData("patientDetails", "Data"), patEmail);
		verificationUtils.partialyVerify("page", "Patient added and Doctor | Add Patient", webDriverUtils.getTitle(), "Doctor | Add Patient", takesScreenshotUtils, javaUtils);

		
		//Doctor adding medical details
		doctorCommonPage.clickTab(TabNames.PATIENTS);
		doctorCommonPage.clickTab(TabNames.MANAGEPATIENT);
		verificationUtils.partialyVerify("page", "Doctor | Manage Patients", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);
		managePatientsPage.clickViewIcon();
		managePatientsPage.clickaddMedicalHistoryButton();
		verificationUtils.partialyVerify("page", "Add Medical History", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);
		addMedicalHistoryPage.addMedicalHistoryAction(excleUtils.getData("medicalHistory", "Data"));
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("Tc", "Patient medication added ", webDriverUtils.getTitle(), "Doctor | Manage Patients", takesScreenshotUtils, javaUtils);

		webDriverUtils.closeApplication();
		 
	}
}

