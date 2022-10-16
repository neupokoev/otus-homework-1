package ui.courses;
/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan
@EnableConfigurationProperties({
*//*    AndroidPropertyProvider.class,
    IosPropertyProvider.class,
    GridPropertyProvider.class,
    LaunchPropertyProvider.class,
    DeepLinkPropertyProvider.class*//*
})
public class ui.courses.Application {

  public static void main(String[] args) {
    new SpringApplication(ui.courses.Application.class).run(args);
  }
}
//@EnableWsContextConfiguration
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}*/
