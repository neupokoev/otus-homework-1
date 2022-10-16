package pages;

import annotations.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component("title")
public class LessonPage extends AnyPageAbs<LessonPage> {

  @FindBy(css = "title")
  WebElement pageTitle;

  public LessonPage(WebDriver driver) {
    super(driver);
  }

}
