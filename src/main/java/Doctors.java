import org.sql2o.*;
import java.util.List;

public class Doctors {

   private int id;
   private String name;
   private String specialty;

  public Doctors (String name, String specialty) {
    this.name = name;
    this.specialty = specialty;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }

  @Override
  public boolean equals(Object otherDoctorsInstance) {
    if (!(otherDoctorsInstance instanceof Doctors)) {
      return false;
    } else {
      Doctors newDoctorsInstance = (Doctors) otherDoctorsInstance;
      return this.getName().equals(newDoctorsInstance.getName()) &&
              this.getSpecialty().equals(newDoctorsInstance.getSpecialty()) &&
              this.getId() == newDoctorsInstance.getId();
    }
  }

  public static List<Doctors> all() {
    String sql = "SELECT * FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctors.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO doctors (name, specialty) VALUES (:name, :specialty)";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("specialty", specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctors find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM doctors where id=:id";
      Doctors doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctors.class);
      return doctor;
    }
  }

}
