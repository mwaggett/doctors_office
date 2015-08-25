import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("List Of Specialties:");
  }

  @Test
  public void specialtyIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#newSpecialty").with("plastic");
    submit(".btn");
    assertThat(pageSource()).contains("plastic");
  }

  @Test
  public void specialtyRouteToDoctorList(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    String specialtyPath = String.format("http://localhost:4567/specialties/%d", mySpecialty.getId());
    goTo(specialtyPath);
    assertThat(pageSource()).contains("Doctors in plastics:");
  }

  @Test
  public void displayDoctorsPatientList(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    Doctors myDoctor = new Doctors("Dr. Sloan");
    myDoctor.save();
    String specialtyDoctorPath = String.format("http://localhost:4567/specialties/%d/doctors/%d", mySpecialty.getId(), myDoctor.getId());
    goTo(specialtyDoctorPath);
    assertThat(pageSource()).contains("Dr. Sloan's Patients:");
  }

  @Test
  public void patientsAddedToDoctorList(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    Doctors myDoctor = new Doctors("Dr. Sloan");
    myDoctor.save();
    Patient myPatient = new Patient("Eva", "Jan 1, 1980");
    myPatient.addDoctor(myDoctor.getId());
    myPatient.save();
    String specialtyDoctorPath = String.format("http://localhost:4567/specialties/%d/doctors/%d", mySpecialty.getId(), myDoctor.getId());
    goTo(specialtyDoctorPath);
    assertThat(pageSource()).contains("Eva: Jan 1, 1980");
  }

  @Test
  public void linkToSpecialtyListFromPatientListWorks(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    Doctors myDoctor = new Doctors("Dr. Sloan");
    myDoctor.save();
    Patient myPatient = new Patient("Eva", "Jan 1, 1980");
    myPatient.addDoctor(myDoctor.getId());
    myPatient.save();
    String specialtyDoctorPath = String.format("http://localhost:4567/specialties/%d/doctors/%d", mySpecialty.getId(), myDoctor.getId());
    goTo(specialtyDoctorPath);
    click("a", withText("return to specialties"));
    assertThat(pageSource()).contains("List Of Specialties:");
  }

  @Test
  public void linkToDoctorListFromPatientListWorks(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    Doctors myDoctor = new Doctors("Dr. Sloan");
    myDoctor.save();
    Patient myPatient = new Patient("Eva", "Jan 1, 1980");
    myPatient.addDoctor(myDoctor.getId());
    myPatient.save();
    String specialtyDoctorPath = String.format("http://localhost:4567/specialties/%d/doctors/%d", mySpecialty.getId(), myDoctor.getId());
    goTo(specialtyDoctorPath);
    click("a", withText("return to list of doctors"));
    assertThat(pageSource()).contains("Doctors in plastics:");
  }

  @Test
  public void linkToSpecialtiesListFromDoctorListWorks(){
    Specialty mySpecialty = new Specialty("plastics");
    mySpecialty.save();
    Doctors myDoctor = new Doctors("Dr. Sloan");
    myDoctor.save();
    String specialtyDoctorPath = String.format("http://localhost:4567/specialties/%d/doctors/%d", mySpecialty.getId(), myDoctor.getId());
    goTo(specialtyDoctorPath);
    click("a", withText("return to specialties"));
    assertThat(pageSource()).contains("List Of Specialties:");
  }


  // ~~INTEGRATION TESTING~~
  // @Test
  // public void rootTest() {
  //   goTo("http://localhost:4567/");
  //   assertThat(pageSource()).contains("Something");
  // }
  //
  // @Test
  // public void newPage_desiredResult() {
  //   goTo("starting_page_url");
  //   fill("#input_id").with("input");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Some result of input");
  // }

}
