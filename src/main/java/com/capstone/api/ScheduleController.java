package com.capstone.api;




import java.util.ArrayList;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@CrossOrigin
public class ScheduleController {


    private ScheduleData schedDat = new ScheduleData();


    public ScheduleController() {


        schedDat.connect("capstone", "root", "CapstoneProjectPass");


    }


    @GetMapping("/schedule")
    public ResponseEntity<ArrayList<Schedule>> getSchedules() {


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedDat.getSchedules());




    }


    @GetMapping("/schedule/{id}")
    public ResponseEntity<ArrayList<Schedule>> getScheduleById(@PathVariable int id) {


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedDat.getSchedulesByServiceId(id));


    }


    @GetMapping("/schedule/{id}/{name}")
    public ResponseEntity<ArrayList<Schedule>> getScheduleByName(@PathVariable int id, @PathVariable String name) {


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedDat.getSchedulesByName(id, name));


    }


    @PostMapping("/schedule")
    public void createSchedule(@RequestBody(required = true) Schedule sched){


        schedDat.insert(sched);




    }


    @PutMapping("/schedule")
    public void updateSchedule(@RequestBody(required = true) Schedule sched) {


        schedDat.update(sched);


    }


    @DeleteMapping("/schedule/{id}")
    public void deleteScheduleByScheduleId(@PathVariable final int id) {


        schedDat.deleteScheduleByScheduleId(id);


    }


    @DeleteMapping("/schedule/service/{id}")
    public void deleteScheduleByServiceId(@PathVariable final int id) {


        schedDat.deleteScheduleByServiceId(id);


    }


}
