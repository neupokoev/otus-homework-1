package ui.courses;

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
    new OtusCourses(driver)
        .getCoursesAmount()
        .checkThatLessonsAmountIsGreaterThan(10);
  }

  @Test
  public void findLessonsWithGivenNameTest() {
    final String subStringInCourseName = "Специализация";
    //Отфильтровать курсы по их названию
    new OtusCourses(driver)
        .filterCoursesNamedAs(subStringInCourseName)
        .printFilteredCoursesTitle()
        .checkThatNamedCoursesAmountIsGreaterThan(10, subStringInCourseName);
  }

  @Test
  public void findPopularCourseByTimeConditionTest() {
    //Выбрать курс из популярных, стартующий позже всех
    new OtusCourses(driver)
        .chooseTheLatestPopularCourse()
        .printPageTitle();
  }

  @Test
  public void findRegularCourseByTimeConditionTest() {
    //Выбрать курс из Специализаций, стартующий раньше всех
    new OtusCourses(driver)
        .chooseTheEarliestRegularCourse()
        .printPageTitle();
  }

}
