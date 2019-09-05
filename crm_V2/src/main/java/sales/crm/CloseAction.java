package sales.crm;

import org.openqa.selenium.WebDriver;

public class CloseAction extends CRMLoginLogout{

	public static WebDriver closingActions(WebDriver driver, String path)
	{
		CheckResult.takeSnapShot(driver, path);
		driver.close();
		driver=null;
		return driver;
	}
	
}
