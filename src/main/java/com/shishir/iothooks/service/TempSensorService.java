package com.shishir.iothooks.service;

import com.shishir.iothooks.models.TempSensor;
import com.shishir.iothooks.repositories.TempSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TempSensorService {

    @Autowired
    TempSensorRepository tempSensorRepository;

    public Iterable<TempSensor> findAll() {
        return tempSensorRepository.findAll();
    }

    public Optional<TempSensor> findById(Long id) {
        return tempSensorRepository.findById(id);
    }

    public TempSensor addTempSensor(TempSensor tempSensor) {
        return tempSensorRepository.save(tempSensor);
    }
}
