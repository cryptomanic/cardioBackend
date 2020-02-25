package com.cardio.backend.controller;

import com.cardio.backend.entity.Patient;
import com.cardio.backend.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    @Autowired
    CustomRepository customRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @CrossOrigin()
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String addPatient(@RequestBody Patient patient) {
        Optional<Patient> optionalPatient = customRepository.findById(patient.emailId);

        if(optionalPatient.isPresent()) {
            return "{\"Success\":0,message:\"Patient already registered\"}";
        } else {
            patient.setSourceOfData("signup");
            customRepository.save(patient);
            return "{\"Success\":1,message:\"Successfully registered\"}";
        }
    }

    @CrossOrigin()
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @CrossOrigin()
    @RequestMapping(value = "/getintouch", method = RequestMethod.POST)
    public ResponseEntity<String> getCustomerContactDetails(@RequestBody Patient patient) {
        patient.setSourceOfData("getintouch");
        customRepository.save(patient);
        String responseBody = "{\"Success\":1,message:\"Customer interest is registered\"}" ;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("india.cardio@gmail.com");

        msg.setSubject("Maja aa gaya");
        msg.setText("Patient Details\n" + "Name: "+patient.getFirstName()+ "\nNumber: "+patient.getPhoneNo());

        javaMailSender.send(msg);
        return new ResponseEntity<String>("suceess", HttpStatus.OK);
    }

    @CrossOrigin()
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> getSuccess() {
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

//    @CrossOrigin()
//    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
//    private ResponseEntity<String> sendEmail(@RequestBody Patient patient) throws AddressException, MessagingException, IOException {
//
//
//        return new ResponseEntity<String>("Mail Sent Successfully", HttpStatus.OK);
//    }

}
