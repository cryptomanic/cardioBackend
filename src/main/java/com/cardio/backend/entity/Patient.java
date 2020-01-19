package com.cardio.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patient")
public class Patient {

    public String firstName;
    public String lastName;
    @Id
    public String emailId;
    public String password;
    public String phoneNo;
    public Integer question;

    public Patient(String firstName, String lastName, String emailId, String password,
                   String phoneNo, Integer question) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.question = question;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }
}
