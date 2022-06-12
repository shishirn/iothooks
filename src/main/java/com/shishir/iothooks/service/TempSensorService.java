package com.shishir.iothooks.service;

import com.shishir.iothooks.exceptions.SensorNotFoundException;
import com.shishir.iothooks.models.TempSensor;
import com.shishir.iothooks.repositories.TempSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class TempSensorService {

    @Autowired
    TempSensorRepository tempSensorRepository;

    public Iterable<TempSensor> findAll() {

        return tempSensorRepository.findAll();
    }

    public ResponseEntity<TempSensor> findById(Long id) throws SensorNotFoundException {
        TempSensor tempSensor =  tempSensorRepository
                .findById(id)
                .orElseThrow(() -> new SensorNotFoundException());
        return ResponseEntity.ok(tempSensor);
    }

    public TempSensor addTempSensor(TempSensor tempSensor) {
        return tempSensorRepository.save(tempSensor);
    }

    //Update temperature sensor details
    public ResponseEntity<TempSensor> updateTempSensor(Long id, TempSensor updatedValues) throws SensorNotFoundException {

        TempSensor existingTempSensor =  tempSensorRepository
                .findById(id)
                .orElseThrow(() -> new SensorNotFoundException());

        if(updatedValues.getLocation()!=null){
            existingTempSensor.setLocation(updatedValues.getLocation());
        }
        if(updatedValues.getMaxUptimeInMinutes()!=null){
            existingTempSensor.setMaxUptimeInMinutes(updatedValues.getMaxUptimeInMinutes());
        }
        if(updatedValues.getUpperTemperatureLimit()!=null){
            existingTempSensor.setUpperTemperatureLimit(updatedValues.getUpperTemperatureLimit());
        }

        tempSensorRepository.save(existingTempSensor);
        return ResponseEntity.ok(existingTempSensor);

    }
}
