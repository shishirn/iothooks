package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/temperature")
public class TempSensorController {

    @Autowired
    TempSensorRepository tempSensorRepository;

    @PostMapping("/")
    public TempSensorModel postTempReading(@RequestBody TempSensorModel tempSensorModel){

         return tempSensorRepository.save(tempSensorModel);

    }

    @GetMapping("/{id}")
    public Optional<TempSensorModel> getTempReadingById(@PathVariable Long id){
        return tempSensorRepository.findById(id);
    }

}
