import org.junit.*;
import static org.junit.Assert.*;
// import anything else needed

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Patient.all().size());
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Patient firstPatient = new Patient("John Doe", "January 1, 1965");
    Patient secondPatient = new Patient("John Doe", "January 1, 1965");
    assertEquals(true, firstPatient.equals(secondPatient));
  }

  @Test
  public void getName_returnsCorrectName(){
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    assertEquals("John Doe", newPatient.getName());
  }


  @Test
  public void getBirthday_returnsCorrectBDay(){
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    assertEquals("January 1, 1965", newPatient.getBirthday());
  }

  @Test
  public void getDoctorId_returnsCorrectDoctorId(){
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.addDoctor(1);
    assertEquals(1, newPatient.getDoctorId());
  }

  @Test
  public void save_returnTrueIfSaved(){
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.save();
    assertTrue(Patient.all().get(0).equals(newPatient));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.save();
    assertEquals(Patient.all().get(0).getId(), newPatient.getId());
  }

  @Test
  public void find_findsCorrectId(){
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.save();
    Patient savedPatient = Patient.find(newPatient.getId());
    assertEquals(savedPatient, newPatient);
  }

  @Test
  public void update_updatesPatientNameInMemory() {
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.save();
    newPatient.update("Barack Obama");
    assertEquals("Barack Obama", newPatient.getName());
  }

  @Test
  public void update_updatesPatientNameInDatabase() {
    Patient newPatient = new Patient("John Doe", "January 1, 1965");
    newPatient.save();
    newPatient.update("Barack Obama");
    assertEquals("Barack Obama", Patient.all().get(0).getName());
  }

}
