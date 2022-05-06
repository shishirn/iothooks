package com.shishir.iothooks.service;

import com.shishir.iothooks.models.TempSensorModel;
import com.shishir.iothooks.repositories.TempSensorRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TempSensorService {

    @Autowired
    TempSensorRepository tempSensorRepository;

    @Value("${temp.upperlimit}")
    private Integer upperLimit;

    @Value("${sensor.maxuptimeinminutes}")
    private Integer maxUptimeInMinutes;

    private Integer notificationFreq;

    public TempSensorModel save(TempSensorModel tempSensorModel) {
        notificationFreq=5;

        //set time of sensor readings
        tempSensorModel.setDatetime(new Timestamp(System.currentTimeMillis()));

        //Check if reported temperature is above the upper threshold limit
        if(tempSensorModel!=null && tempSensorModel.getTemperature()>upperLimit){
            //Get last reading for the sensor
            TempSensorModel mostRecentTempReading = tempSensorRepository
                    .getMostRecentTempReading(tempSensorModel.getDeviceid());
            //Update the equipment uptime by 1 min
            if(mostRecentTempReading!=null){
                tempSensorModel.setUptimeInMinutes(mostRecentTempReading.getUptimeInMinutes()+1);
            }
            else
                tempSensorModel.setUptimeInMinutes(1);
        }
        else{
            tempSensorModel.setUptimeInMinutes(0);
        }

        //check if equipment uptime is higher than the max Uptime defined for that equipment and send IFTTT notification if true
        if(tempSensorModel.getUptimeInMinutes()>=maxUptimeInMinutes){
            System.out.println("****** Uptime in minutes "+tempSensorModel.getUptimeInMinutes());
            if(tempSensorModel.getUptimeInMinutes()%notificationFreq==0){
                System.out.println("Sending notification!!!!!");
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n    \"test\": \"Stove is ON\"\r\n}");
                Request request = new Request.Builder()
                        .url("https://maker.ifttt.com/trigger/StoveTopIsOn/json/with/key/iJGJBjaL77IJpXCvf_UL0_KwImuUY8eV8fRkPHarc3s")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempSensorRepository.save(tempSensorModel);
    }

    public Optional<TempSensorModel> findById(Long id) {

        return tempSensorRepository.findById(id);
    }

    public Iterable<TempSensorModel> findAll() {
        List<TempSensorModel> tempSensorModelList = (List<TempSensorModel>) tempSensorRepository.findAll();
        tempSensorModelList.sort((o1, o2) -> o1.getId()>o2.getId() ? -1 : 1);
        //return tempSensorRepository.findAll();
        return tempSensorModelList;
    }


    public String getEquipmentStatus(String deviceid) {
        TempSensorModel tempSensorModel= tempSensorRepository.getMostRecentTempReading(deviceid);
        if(tempSensorModel.getTemperature() > 100){
            return "ON";
        }
        return "OFF";
    }
}
