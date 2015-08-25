import org.sql2o.*;
import java.util.List;

public class Specialty {

   private int id;
   private String specialty;

  public Specialty (String specialty) {
    this.specialty = specialty;
  }

  public int getId() {
    return id;
  }

  public String getSpecialty() {
    return specialty;
  }

  public List<Doctors> getDoctors() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors WHERE specialty_id = :id ORDER BY name ASC";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Doctors.class);
    }
  }

  @Override
  public boolean equals(Object otherSpecialtyInstance) {
    if (!(otherSpecialtyInstance instanceof Specialty)) {
      return false;
    } else {
      Specialty newSpecialtyInstance = (Specialty) otherSpecialtyInstance;
      return  this.getSpecialty().equals(newSpecialtyInstance.getSpecialty()) &&
              this.getId() == newSpecialtyInstance.getId();
    }
  }

  public static List<Specialty> all() {
    String sql = "SELECT * FROM specialties";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Specialty.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO specialties (specialty) VALUES (:specialty)";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("specialty", specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Specialty find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM specialties where id=:id";
      Specialty specialty = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Specialty.class);
      return specialty;
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM specialties WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
