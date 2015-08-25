import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Doctors.all().size());
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Doctors firstDoctor= new Doctors("Dr. John Doe", "Cardiology");
    Doctors secondDoctor = new Doctors("Dr. John Doe", "Cardiology");
    assertEquals(true, firstDoctor.equals(secondDoctor));
  }

  @Test
  public void getName_returnsCorrectName(){
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    assertEquals("Dr. John Doe", newDoctor.getName());
  }


  @Test
  public void getSpecialty_returnsCorrectBDay(){
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    assertEquals("Cardiology", newDoctor.getSpecialty());
  }

  @Test
  public void getPatients_returnsCorrectPatients() {
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    newDoctor.save();
    Patient newPatient = new Patient("Joe Shmoe", "July 23, 1990");
    newPatient.save();
    newPatient.addDoctor(newDoctor.getId());
    assertEquals(newPatient, newDoctor.getPatients().get(0));
  }

  @Test
  public void save_returnTrueIfSaved(){
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    newDoctor.save();
    assertTrue(Doctors.all().get(0).equals(newDoctor));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    newDoctor.save();
    assertEquals(Doctors.all().get(0).getId(), newDoctor.getId());
  }

  @Test
  public void find_findsCorrectId(){
    Doctors newDoctor = new Doctors("Dr. John Doe", "Cardiology");
    newDoctor.save();
    Doctors savedDoctor = Doctors.find(newDoctor.getId());
    assertEquals(savedDoctor, newDoctor);
  }


}
