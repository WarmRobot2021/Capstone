package com.capstone.api;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    private ServiceData sd = new ServiceData();

    public ServiceController() {

        sd.connect("capstone", "root", "CapstoneProjectPass");

    }

    @GetMapping("/Services")
    public ResponseEntity<ArrayList<Service>> getServices() {

        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServices());

    }

    @GetMapping("/Services/{org_id}")
    public ResponseEntity<ArrayList<Service>> serviceByOrgID(@PathVariable int org_id) {

        System.out.println(org_id);
        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServicesByOrgId(org_id));

    }



    @PostMapping("/Services")
    public ResponseEntity updateServices(@RequestBody(required = true) Service serv) {

        sd.insert(serv);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/Services")
    public void addServices(@RequestBody(required = true) Service serv) {

        sd.update(serv);

    }

    @DeleteMapping("/Services/{id}")
    public void deleteServices(@PathVariable int id) {

        sd.delete(id);

    }
}