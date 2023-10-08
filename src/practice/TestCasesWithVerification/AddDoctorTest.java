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
import hms.nml.pageRepository.adminPageRepository.AddDoctorSpecializationPage;
import hms.nml.pageRepository.adminPageRepository.AdminCommonPage;
import hms.nml.pageRepository.adminPageRepository.ManageDoctorPage;
import hms.nml.pageRepository.doctorPageRepository.DoctorCommonPage;


public class AddDoctorTest {

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

		//POM objects
		LoginPage loginPage=new LoginPage(driver);
		Hospital_Management_SystemPage hmsHomePage= new Hospital_Management_SystemPage(driver);
		DoctorCommonPage doctorCommonPage= new DoctorCommonPage(driver);
		AdminCommonPage adminCommonPage= new AdminCommonPage(driver);
		AddDoctorPage addDoctorPage = new AddDoctorPage(driver);
		ManageDoctorPage manageDoctorPage= new ManageDoctorPage(driver);
		AddDoctorSpecializationPage addDoctorSpecializationPage= new AddDoctorSpecializationPage(driver);


		//Admin login
		hmsHomePage.adminLoginClickAction();
		verificationUtils.partialyVerify("page", "Admin-Login", webDriverUtils.getTitle(), "Admin-Login", takesScreenshotUtils, javaUtils);
		loginPage.loginAction(adminUsername, adminPassword);
		verificationUtils.partialyVerify("page", "Admin loggedin and Admin | Dashboard", webDriverUtils.getTitle(), "Admin | Dashboard", takesScreenshotUtils, javaUtils);



		//admin adding doctor specialization
		adminCommonPage.clickTab(TabNames.DOCTORS);
		doctorCommonPage.clickTab(TabNames.DOCTORSPECIALIZATION);
		verificationUtils.partialyVerify("page", "Admin | Doctor Specialization", webDriverUtils.getTitle(), "Admin | Doctor Specialization", takesScreenshotUtils, javaUtils);
		addDoctorSpecializationPage.addDoctorSpecializationAction(map.get("Doctorspecilization"));
		verificationUtils.partialyVerify("page", "Doctor Specialization added and Admin | Doctor Specialization", webDriverUtils.getTitle(), "Admin | Doctor Specialization", takesScreenshotUtils, javaUtils);

		//admin adding doctor
		adminCommonPage.clickTab(TabNames.DOCTORS);
		adminCommonPage.clickTab(TabNames.ADDDOCTOR);
		verificationUtils.partialyVerify("page", "Admin | Add Doctor", webDriverUtils.getTitle(), "Admin | Add Doctor", takesScreenshotUtils, javaUtils);
		String doctorEmail= map.get("DoctorName")+javaUtils.getRandomNumber(100)+"@gmail.com";
		addDoctorPage.addDoctorAction(dropDownUtils,map.get("Doctorspecilization") , excleUtils.getData("doctorDetails", "Data"), doctorEmail);
		popUpUtils.alterAccept(driver);
		verificationUtils.partialyVerify("popup", "Doctor added and confirmation", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);


		//Admin modifying doctor details
		manageDoctorPage.editDoctorAction(dropDownUtils, map.get("NewDoctorspecilization"));
		verificationUtils.partialyVerify("page", "Doctor details modify and Admin | Edit Doctor Details", webDriverUtils.getTitle(), "Admin | Edit Doctor Details", takesScreenshotUtils, javaUtils);


		//admin deleting doctor 
		adminCommonPage.clickTab(TabNames.DOCTORS);
		adminCommonPage.clickTab(TabNames.MANAGEDOCTORS);
		verificationUtils.partialyVerify("page", "Admin | Manage Doctors", webDriverUtils.getTitle(), "Admin | Manage Doctors", takesScreenshotUtils, javaUtils);
		manageDoctorPage.clickDoctorToDelete(map.get("DoctorName"));
		popUpUtils.alterAccept(driver);
		String actText = manageDoctorPage.getWarningText();
		String exptext = "data deleted !!";
		verificationUtils.partialyVerify("TC", "Doctor Details deleted and", actText, exptext, takesScreenshotUtils, javaUtils);


		webDriverUtils.closeApplication();
	}
}
