package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	//Inheritance by using super keyword calling parent class constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	

	
@FindBy(xpath="//a[@title=\"My Account\"]")
WebElement MyAccountLnk;

@FindBy(xpath="//a[text()=\"Register\"]")
WebElement RegisterLnk;

@FindBy(xpath="//li[@class=\"dropdown open\"]//a[text()=\"Login\"]")
WebElement linkLogin;
	
public void clickMyAccount() {
	MyAccountLnk.click();
}

public void clickRegisterLnk() {
	RegisterLnk.click();
}

public void clickLoginLnk() {
	linkLogin.click();
}
}
