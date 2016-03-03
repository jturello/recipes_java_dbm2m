import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class RecipeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Recipe.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Recipe firstRecipe = new Recipe("BLT");
    Recipe secondRecipe = new Recipe("BLT", "bacon, lettice, tomato");
    assertTrue(firstRecipe.equals(secondRecipe));
  }

  @Test
  public void save_addsInstanceOfRecipeToDatabase() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();
    Recipe savedRecipe = Recipe.all().get(0);
    assertTrue(newRecipe.equals(savedRecipe));
  }

  @Test
  public void save_assignsIdToObject() {
    Recipe newRecipe = new Recipe("Tacos");
    newRecipe.save();
    Recipe savedRecipe = Recipe.all().get(0);
    assertEquals(newRecipe.getId(), savedRecipe.getId());
  }

  @Test
  public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();
    Recipe savedRecipe = Recipe.find(newRecipe.getId());
    assertTrue(newRecipe.equals(savedRecipe));
  }

  @Test
  public void updateTitle_updatesTitleOfObject() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();
    newRecipe.updateTitle("Susan");
    assertEquals(Recipe.all().get(0).getTitle(), ("Susan"));
  }

  @Test
  public void updateIngredients_updatesIngredientsOfObject() {
    Recipe newRecipe = new Recipe("Sally", "Tomatoes");
    newRecipe.save();
    newRecipe.updateIngredients("1901/01/01");
    assertEquals(Recipe.all().get(0).getIngredients(), ("1901/01/01"));
  }

  @Test
  public void updateInstructions_updatesInstructionsOfObject() {
    Recipe newRecipe = new Recipe("Sally", "Tomatoes", "Bake 350");
    newRecipe.save();
    newRecipe.updateInstructions("1901/01/01");
    assertEquals(Recipe.all().get(0).getInstructions(), ("1901/01/01"));
  }

  @Test
  public void updateRating_updatesRatingOfObject() {
    Recipe newRecipe = new Recipe("Sally", "Tomatoes", "Bake 350", 1);
    newRecipe.save();
    newRecipe.updateRating(4);
    assertEquals(Recipe.all().get(0).getRating(), (4));
  }

  @Test
  public void deleteRecipe() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();
    newRecipe.delete();
    assertEquals(Recipe.all().size(), 0);
  }

  @Test
  public void addTag_addsTagToRecipe() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();

    Tag newTag = new Tag("Mexican");
    newTag.save();

    newRecipe.addTag(newTag);
    Tag savedTag = newRecipe.getTags().get(0);
    assertTrue(newTag.equals(savedTag));
  }

  @Test
  public void getTags_getsRecipesTagsByRecipeID() {
    Recipe newRecipe = new Recipe("BLT");
    newRecipe.save();

    Tag newTag = new Tag("Mexican");
    newTag.save();

    newRecipe.addTag(newTag);
    List savedTags = newRecipe.getTags();
    assertEquals(savedTags.size(), 1);
  }

  @Test
  public void deleteAll_deletesAllRecipesAndRecipeTags() {
      Recipe firstRecipe = new Recipe("BLT");
      Recipe secondRecipe = new Recipe("Taco");
      firstRecipe.save();
      secondRecipe.save();
      Tag firstRecipeTag = new Tag("firstTag");
      firstRecipe.addTag(firstRecipeTag);
      Recipe.deleteAll();
      assertEquals(Recipe.all().size(), 0);
  }

  @Test
  public void deleteAll_deletesRecipeTagAssociations () {
      Recipe firstRecipe = new Recipe("BLT");
      firstRecipe.save();
      int recipeId = firstRecipe.getId();
      Tag firstRecipeTag = new Tag("firstTag");
      firstRecipe.addTag(firstRecipeTag);
      Recipe.deleteAll();
      assertEquals(firstRecipeTag.getRecipes().size(), 0);
  }

}
