package testCases;
/*Data is Valid -login success -test pass-logout
 * Data is Valid-login Failed-test fail
 * 
 * Data is Invalid --login success test fail-logout
 * Data is Invalid--login failed -test pass
 * 
 */

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)//Getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) {
		
	logger.info("*****Starting  TC003 Login DDT Test*****");
	try
	{
	//Home page
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLoginLnk();
	//Login Page
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPaasword(pwd);
	lp.clicklogin();
	
	//MyAccount page
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage=macc.isMyAccountPageExists();
	
	if(exp.equalsIgnoreCase("Valid")) {
		if (targetPage==true)
		{
			Assert.assertTrue(true);
			macc.clicklogout();
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(exp.equalsIgnoreCase("Invalid")) {
		if(targetPage==true) 
		{
			macc.clicklogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	}catch(Exception e) {
		Assert.fail();
	}
	
	logger.info("*****Finished TC_003 LoginDDT Test*****");
	}
	
	
}
