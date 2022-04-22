package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import com.shishir.iothooks.service.TempSensorService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
