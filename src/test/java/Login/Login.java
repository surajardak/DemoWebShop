package Login;

import org.openqa.selenium.WebDriver;

import PageFactory.LoginPageFactory;
import SeleniumFunc.Functions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {
	WebDriver driver;
	Functions f = new Functions();
	LoginPageFactory pf;
	@Given("^User is on the login page$")
	public void user_is_on_the_login_page() throws Throwable {
		f.chromeSetUp();
	}

	@When("^User enters valid email and password and click login button$")
	public void user_enters_valid_email_and_password_and_click_login_button() throws Throwable {
	    f.validLogin("no");
	}

	@Then("^User is navigated to home page to continue shopping$")
	public void user_is_navigated_to_home_page_to_continue_shopping() throws Throwable {
		f.cleanUp();
	}

	@When("^User enters an invalid Email as\"([^\"]*)\" or an invalid password as \"([^\"]*)\" and click login$")
	public void user_enters_an_invalid_Email_as_or_an_invalid_password_as_and_click_login() throws Throwable {
		f.invalidLogin();
	}

	@Then("^User should be on login page$")
	public void user_should_be_on_login_page() throws Throwable {
	    f.cleanUp();
	}
	
	@When("^User enters the valid email and password and user check remember me option$")
	public void user_enters_the_valid_email_and_password_and_user_check_remember_me_option() throws Throwable {
		f.validLogin("yes");
	}

	@Then("^login should be stored even browser is closed and opened again$")
	public void login_should_be_stored_even_browser_is_closed_and_opened_again() throws Throwable {
		f.rememberMe();
	}
	
	@When("^User enters an invalid \"([^\"]*)\" or an invalid \"([^\"]*)\" and click login$")
	public void user_enters_an_invalid_or_an_invalid_and_click_login(String arg1, String arg2) throws Throwable {
		f.InvalidOutline(arg1,arg2);
	}
}
