package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OperaWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {
    //var driverPath = new DriverManager().SetUpDriver(config, "Latest");
    //ChromeOptions operaOptions = new ChromeOptions();
    //WebDriver driver = new ChromeDriver(ChromeDriverService.createDefaultService(driverDir, "operadriver.exe"), options);
    OperaOptions operaOptions = new OperaOptions();
    operaOptions.setCapability("marionette", true);
    //operaOptions.getExperimentalOption("w3c");
    operaOptions.addArguments("allow-elevated-browser");
    operaOptions.addArguments("--no-sandbox");
    operaOptions.addArguments("--no-first-run");
    operaOptions.addArguments("--enable-extensions");
    operaOptions.addArguments("--homepage=about:blank");
    operaOptions.addArguments("--ignore-certificate-errors");
    operaOptions.setCapability("w3c", true);
    operaOptions.addArguments("--disable-dev-shm-usage");

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.OPERA);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }
      return new OperaDriver(operaOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), operaOptions);
  }

}
