import org.sql2o.*;
import java.util.List;

public class Doctors {

   private int id;
   private String name;
   private int specialty_id;

  public Doctors (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void addSpecialty(int specialty_id){
    this.specialty_id = specialty_id;
    String sql = "UPDATE doctors SET specialty_id = :specialty_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("specialty_id", specialty_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getSpecialty() {
    return specialty_id;
  }

  public List<Patient> getPatients() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE doctor_id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Patient.class);
    }
  }

  public int getNumberOfPatients() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT COUNT(doctor_id) FROM patients WHERE doctor_id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctorsInstance) {
    if (!(otherDoctorsInstance instanceof Doctors)) {
      return false;
    } else {
      Doctors newDoctorsInstance = (Doctors) otherDoctorsInstance;
      return this.getName().equals(newDoctorsInstance.getName()) &&
              this.getSpecialty() == newDoctorsInstance.getSpecialty() &&
              this.getId() == newDoctorsInstance.getId();
    }
  }

  public static List<Doctors> all() {
    String sql = "SELECT * FROM doctors ORDER BY name ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctors.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO doctors (name, specialty_id) VALUES (:name, :specialty_id)";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("specialty_id", specialty_id)
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
