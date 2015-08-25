import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public"); // Relative path for images, css, etc.
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Specialty> specialties = Specialty.all();

      model.put("specialties", specialties);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Specialty newSpecialty = new Specialty(request.queryParams("newSpecialty"));
      newSpecialty.save();
      List<Specialty> specialties = Specialty.all();

      model.put("specialties", specialties);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/specialties/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Specialty specialty = Specialty.find(Integer.parseInt(request.params(":id")));
      List<Doctors> doctors = specialty.getDoctors();

      model.put("specialty", specialty);
      model.put("doctors", doctors);
      model.put("template", "templates/specialty.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/specialties/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Specialty specialty = Specialty.find(Integer.parseInt(request.params(":id")));
      Doctors newDoctor = new Doctors(request.queryParams("newDoctor"));
      newDoctor.addSpecialty(specialty.getId());
      newDoctor.save();
      List<Doctors> doctors = specialty.getDoctors();

      model.put("specialty", specialty);
      model.put("doctors", doctors);
      model.put("template", "templates/specialty.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/specialties/:specialty_id/doctors/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Doctors doctor = Doctors.find(Integer.parseInt(request.params(":id")));
      List<Patient> patients = doctor.getPatients();
      Specialty specialty = Specialty.find(Integer.parseInt(request.params(":specialty_id")));
      model.put("specialty", specialty);
      model.put("doctor", doctor);
      model.put("patients", patients);
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/specialties/:specialty_id/doctors/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Doctors doctor = Doctors.find(Integer.parseInt(request.params(":id")));
      Patient newPatient = new Patient(request.queryParams("newPatientName"), request.queryParams("newPatientBday"));
      newPatient.addDoctor(doctor.getId());
      newPatient.save();
      List<Patient> patients = doctor.getPatients();

      model.put("doctor", doctor);
      model.put("patients", patients);
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

}
