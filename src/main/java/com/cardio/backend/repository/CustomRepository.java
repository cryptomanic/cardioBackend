package com.cardio.backend.repository;

import com.cardio.backend.entity.Patient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CustomRepository extends MongoRepository<Patient, String> {
    public Patient findByFirstName(String firstName);
    public List<Patient> findByLastName (String lastName);
}
