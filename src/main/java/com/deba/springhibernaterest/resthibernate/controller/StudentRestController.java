package com.deba.springhibernaterest.resthibernate.controller;

import com.deba.springhibernaterest.resthibernate.entity.Student;
import com.deba.springhibernaterest.resthibernate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RestController
public class StudentRestController {

    @Autowired
    StudentRepository studentRepo;

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){

        Student foundStudent= studentRepo.findById(id).get();
        //System.out.println("Id of found student is : "+foundStudent.getId());
        return ResponseEntity.ok(foundStudent);

    }

    @PostMapping(value = "/student")
    public ResponseEntity<Student> createStudentRecord(@RequestBody Student requestBody){
        Student savedStudent=studentRepo.save(requestBody);

        //String result =savedStudent!=null ? "Student data saved is : "+savedStudent.getName(): "Student data not saved";

        if(savedStudent==null)
            return ResponseEntity.notFound().build();
        else
        {
           URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
           return ResponseEntity.created(uri).body(savedStudent);
        }
    }

}
