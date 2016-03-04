import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Recipe {
  private int id;
  private String instructions;
  private String ingredients;
  private int rating;
  private String title;


  public String getInstructions() {
    return instructions;
  }

  public String getIngredients() {
    return ingredients;
  }

  public int getId() {
    return id;
  }

  public int getRating() {
    return rating;
  }

  public String getTitle() {
    return title;
  }

  public Recipe (String title, String ingredients, String instructions, int rating) {
    this.title = title;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.rating = rating;
  }

  public Recipe (String title, String ingredients, String instructions) {
    this.title = title;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.rating = -1;
  }

  public Recipe (String title, String ingredients) {
    this.title = title;
    this.ingredients = ingredients;
    this.instructions = "";
    this.rating = -1;

  }

  public Recipe (String title) {
    this.title = title;
    this.ingredients = "";
    this.instructions = "";
    this.rating = -1;

  }

  public static  List<Recipe> all() {
    String sql = "SELECT * FROM recipes ORDER BY rating DESC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Recipe.class);
    }
  }

  @Override
  public boolean equals(Object otherRecipe) {
    if (!(otherRecipe instanceof Recipe)) {
      return false;
    } else {
      Recipe newRecipe = (Recipe) otherRecipe;
      return this.getTitle().equals(newRecipe.getTitle());
    }
  }

  public void save() {
    String sql = "INSERT INTO recipes (title, ingredients, instructions, rating) VALUES (:title, :ingredients, :instructions, :rating)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", title)
        .addParameter("ingredients", ingredients)
        .addParameter("instructions", instructions)
        .addParameter("rating", rating)
        .executeUpdate()
        .getKey();
    }
  }

  public static Recipe find(int id) {
    String sql = "SELECT * FROM recipes WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Recipe recipe = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Recipe.class);
      return recipe;
    }
  }

  public void updateTitle(String title) {
    String sql ="UPDATE recipes SET title = :title WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("title", title)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateIngredients(String ingredients) {
    String sql ="UPDATE recipes SET ingredients = :ingredients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("ingredients", ingredients)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateInstructions(String instructions) {
    String sql ="UPDATE recipes SET instructions = :instructions WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("instructions", instructions)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateRating(int rating) {
    String sql ="UPDATE recipes SET rating = :rating WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("rating", rating)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateAll(String titleUpdate, String ingredientsUpdate) {
     updateTitle(titleUpdate);
     updateIngredients(ingredientsUpdate);
    //  updateInstructions(instructionsUpdate);
    //  updateRating(ratingUpdate);
    }



  public void delete() {
    String sqlJoin ="DELETE FROM recipes_tags WHERE recipe_id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .addParameter("id", id)
        .executeUpdate();
    }
    String sql ="DELETE FROM recipes WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static void deleteAll() {
    String sqlJoin ="DELETE FROM recipes_tags";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .executeUpdate();
    }
    String sql ="DELETE FROM recipes ";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .executeUpdate();
    }
  }

  public void addTag (Tag tag) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes_tags (tag_id, recipe_id) VALUES (:tag_id, :recipe_id)";
      con.createQuery(sql)
      .addParameter("recipe_id", this.getId())
      .addParameter("tag_id", tag.getId())
      .executeUpdate();
    }
  }

  public List<Tag> getTags() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT tags.* FROM tags " +
      "JOIN recipes_tags ON (tags.id = tag_id) " +
      "JOIN recipes ON (recipes.id = recipe_id) " +
      "WHERE recipes.id = :id";
      List<Tag> tags = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Tag.class);
      return tags;
    }
  }
}
