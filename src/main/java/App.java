import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/recipes", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("recipes", Recipe.all());
      model.put("template", "templates/recipes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tags", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("tags", Tag.all());
      model.put("template", "templates/tags.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/recipes/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Recipe recipe = Recipe.find(id);
      model.put("recipe", recipe);
      model.put("allTags", Tag.all());
      model.put("template", "templates/recipe.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/tags/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.params("id"));
    Tag tag = Tag.find(id);
    model.put("tag", tag);
    model.put("allRecipes", Recipe.all());
    model.put("template", "templates/tag.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    // post("/students", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String student_name = request.queryParams("student_name");
    //   String enroll_date = request.queryParams("enroll_date");
    //   Student newStudent = new Student(student_name, enroll_date);
    //   newStudent.save();
    //   response.redirect("/students");
    //   return null;
    // });
    //
    // post("/students/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int studentId = Integer.parseInt(request.queryParams("student_id"));
    //   int courseId = Integer.parseInt(request.queryParams("description"));
    //   Course course = Course.find(courseId);
    //   Student student = Student.find(studentId);
    //   student.addCourse(course);
    //   response.redirect("/students/" + studentId);
    //   return null;
    // });
    //
    // post("/students/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int studentId = Integer.parseInt(request.queryParams("studentId"));
    //   Student student = Student.find(studentId);
    //   student.delete();
    //   response.redirect("/students");
    //   return null;
    // });
    //
    //
    //
    //
    // post("/courses", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String description = request.queryParams("description");
    //   String number = request.queryParams("number");
    //   Course newCourse = new Course(description, number);
    //   newCourse.save();
    //   response.redirect("/courses");
    //   return null;
    // });
    //

    //
    // post("/courses/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int courseId = Integer.parseInt(request.queryParams("course_id"));
    //   int student_id = Integer.parseInt(request.queryParams("student_id"));
    //   Course course = Course.find(courseId);
    //   Student student = Student.find(student_id);
    //   course.addStudent(student);
    //   response.redirect("/courses/" + courseId);
    //   return null;
    // });
  }

  //public static 'Returntype' 'FuncName' (Paramtype param) {}  //first business logic function

}
