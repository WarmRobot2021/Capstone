package com.capstone.api;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrgController {

    private OrgData od = new OrgData();

    public OrgController() {

        od.connect("capstone", "root", "CapstoneProjectPass");

    }

    @GetMapping("/organizations")
    public ResponseEntity<ArrayList<Organizations>> getOrganizations() {

        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(od.getOrganizations());


    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<Organizations> getOrganizationsByID(@PathVariable final int id) {

        System.out.println(id);
        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(od.getOrganizationsById(id));

    }

    @GetMapping("/organizations/categories/{category}")
    public ResponseEntity<ArrayList<Organizations>> getOrganizationsByCategory(@PathVariable String category) {

        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(od.getOrganizationsByCategory(category));
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/organizations")
    public ResponseEntity updateOrganization(@RequestBody(required = true) Organizations org){

        od.insert(org);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping("/organizations")
    public void updateOrg(@RequestBody(required = true) Organizations org) {

        od.update(org);

    }

    @DeleteMapping("/organizations/{id}")
    public void deleteOrg(@PathVariable final int id) {

        od.delete(id);

    }

}
