package ui.courses;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Driver;
import components.OtusCourses;
import extensions.UIExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class Homework1Tests {

  @Driver
  public WebDriver driver;

  @BeforeEach
  public void openMainPage() {
    new MainPage(driver)
        .open()
        .closeAnnoyedCookiesBanner();
  }

  @Test
  public void findAllLessonsTest() {
    //Посчитать все курсы на главной
    int lessonsAmount = new OtusCourses(driver)
        .getCoursesAmount();

    assertThat(lessonsAmount)
        .withFailMessage("У OTUS'a большие проблемы с выбором курсов!")
        .isGreaterThan(10);
  }

  @Test
  public void findLessonsWithGivenNameTest() {
    final String subStringInCourseName = "Специализация";
    //Отфильтровать курсы по их названию
    long lessonsNamedAmount = new OtusCourses(driver)
        .filterCoursesNamedAs(subStringInCourseName);

    System.out.println("Найдено " + lessonsNamedAmount + " курсов, содержащих слово \"" + subStringInCourseName + "\"");

    assertThat(lessonsNamedAmount)
        .withFailMessage("Многие курсы OTUS'a когда-то начинались со слова \""
            + subStringInCourseName + "\"! " + "Что-то поменялось...")
        .isGreaterThan(10);
  }

  @Test
  public void findPopularCourseByTimeConditionTest() {
    //Выбрать курс из популярных, стартующий позже всех
    String pageTitle = new OtusCourses(driver)
        .chooseTheLatestPopularCourse()
        .getPageTitle();

    assertThat(pageTitle.trim())
        .withFailMessage("На момент написания теста курс назывался: %s! "
            + "Что-то поменялось...", "Выбор профессии в IT")
        .isEqualTo("Выбор профессии в IT");
  }

  @Test
  public void findRegularCourseByTimeConditionTest() {
    //Выбрать курс из Специализаций, стартующий раньше всех
    String pageTitle = new OtusCourses(driver)
        .chooseTheEarliestRegularCourse()
        .getPageTitle();

    assertThat(pageTitle.trim())
        .withFailMessage("На момент написания теста курс назывался: %s! "
            + "Что-то поменялось...", "Специальность C# Developer")
        .isEqualTo("Специальность C# Developer");
  }

}
