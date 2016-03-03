import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
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

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Recipe Box");
  }


  @Test
  public void addRecipe() {
    goTo("http://localhost:4567/recipes");
    fill("#recipe_name").with("Tacos");
    fill("#ingredients").with("Beef");
    submit("#addRecipe");
    assertThat(pageSource()).contains("Tacos");
    assertThat(pageSource()).contains("Beef");
  }

  @Test
  public void addTag() {
    goTo("http://localhost:4567/tags");
    fill("#tagTitle").with("History");
    submit(".btn");
    assertThat(pageSource()).contains("History");
  }

  @Test
  public void addTagToRecipe() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Recipe newRecipe = new Recipe("Tacos");
    newRecipe.save();
    String recipePath = String.format("http://localhost:4567/recipes/%d", newRecipe.getId());
    goTo(recipePath);
    assertThat(pageSource()).contains("Mexican");
    assertThat(pageSource()).contains("Tacos");
  }

  @Test
  public void addRecipeToTag() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Recipe newRecipe = new Recipe("Tacos");
    newRecipe.save();
    String tagPath = String.format("http://localhost:4567/tags/%d", newTag.getId());
    goTo(tagPath);
    assertThat(pageSource()).contains("Mexican");
    assertThat(pageSource()).contains("Tacos");
  }
  // @Test
  // public void checkThatSubmitButtonWorksOnRecipes() {
  //   goTo("http://localhost:4567/recipes");
  //   fill()
  // }


  /*
  @Test
  public void negativeNumber() {
    goTo("http://localhost:4567");
    fill("#userChange").with("-87");
    submit(".btn");
    assertThat(pageSource()).contains("Please enter a positive value");
  }
*/

}
