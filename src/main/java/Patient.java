import org.sql2o.*;
import java.util.List;

public class Patient {

   private int id;
   private String name;
   private String birthday;

  public Patient (String name, String birthday) {
    this.name = name;
    this.birthday = birthday;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }

  @Override
  public boolean equals(Object otherPatientInstance) {
    if (!(otherPatientInstance instanceof Patient)) {
      return false;
    } else {
      Patient newPatientInstance = (Patient) otherPatientInstance;
      return this.getName().equals(newPatientInstance.getName()) &&
              this.getBirthday().equals(newPatientInstance.getBirthday()) &&
              this.getId() == newPatientInstance.getId();
    }
  }

  public static List<Patient> all() {
    String sql = "SELECT * FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO patients (name, birthday) VALUES (:name, :birthday)";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("birthday", birthday)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM patients where id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }

}
