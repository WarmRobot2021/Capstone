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
@CrossOrigin
public class ServiceController {


    private ServiceData sd = new ServiceData();
    private CardData cd = new CardData();


    public ServiceController() {


        sd.connect("capstone", "root", "CapstoneProjectPass");
        cd.connect("capstone", "root", "CapstoneProjectPass");


    }


    @GetMapping("/services")
    public ResponseEntity<ArrayList<Service>> getServices() {


        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServices());


    }


    @GetMapping("/services/{org_id}")
    public ResponseEntity<ArrayList<Service>> getServiceByOrgID(@PathVariable int org_id) {


        System.out.println(org_id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServicesByOrgId(org_id));


    }


    @GetMapping("/services/Categories")
    public ResponseEntity<ArrayList<String>> getServiceCategories() {


        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sd.getServiceCategories());




    }


    @GetMapping("services/schedule/")
    public ResponseEntity<ArrayList<Card>> getByOpen(@RequestParam(required = false) boolean open, @RequestParam(required = false) String category) {


        //System.out.println(open);
        //System.out.println(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cd.getByOpen(open, category));


    }


    @PostMapping("/services")
    public ResponseEntity updateServices(@RequestBody(required = true) Service serv) {


        sd.insert(serv);
        return ResponseEntity.ok(HttpStatus.CREATED);


    }


    @PutMapping("/services")
    public void addServices(@RequestBody(required = true) Service serv) {


        sd.update(serv);


    }


    @DeleteMapping("/services/{id}")
    public void deleteServices(@PathVariable int id) {


        sd.delete(id);


    }
}
