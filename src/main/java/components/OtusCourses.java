package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.LessonPage;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component(".lessons a")
public class OtusCourses extends AnyComponentAbs<OtusCourses> {

  @FindBy(css = ".lessons a")
  private List<WebElement> courses;

  @FindBy(xpath = "(//div[@class='lessons'])[1]/a")
  private List<WebElement> favoriteCourses;

  @FindBy(xpath = "(//div[@class='lessons'])[2]/a")
  private List<WebElement> regularCourses;

  @FindBy(css = ".lessons a .lessons__new-item-title")
  private List<WebElement> coursesTitles;

  public OtusCourses(WebDriver driver) {
    super(driver);
  }

  public LessonPage clickFirstCourseItem() {
    scrollToElement(courses.get(0));
    courses.get(0).click();

    return new LessonPage(driver);
  }

  public int getCoursesAmount() {
    return courses.size();
  }

  //Метод фильтр по названию курса (ищет подстроку в названии курса без учета регистра)
  public long filterCoursesNamedAs(String subStringInCourseName) {
    return courses.stream()
        .filter(item -> (item.findElement(By.cssSelector(".lessons__new-item-title")).getText().toLowerCase())
            .contains(subStringInCourseName.toLowerCase()))
        .count();
  }

  //Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
  public LessonPage chooseTheLatestPopularCourse() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru"));
    LocalDate theLatestDate = favoriteCourses.stream()
        .map(course -> {
          String dateInString = (course.findElement(By.cssSelector(".lessons__new-item-start")))
              .getText().toLowerCase().split("с ")[1] + " " + Year.now();
          return LocalDate.parse(dateInString, formatter);
        })
        .reduce((a, b) -> a.isAfter(b) ? a : b).orElse(null);//find maximum

    for (WebElement course : favoriteCourses) {
      if (course.findElement(By.cssSelector(".lessons__new-item-start")).getText().toLowerCase(Locale.forLanguageTag("ru"))
          .contains(theLatestDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale.forLanguageTag("ru"))))) {
        scrollToElement(course);
        course.click();
        return new LessonPage(driver);
      }
    }
    return null;
  }


  //Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
  public LessonPage chooseTheEarliestRegularCourse() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru"));
    LocalDate theEarliestDate = regularCourses.stream()
        .map(course -> {
          String[] splittedDate = (course.findElement(By.cssSelector(".lessons__new-item-time"))).getText()
              .toLowerCase().split(" ");
          String dateInString = splittedDate[0] + " " + splittedDate[1];
          dateInString += ((splittedDate[2].length() == 4) && splittedDate[2].matches("20[0-9]+"))
              ? splittedDate[2] : " " + Year.now();
          try {
            return LocalDate.parse(dateInString, formatter);
          } catch (Exception exception) {//ignore
          }
          return null;
        })
        .filter(Objects::nonNull)//remove null objects
        .reduce((a, b) -> a.isAfter(b) ? b : a).orElse(null);//find minimum

    for (WebElement course : regularCourses) {
      if (course.findElement(By.cssSelector(".lessons__new-item-time")).getText().toLowerCase(Locale.forLanguageTag("ru"))
          .contains(theEarliestDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale.forLanguageTag("ru"))))) {
        scrollToElement(course);
        course.click();
        return new LessonPage(driver);
      }
    }
    return null;
  }

}
