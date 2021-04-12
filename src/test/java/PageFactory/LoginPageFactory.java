package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	WebDriver driver;
	
	@FindBy(linkText = "Log in")
    @CacheLookup
    WebElement login;
	
	@FindBy(name="Email")
    @CacheLookup
    WebElement email;
	
	@FindBy(name="Password")
    @CacheLookup
    WebElement pass;
	
	@FindBy(xpath = "//input[@class='button-1 login-button' and @value='Log in']")
    @CacheLookup
    WebElement loginBtn;
	
	@FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[1]/div/span")
	@CacheLookup
	WebElement loginError;
	
	@FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[2]/span/span")
	@CacheLookup
	WebElement emailError;
	
	@FindBy(name="RememberMe")
    @CacheLookup
    WebElement rememberMe;
	
	public String getEmailerror() {
		return emailError.getAttribute("innerHTML");
	}
	
	public String getLoginerror() {
		return loginError.getAttribute("innerHTML");
	}
	
	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setlogin() {
		login.click();
	}

	public WebElement getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		email.sendKeys(mail);;
	}

	public WebElement getPass() {
		return pass;
	}

	public void setPass(String pwd) {
		pass.sendKeys(pwd);;
	}

	public void setloginbtn() {
		loginBtn.click();;
	}

	public void setRememberme() {
		rememberMe.click();
	}	
}
