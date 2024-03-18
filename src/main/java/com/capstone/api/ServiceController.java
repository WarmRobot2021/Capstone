package com.capstone.api;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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

        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServices());

    }

    @GetMapping("/Services/{org_id}")
    public ResponseEntity<ArrayList<Service>> getServiceByOrgID(@PathVariable int org_id) {

        System.out.println(org_id);
        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServicesByOrgId(org_id));

    }

    @GetMapping("/Services/Categories")
    public ResponseEntity<ArrayList<String>> getServiceCategories() {

        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServiceCategories());


    }
/*
    @GetMapping("/Services/Categories/{category}")
    public ResponseEntity<ArrayList<Service>> getServicesByCategory(@PathVariable String category) {

        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServicesByCategory(category));

    }
//SELECT * FROM Organizations, Services, Schedule
    //WHERE Services.org_id = Organizations.org_id
    //AND Services.service_id = Schedule.service_id
    //AND Schedule.day_of_week = dayname(now())
    //AND Schedule.open_time < now()
    //AND Schedule.close_time < now()
    //AND Services.service_category = {selected category}
    //Get schedule by open, day, org id, and service id
*/
    @GetMapping("services/schedule/")
    public ResponseEntity<HashMap<String, Object>> getByOpen(@RequestParam(required = false) boolean open, @RequestParam(required = false) String category) {

        System.out.println(open);
        System.out.println(category);
        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getByOpen(open, category));

    }


    @PostMapping("/Services")
    public ResponseEntity updateServices(@RequestBody(required = true) Service serv) {

        sd.insert(serv);
        return ResponseEntity.ok(HttpStatus.CREATED);

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