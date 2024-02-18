package com.capstone.api;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ScheduleController {

    private ScheduleData schedDate = new ScheduleData();

    public ScheduleController() {

        schedDate.connect("capstone", "root", "CapstoneProjectPass");

    }

    @GetMapping("/Schedule")
    public ResponseEntity<ArrayList<Schedule>> getSchedules() {

        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedDate.getSchedules());


    }

    @GetMapping("/Schedule/{id}")
    public ResponseEntity<ArrayList<Schedule>> getSchedulesById(@PathVariable final int id) {

        System.out.println(id);
        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedDate.getSchedulesById(id));

    }

    //SELECT * FROM Organizations, Services, Schedule
    //WHERE Services.org_id = Organizations.org_id
    //AND Services.service_id = Schedule.service_id
    //AND Schedule.day_of_week = dayname(now())
    //AND Schedule.open_time < now()
    //AND Schedule.close_time < now()
    //AND Services.service_category = {selected category} 
    //Get schedule by open, day, org id, and service id

    @PostMapping("/Schedule")
    public void updateOrganization(@RequestBody(required = true) Schedule sched){

        schedDate.insert(sched);


    }

    @PutMapping("/Schedule")
    public void updateOrg(@RequestBody(required = true) Schedule sched) {

        schedDate.update(sched);

    }

    @DeleteMapping("/Schedule/{id}")
    public void deleteOrg(@PathVariable final int id) {

        schedDate.delete(id);

    }

}
