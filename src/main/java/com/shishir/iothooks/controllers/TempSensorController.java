package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensor;
import com.shishir.iothooks.service.TempSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController()
@RequestMapping("/tempsensors")
public class TempSensorController {

    @Autowired
    TempSensorService tempSensorService;

    @GetMapping
    public Iterable<TempSensor> findAll(){
        return tempSensorService.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<TempSensor> findById(@PathVariable Long id){
        return tempSensorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TempSensor addTempSensor(@RequestBody TempSensor tempSensor){
        return tempSensorService.addTempSensor(tempSensor);
    }
}
