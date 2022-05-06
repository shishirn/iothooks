package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import com.shishir.iothooks.service.TempSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/temperature")
public class TempSensorController {

    @Autowired
    TempSensorService tempSensorService;


    @PostMapping()
    public TempSensorModel postTempReading(@RequestBody TempSensorModel tempSensorModel){
        return tempSensorService.save(tempSensorModel);
    }

    @GetMapping("/{id}")
    public Optional<TempSensorModel> getTempReadingById(@PathVariable Long id){
        return tempSensorService.findById(id);
    }

    @GetMapping()
    public Iterable<TempSensorModel> getAllTempReading(){
        return tempSensorService.findAll();
    }

    @GetMapping("/status")
    public String getEquipmentStatus(@RequestParam(name="deviceid",required = true) String deviceid){
        return tempSensorService.getEquipmentStatus(deviceid);
    }

    @DeleteMapping
    public void deleteAll(){
        tempSensorService.deleteAll();

    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id){
        tempSensorService.deleteById(id);

    }
}
