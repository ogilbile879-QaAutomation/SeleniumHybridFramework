package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	// public WebDriver driver;

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		logger.info("******Starting TC001_AccountRegistrationTest******* ");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on Home page My Account link.");
			hp.clickRegisterLnk();
			logger.info("Clicked on my Account Register Link.");

			AccountRegistrationPage repage = new AccountRegistrationPage(driver);
			logger.info("***Prividing Customer Info***");

			repage.setFirstName("Selenium");
			repage.setlastName("Automation");
			repage.setEmail("SeleniumAutomation" + randomString() + "@mailinator.com");
			repage.setTelephone("9292929292");
			repage.setPassword("Qofx123!");
			repage.setConfirmpassword("Qofx123!");
			repage.setPrivacyPolicy();
			repage.clickContinue();
			logger.info("***validating expected message***");

			String confmsg = repage.getConfirmationMsg();

			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test Failed...");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
				
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC001_AccountRegistrationTest*****");
	}

}
