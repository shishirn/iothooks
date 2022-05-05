package com.shishir.iothooks.service;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TempSensorService {

    @Autowired
    TempSensorRepository tempSensorRepository;

    @Value("${temp.upperlimit}")
    private Integer upperLimit;

    @Value("${sensor.maxuptimeinminutes}")
    private Integer maxUptimeInMinutes;

    public TempSensorModel save(TempSensorModel tempSensorModel) {
        tempSensorModel.setDatetime(new Timestamp(System.currentTimeMillis()));

        if(tempSensorModel!=null && tempSensorModel.getTemperature()>upperLimit){
            tempSensorModel.setUptimeInMinutes(tempSensorModel.getUptimeInMinutes()+1);
        }
        if(tempSensorModel.getUptimeInMinutes()>maxUptimeInMinutes){

        }
        return tempSensorRepository.save(tempSensorModel);
    }

    public Optional<TempSensorModel> findById(Long id) {
        return tempSensorRepository.findById(id);
    }

    public Iterable<TempSensorModel> findAll() {
        return tempSensorRepository.findAll();
    }


    public String getEquipmentStatus(String deviceid) {
        TempSensorModel tempSensorModel= tempSensorRepository.getMostRecentTempReading(deviceid);
        if(tempSensorModel.getTemperature() > 100){
            return "ON";
        }
        return "OFF";
    }
}
