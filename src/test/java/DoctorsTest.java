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
  public void all_returnsAlphabetizedDoctors() {
    Doctors firstDoctor= new Doctors("Dr. John Doe");
    firstDoctor.save();
    Doctors secondDoctor = new Doctors("Dr. Fred Doe");
    secondDoctor.save();
    assertEquals(secondDoctor, Doctors.all().get(0));
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Doctors firstDoctor= new Doctors("Dr. John Doe");
    Doctors secondDoctor = new Doctors("Dr. John Doe");
    assertEquals(true, firstDoctor.equals(secondDoctor));
  }

  @Test
  public void getName_returnsCorrectName(){
    Doctors newDoctor = new Doctors("Dr. John Doe");
    assertEquals("Dr. John Doe", newDoctor.getName());
  }


  @Test
  public void getSpecialty_returnsCorrectSpecialty(){
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.addSpecialty(1);
    assertEquals(1, newDoctor.getSpecialty());
  }

  @Test
  public void getPatients_returnsCorrectPatients() {
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.save();
    Patient newPatient = new Patient("Joe Shmoe", "July 23, 1990");
    newPatient.save();
    newPatient.addDoctor(newDoctor.getId());
    assertEquals(newPatient, newDoctor.getPatients().get(0));
  }

  @Test
  public void getNumberOfPatients_returnsCorrectNumber() {
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.save();
    Patient firstPatient = new Patient("Joe Shmoe", "July 23, 1990");
    firstPatient.save();
    firstPatient.addDoctor(newDoctor.getId());
    Patient secondPatient = new Patient("Jane Shmoe", "March 17, 1990");
    secondPatient.save();
    secondPatient.addDoctor(newDoctor.getId());
    assertEquals(2, newDoctor.getNumberOfPatients());
  }

  @Test
  public void save_returnTrueIfSaved(){
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.save();
    assertTrue(Doctors.all().get(0).equals(newDoctor));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.save();
    assertEquals(Doctors.all().get(0).getId(), newDoctor.getId());
  }

  @Test
  public void find_findsCorrectId(){
    Doctors newDoctor = new Doctors("Dr. John Doe");
    newDoctor.save();
    Doctors savedDoctor = Doctors.find(newDoctor.getId());
    assertEquals(savedDoctor, newDoctor);
  }


}
