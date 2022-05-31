package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensorReading;
import com.shishir.iothooks.service.TempSensorReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tempreadings")
public class TempSensorReadingController {

    @Autowired
    TempSensorReadingService tempSensorReadingService;

    @PostMapping()
    public TempSensorReading postTempReading(@RequestBody TempSensorReading tempSensorReading){
        return tempSensorReadingService.save(tempSensorReading);
    }

    @GetMapping("/{id}")
    public Optional<TempSensorReading> getTempReadingById(@PathVariable Long id){
        return tempSensorReadingService.findById(id);
    }

    @GetMapping()
    public Iterable<TempSensorReading> getAllTempReading(@RequestParam(name="sensorid",required = false) Integer deviceid){
        return tempSensorReadingService.getTempSensorReadings(deviceid);
    }

    @GetMapping("/status")
    public String getEquipmentStatus(@RequestParam(name="deviceid",required = true) String deviceid){
        return tempSensorReadingService.getEquipmentStatus(deviceid);
    }

    @DeleteMapping
    public void deleteAll(){
        tempSensorReadingService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id){
        tempSensorReadingService.deleteById(id);
    }



}
