package com.shishir.iothooks.service;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TempSensorService {

    @Autowired
    TempSensorRepository tempSensorRepository;

    public TempSensorModel save(TempSensorModel tempSensorModel) {
        tempSensorModel.setDatetime(new Timestamp(System.currentTimeMillis()));
        return tempSensorRepository.save(tempSensorModel);
    }

    public Optional<TempSensorModel> findById(Long id) {
        return tempSensorRepository.findById(id);
    }

    public Iterable<TempSensorModel> findAll() {
        return tempSensorRepository.findAll();
    }
}
