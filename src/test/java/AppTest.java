import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void stylistListIsDisplayedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Stylists"));
    assertThat(pageSource()).contains("Stylists List");
  }

  @Test
  public void ClientListIsDisplayedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Clients"));
    assertThat(pageSource()).contains("Clients List");
  }

  @Test
  public void SerivcesListIsDisplayedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Serivces"));
    assertThat(pageSource()).contains("Serivces List");
  }

  @Test
  public void stylistIsAddedTest() {
    goTo("http://localhost:4567/");
    fill("#first_name").with("Bob");
    fill("#last_name").with("Parr");
    submit(".btn");
    assertThat(pageSource()).contains("Bob Parr");
  } // java.lang.StackOverflowError ...???

  @Test
  public void stylistIsUpdatedTest() {
    goTo("http://localhost:4567/");
    fill("#first_name").with("Bob");
    fill("#last_name").with("Parr");
    submit(".btn");
    click("a", withText("EDIT"));
    fill("#first_name").with("Bob");
    fill("#last_name").with("Smith");
    submit(".btn");
    assertThat(pageSource()).contains("Bob Smith");
  } // java.lang.StackOverflowError ...???

  @Test
  public void clientIsAddedTest() {
    goTo("http://localhost:4567/");
    fill("#first_name").with("Bob");
    fill("#last_name").with("Parr");
    submit(".btn");
    click("a", withText("Bob Parr"));
    fill("#first_name").with("Tom");
    fill("#last_name").with("Wilson");
    fill("#service_id").with("1");
    submit(".btn");
    assertThat(pageSource()).contains("Tom Wilson");
  } // java.lang.StackOverflowError ...???

}
