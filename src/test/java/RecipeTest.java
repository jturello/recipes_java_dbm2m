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
  //
  // @Test
  // public void deleteStudent_deleteStudentObject() {
  //   Student newStudent = new Student("Sally", "1900/01/01");
  //   newStudent.save();
  //   newStudent.delete();
  //   assertEquals(Student.all().size(), 0);
  // }
  //
  // @Test
  // public void addCourse_addsCourseToStudent() {
  //   Student newStudent = new Student("Sally", "1900/01/01");
  //   newStudent.save();
  //
  //   Course newCourse = new Course("History", "101");
  //   newCourse.save();
  //
  //   newStudent.addCourse(newCourse);
  //   Course savedCourse = newStudent.getCourses().get(0);
  //   assertTrue(newCourse.equals(savedCourse));
  // }
  //
  // @Test
  // public void getCourses_getsStudentCoursesByStudentID() {
  //   Student newStudent = new Student("Sally", "1900/01/01");
  //   newStudent.save();
  //
  //   Course newCourse = new Course("History", "101");
  //   newCourse.save();
  //
  //   newStudent.addCourse(newCourse);
  //   List savedCourses = newStudent.getCourses();
  //   assertEquals(savedCourses.size(), 1);
  // }
}
