import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class SpecialtyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Specialty.all().size());
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Specialty firstSpecialty= new Specialty("Cardiology");
    Specialty secondSpecialty = new Specialty("Cardiology");
    assertEquals(true, firstSpecialty.equals(secondSpecialty));
  }


  @Test
  public void getSpecialty_returnsCorrectSpecialty(){
    Specialty newSpecialty = new Specialty("Cardiology");
    assertEquals("Cardiology", newSpecialty.getSpecialty());
  }

  @Test
  public void getDoctors_returnsCorrectDoctors() {
    Specialty newSpecialty = new Specialty("Cardiology");
    newSpecialty.save();
    Doctors newDoctor = new Doctors("Dr. Joe Shmoe");
    newDoctor.save();
    newDoctor.addSpecialty(newSpecialty.getId());
    assertEquals(newDoctor, newSpecialty.getDoctors().get(0));
  }

  @Test
  public void save_returnTrueIfSaved(){
    Specialty newSpecialty = new Specialty("Cardiology");
    newSpecialty.save();
    assertTrue(Specialty.all().get(0).equals(newSpecialty));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Specialty newSpecialty = new Specialty("Cardiology");
    newSpecialty.save();
    assertEquals(Specialty.all().get(0).getId(), newSpecialty.getId());
  }

  @Test
  public void find_findsCorrectId(){
    Specialty newSpecialty = new Specialty("Cardiology");
    newSpecialty.save();
    Specialty savedSpecialty = Specialty.find(newSpecialty.getId());
    assertEquals(savedSpecialty, newSpecialty);
  }


}
