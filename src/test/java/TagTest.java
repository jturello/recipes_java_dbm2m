import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Tag.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTitlesAreTheSame() {
    Tag firstTag = new Tag("Mexican");
    Tag secondTag = new Tag("Mexican");
    assertTrue(firstTag.equals(secondTag));
  }

  @Test
  public void save_addsInstanceOfTagToDatabase() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Tag savedTag = Tag.all().get(0);
    assertTrue(newTag.equals(savedTag));
  }

  @Test
  public void save_assignsIdToObject() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Tag savedTag = Tag.all().get(0);
    assertEquals(newTag.getId(), savedTag.getId());
  }

  @Test
  public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Tag savedTag = Tag.find(newTag.getId());
    assertTrue(newTag.equals(savedTag));
  }

  @Test
  public void update_updatesTitleOfObject() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    newTag.update("Italian");
    assertEquals(Tag.all().get(0).getTitle(), ("Italian"));
  }

  @Test
  public void delete_deleteTagObject() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    newTag.delete();
    assertEquals(Tag.all().size(), 0);
  }

  @Test
  public void addRecipe_addsRecipeToTag() {
    Tag newTag = new Tag("Mexican");
    newTag.save();

    Recipe newRecipe = new Recipe("Enchilada");
    newRecipe.save();

    newTag.addRecipe(newRecipe);
    Recipe savedRecipe = newTag.getRecipes().get(0);
    assertTrue(newRecipe.equals(savedRecipe));
  }

  @Test
  public void getRecipe_getsTagRecipeByTagID() {
    Tag newTag = new Tag("Mexican");
    newTag.save();

    Recipe newRecipe = new Recipe("Tacos");
    newRecipe.save();

    newTag.addRecipe(newRecipe);
    List savedRecipe = newTag.getRecipes();
    assertEquals(savedRecipe.size(), 1);
  }

  @Test
  public void deleteAll_deletesAllTags() {
      Tag firstTag = new Tag("BLT");
      Tag secondTag = new Tag("Taco");
      firstTag.save();
      secondTag.save();
      Recipe firstTagRecipe = new Recipe("firstRecipe");
      firstTag.addRecipe(firstTagRecipe);
      Tag.deleteAll();
      assertEquals(Tag.all().size(), 0);
  }

  @Test
  public void deleteAll_deletesAllRecipeTagAssociations() {
      Tag firstTag = new Tag("BLT");
      Tag secondTag = new Tag("Taco");
      firstTag.save();
      secondTag.save();
      Recipe firstTagRecipe = new Recipe("firstRecipe");
      firstTag.addRecipe(firstTagRecipe);
      Tag.deleteAll();
      assertEquals(firstTagRecipe.getTags().size(), 0);
  }


}
