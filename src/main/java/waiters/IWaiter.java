package waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Интерфейс стандартного набора ожиданий
 *
 * @author Pavel Balahonov <p.balahonov@corp.mail.ru>
 */
public interface IWaiter {
  long IMPLICITLY_WAIT_SECOND = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", "5000")) / 1000;

  boolean waitForCondition(ExpectedCondition condition);
}
