package com.cardio.backend.controller;

import com.cardio.backend.entity.Patient;
import com.cardio.backend.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    @Autowired
    CustomRepository customRepository;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String addPatient(@RequestBody Patient patient) {
        Optional<Patient> optionalPatient = customRepository.findById(patient.emailId);

        if(optionalPatient.isPresent()) {
            return "{\"Success\":0,message:\"Patient already registered\"}";
        } else {
            customRepository.save(patient);
            return "{\"Success\":1,message:\"Successfully registered\"}";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String checkPatient(@RequestBody Patient patient) {
        Optional<Patient> optionalPatient = customRepository.findById(patient.emailId);

        if(optionalPatient.isPresent()) {
            Patient tempPatient = optionalPatient.get();
            if(tempPatient.password.equals(patient.password)) {
                return "{\"Success\":1,message:\"Login successful\"}";
            }
            return "{\"Success\":0,message:\"Wrong password\"}";

        } else {
            return "{\"Success\":0,message:\"Wrong EmailId\"}";
        }
    }

}
