import org.sql2o.*;
import java.util.List;

public class Patient {

   private int id;
   private String name;
   private String birthday;
   private int doctor_id;

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

  public void addDoctor(int doctor_id){
    this.doctor_id = doctor_id;
    String sql = "UPDATE patients SET doctor_id = :doctor_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("doctor_id", doctor_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getDoctorId() {
    return doctor_id;
  }

  @Override
  public boolean equals(Object otherPatientInstance) {
    if (!(otherPatientInstance instanceof Patient)) {
      return false;
    } else {
      Patient newPatientInstance = (Patient) otherPatientInstance;
      return this.getName().equals(newPatientInstance.getName()) &&
              this.getBirthday().equals(newPatientInstance.getBirthday()) &&
              this.getDoctorId() == newPatientInstance.getDoctorId() &&
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
      String sql = "INSERT INTO patients (name, birthday, doctor_id) VALUES (:name, :birthday, :doctor_id)";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("birthday", birthday)
        .addParameter("doctor_id", doctor_id)
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
