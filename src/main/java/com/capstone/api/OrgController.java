package com.capstone.api;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrgController {

    private OrgData od = new OrgData();

    public OrgController() {

        od.connect("capstone", "root", "My_Password_2023!");

    }

    @GetMapping("/Organizations/")
    public ResponseEntity<ArrayList<Organization>> getOrganizations() {

        return ResponseEntity.ok(od.getOrganizations());

    }

    @PostMapping("/Organizations/")
    public ResponseEntity updateOrganization(@RequestBody(required = true) Organization org){

        od.update(org);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/Organizations/")
    public ResponseEntity addOrganization(@RequestBody(required = true) Organization org) {

        od.insert(org);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
