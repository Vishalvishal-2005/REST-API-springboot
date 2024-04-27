package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Homeappliance;
import com.example.project.service.HomeapplianceService;

@RestController
@RequestMapping("/api")
public class Homeappliancecontroller{
    @Autowired
    private HomeapplianceService ps;

    @PostMapping("/customer")
    public ResponseEntity<Homeappliance> createPatient(@RequestBody Homeappliance product){
        Homeappliance createdProduct=ps.createPatient(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/customers")
    public List<Homeappliance> getAllPatients(){
        return ps.getAllPatients();
    }

    @GetMapping("/customer/{customerId}")
    public Optional<Homeappliance> getPatientById(@PathVariable int CustomerId){
        Optional<Homeappliance> product=ps.getPatientById(CustomerId);
        return product;
    }
        @PutMapping("/customer/{customerId}")
    public ResponseEntity<Homeappliance> putMethod(@PathVariable("CustomerId") int CustomerId,@RequestBody Homeappliance l)
    {
        if(ps.updateDetails(CustomerId,l) == true)
        {
            return new ResponseEntity<>(l,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Boolean> delete(@PathVariable("customerId") int CustomerId)
    { 
        if(ps.deleteStudent(CustomerId) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

}
