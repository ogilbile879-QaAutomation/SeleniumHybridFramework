package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups= {"sanity","Master"})
	public void verify_login() {
	logger.info("*****Starting  TC002 Login Test*****");
	try {
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLoginLnk();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(p.getProperty("email"));
	lp.setPaasword(p.getProperty("password"));
	lp.clicklogin();
	
	
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage=macc.isMyAccountPageExists();
	
	Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true,"Login Failed");
	}
	catch(Exception e) {
		Assert.fail();
	}
}
}