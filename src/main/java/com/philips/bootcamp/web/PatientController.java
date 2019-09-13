/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.bootcamp.domain.Patient;
import com.philips.bootcamp.service.PatientService;
import com.philips.bootcamp.service.PulseRate;
import com.philips.bootcamp.service.Spo2;
import com.philips.bootcamp.service.Temperature;

@RestController
public class PatientController {
  PatientService service;

  @Autowired
  public void setService(PatientService service) {
    this.service = service;
  }

  @PostMapping(value = "/api/patients")
  public ResponseEntity<Patient> addAPatient(@RequestBody Patient toBeAdded) {
    try {
      final String id = service.addNewPatient(toBeAdded);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/patients/" + id));
      return new ResponseEntity<>(headers, HttpStatus.CREATED);
    } catch (final IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/api/patients/{patientId}")
  public ResponseEntity<Patient> dischargeAPatient(@PathVariable("patientId") String patientId) {
    final Patient patient = service.findById(patientId);
    if (patient != null) {
      service.dischargePatient(patientId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/api/patients")
  public List<Patient> displayRecords() {
    return service.findAll();
  }

  @GetMapping(value = "/api/patients/{patientId}/bed")
  public int findPatientBed(@PathVariable("patientId") String patientId) {
    return service.findBedId(patientId);
  }

  @PostMapping(value = "/api/patients/{patientId}/vitals")
  public ResponseEntity<List<String>> monitorVitals(@PathVariable("patientId") String patientId,
      @RequestBody Patient vitals) {
    final Temperature temperatureObj = new Temperature();
    final Spo2 spo2Obj = new Spo2();
    final PulseRate pulseRateObj = new PulseRate();
    final Patient patient = service.findById(patientId);
    final List<String> alerts = new ArrayList<>();
    if (patient != null) {
      if (temperatureObj.checkRange(vitals.getTemperatureVal())) {
        service.alarmSwitch("temperature", true, patientId);
        alerts.add(temperatureObj.alertMessage(vitals.getTemperatureVal()));
      }
      if (spo2Obj.checkRange(vitals.getSpo2Val())) {
        service.alarmSwitch("spo2", true, patientId);
        alerts.add(spo2Obj.alertMessage(vitals.getSpo2Val()));
      }
      if (pulseRateObj.checkRange(vitals.getPulseRateVal())) {
        service.alarmSwitch("pulseRate", true, patientId);
        alerts.add(pulseRateObj.alertMessage(vitals.getPulseRateVal()));
      }
      return new ResponseEntity<>(alerts, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/api/patients/{patientId}/alarm/{vital}")
  public ResponseEntity<Patient> turnOffAlarm(@PathVariable String patientId,
      @PathVariable String vital) {
    final Patient patient = service.findById(patientId);
    if (patient != null) {
      if (vital.equals("temperature")) {
        service.alarmSwitch("temperature", false, patientId);
        return new ResponseEntity<>(HttpStatus.OK);
      } else if (vital.equals("spo2")) {
        service.alarmSwitch("spo2", false, patientId);
        return new ResponseEntity<>(HttpStatus.OK);
      } else if (vital.equals("pulseRate")) {
        service.alarmSwitch("pulseRate", false, patientId);
        return new ResponseEntity<>(HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
