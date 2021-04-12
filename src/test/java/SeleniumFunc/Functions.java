package SeleniumFunc;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.google.common.io.Files;
import PageFactory.LoginPageFactory;

public class Functions {
	static WebDriver driver;
	static int j = 1;
	LoginPageFactory pf;
	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher;
	
	public void chromeSetUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    pf = new LoginPageFactory(driver);
	    driver.get("http://demowebshop.tricentis.com");
	    pf.setlogin();
	}
	
	public void edgeSetUp() {
		System.setProperty("webdriver.edge.driver","msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    pf = new LoginPageFactory(driver);
	    driver.get("http://demowebshop.tricentis.com");
	}
	
	public void cleanUp() {
		driver.close();
	}
	
	public void invalidLogin() throws IOException, ParseException, InterruptedException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader;
		String email="",pass="";
		try {
			reader = new FileReader("Testdata.json");
			Object obj = jsonParser.parse(reader);
			JSONArray usersList = (JSONArray) obj;
			for(int i=1;i<usersList.size();i++)
			{
				JSONObject users = (JSONObject) usersList.get(i);
				JSONObject user = (JSONObject) users.get("users");
				email = (String)user.get("username");
				pass = (String)user.get("password");
				checkUser(email,pass);
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void checkUser(String email, String pass) throws InterruptedException, IOException {
			pf = new LoginPageFactory(driver);
			pf.setlogin();
			screenShot();
			pf.setEmail(email);
			pf.setPass(pass);
			screenShot();
			pf.setloginbtn();
			Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Login");
			screenShot();
			matcher = pattern.matcher(email);
			if(matcher.matches() || email.equals(""))
			{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				String error = pf.getLoginerror();
				String str = "Login was unsuccessful. Please correct the errors and try again.";
				assertEquals(error, str);
			}else {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				String inv = pf.getEmailerror();
				String str2 = "Please enter a valid email address.";
				assertEquals(inv,str2);
			}
	}
	

	public String validLogin(String rm) throws InterruptedException, IOException, ParseException {
		pf = new LoginPageFactory(driver);
		JSONParser jsonParser = new JSONParser();
		FileReader reader;
		String email="",pass="";
		try {
			reader = new FileReader("Testdata.json");
			Object obj = jsonParser.parse(reader);
			JSONArray usersList = (JSONArray) obj;
			JSONObject users = (JSONObject) usersList.get(0);
			JSONObject user = (JSONObject) users.get("users");
			email = (String)user.get("username");
			pass = (String)user.get("password");
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		matcher = pattern.matcher(email);
		if(matcher.matches() || email.equals(""))
		{
			pf.setlogin();
			screenShot();
			pf.setEmail(email);
			pf.setPass(pass);
			if(rm.equals("yes")) {
				pf.setRememberme();
			}
			screenShot();
			pf.setloginbtn();
			screenShot();
			Assert.assertEquals(driver.getTitle(), "Demo Web Shop");
			return "Valid User";
		}
	return "Invalid User";
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public void rememberMe() throws IOException {
		 // store the current session
		 Set<Cookie> cookiesInstance1 = driver.manage().getCookies();
		 System.out.println("Cookies = "+cookiesInstance1);

		 // close the web driver instance
		 driver.close();

		 // again initiate web driver and go to the same website. This will open the login page
		 driver = new ChromeDriver();
		 driver.navigate().to("http://demowebshop.tricentis.com");

		 // add the stored session in the bew web driver instance
		 for(Cookie cookie : cookiesInstance1)
		 {
		  driver.manage().addCookie(cookie);
		 }

		 // re-visit the page
		 driver.navigate().to("http://demowebshop.tricentis.com");

		 // get the current session of new web driver instance
		 Set<Cookie> cookiesInstance2 = driver.manage().getCookies();
		 System.out.println("Cookies = "+cookiesInstance2);
	}
	

	static void screenShot() throws IOException {
		File scrFile;
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile, new File("C:\\Users\\Suraj Ardak\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\Sprint2DemoWebShop\\src\\test\\resources\\ScreenShot\\step" + j + ".jpeg"));
        j++;
	}

	public void InvalidOutline(String arg1, String arg2) {
		pf = new LoginPageFactory(driver);
		pf.setEmail(arg1);
		pf.setPass(arg2);
		pf.setlogin();
		
	}
}
