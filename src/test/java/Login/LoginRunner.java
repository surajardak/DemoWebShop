package Login;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin= {"pretty","json:target/cucumber-reports/Cucumber.json"},tags= {"@RememberMe"})
public class LoginRunner {

}
