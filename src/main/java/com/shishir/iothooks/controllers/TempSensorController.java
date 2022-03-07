package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.TempSensorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/temperature/")
public class TempSensorController {

    @Autowired
    TempSensorController tempSensorController;

    @PostMapping( "/")
    public Optional<TempSensorModel> postTempReading(@RequestBody TempSensorModel tempSensorModel){
        Optional<TempSensorModel> tempReading = tempSensorController.postTempReading(tempSensorModel);
        return tempReading;
    }
}
