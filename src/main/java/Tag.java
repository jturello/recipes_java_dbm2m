import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Tag {
  private String title;
  private int id;

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public Tag (String title) {
    this.title = title;
  }

  public static  List<Tag> all() {
    String sql = "SELECT * FROM tags";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Tag.class);
    }
  }

  @Override
  public boolean equals(Object otherTag) {
    if (!(otherTag instanceof Tag)) {
      return false;
    } else {
      Tag newTag = (Tag) otherTag;
      return this.getTitle().equals(newTag.getTitle()) &&
      this.getId() == newTag.getId();
    }
  }

  public void save() {
    String sql = "INSERT INTO tags (title) VALUES (:title)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", title)
        .executeUpdate()
        .getKey();
    }
  }
  //
  // public static Course find(int id) {
  //   String sql = "SELECT id, description, number FROM courses WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     Course course = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetchFirst(Course.class);
  //     return course;
  //   }
  // }
  //
  // public void updateDescription(String description) {
  //   String sql ="UPDATE courses SET description = :description WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("description", description)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void updateNumber(String number) {
  //   String sql ="UPDATE courses SET number = :number WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("number", number)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void delete() {
  //   String sql ="DELETE FROM courses WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void addStudent (Student student) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO students_courses (student_id, course_id) VALUES (:student_id, :course_id)";
  //     con.createQuery(sql)
  //     .addParameter("course_id", this.getId())
  //     .addParameter("student_id", student.getId())
  //     .executeUpdate();
  //   }
  // }
  //
  // public List<Student> getStudents() {
  //   try(Connection con = DB.sql2o.open()) {
  //
  //     String sql = "SELECT students.* FROM courses " +
  //     "JOIN students_courses ON (courses.id = students_courses.course_id) " +
  //     "JOIN students ON (students_courses.student_id = students.id) " +
  //     "WHERE courses.id = :id";
  //     List<Student> students = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetch(Student.class);
  //     return students;
  //   }
  // }
  }
