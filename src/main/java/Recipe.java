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
    String sql = "SELECT id, title, ingredients, instructions, rating FROM recipes ORDER BY rating DESC";
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
  //
  // public void save() {
  //   String sql = "INSERT INTO students (student_name, enroll_date) VALUES (:student_name, :enroll_date)";
  //    try(Connection con = DB.sql2o.open()) {
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("student_name", student_name)
  //       .addParameter("enroll_date", enroll_date)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static Student find(int id) {
  //   String sql = "SELECT id, student_name, enroll_date FROM students WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     Student student = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetchFirst(Student.class);
  //     return student;
  //   }
  // }
  //
  // public void updateName(String student_name) {
  //   String sql ="UPDATE students SET student_name = :student_name WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("student_name", student_name)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void updateDate(String enroll_date) {
  //   String sql ="UPDATE students SET enroll_date = :enroll_date WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("enroll_date", enroll_date)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void delete() {
  //   String sql ="DELETE FROM students WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void addCourse (Course course) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO students_courses (student_id, course_id) VALUES (:student_id, :course_id)";
  //     con.createQuery(sql)
  //     .addParameter("student_id", this.getId())
  //     .addParameter("course_id", course.getId())
  //     .executeUpdate();
  //   }
  // }
  //
  //
  //
  // public List<Course> getCourses() {
  //
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT courses.* FROM students " +
  //     "JOIN students_courses ON (students.id = students_courses.student_id) " +
  //     "JOIN courses ON (students_courses.course_id = courses.id) " +
  //     "WHERE students.id = :id";
  //     List<Course> courses = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetch(Course.class);
  //     return courses;
  //   }
  // }
}
