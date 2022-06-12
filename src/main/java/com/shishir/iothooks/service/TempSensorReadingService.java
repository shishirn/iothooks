package com.shishir.iothooks.service;

import com.shishir.iothooks.models.TempSensor;
import com.shishir.iothooks.models.TempSensorReading;
import com.shishir.iothooks.repositories.TempSensorReadingRepository;
import com.shishir.iothooks.repositories.TempSensorRepository;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class TempSensorReadingService {

    Logger logger = LoggerFactory.getLogger(TempSensorReadingService.class);

    @Autowired
    TempSensorReadingRepository tempSensorReadingRepository;

    @Autowired
    TempSensorRepository tempSensorRepository;

    @Value("${temp.upperlimit}")
    private Integer upperLimit;

    @Value("${sensor.maxuptimeinminutes}")
    private Integer maxUptimeInMinutes;

    private Integer notificationFreq;

    public TempSensorReading save(TempSensorReading tempSensorReading) {
        notificationFreq = 1;
        TempSensor tempSensor = tempSensorReading.getTempSensor();

        //set time of sensor readings
        tempSensorReading.setDatetime(new Timestamp(System.currentTimeMillis()));

        //Check if reported temperature is above the upper threshold limit. If yes, then update the maxUptime value
        //by last current time minus last reported time.
        int upperTempLimit = tempSensor.getUpperTemperatureLimit();

        if (tempSensorReading != null && tempSensorReading.getTemperature() > upperTempLimit) {

            //Get last reported reading of the sensor
            TempSensorReading lastReportedTempReading = tempSensorReadingRepository
                    .lastReportedTemperatureReading(tempSensorReading.getTempSensor().getDeviceid());

            //Update the equipment uptime by diff of current timestamp and last reported timestamp
            if (lastReportedTempReading != null) {
                Timestamp lastReportedReadingTimestamp = lastReportedTempReading.getDatetime();
                int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - lastReportedReadingTimestamp.getTime());
                logger.info("time diff in minutes is : " + diffInMinutes);
                tempSensorReading.setUptimeInMinutes(lastReportedTempReading.getUptimeInMinutes() +
                        (diffInMinutes));
            } else
                tempSensorReading.setUptimeInMinutes(0);
        } else
            tempSensorReading.setUptimeInMinutes(0);


        //check if equipment uptime is higher than the max Uptime defined for
        // that equipment and send IFTTT notification if true
        /*if (tempSensorReading.getUptimeInMinutes() >= maxUptimeInMinutes) {
            System.out.println("****** Uptime in minutes " + tempSensorReading.getUptimeInMinutes());
            if (tempSensorReading.getUptimeInMinutes() % notificationFreq == 0) {
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
        }*/

        return tempSensorReadingRepository.save(tempSensorReading);
    }

    public Optional<TempSensorReading> findById(Long id) {

        return tempSensorReadingRepository.findById(id);
    }

    public Iterable<TempSensorReading> findAll() {
        List<TempSensorReading> tempSensorReadingList = (List<TempSensorReading>) tempSensorReadingRepository.findAll();
        tempSensorReadingList.sort((o1, o2) -> o1.getId() > o2.getId() ? -1 : 1);
        //return tempSensorRepository.findAll();
        return tempSensorReadingList;
    }


    public String getEquipmentStatus(String deviceid) {
        TempSensorReading tempSensorReading = tempSensorReadingRepository.lastReportedTemperatureReading(deviceid);
        if (tempSensorReading.getTemperature() > 100) {
            return "ON";
        }
        return "OFF";
    }

    public void deleteAll() {
        tempSensorReadingRepository.deleteAll();
    }

    public void deleteById(Long id) {
        tempSensorReadingRepository.deleteById(id);
    }

    public Iterable<TempSensorReading> getTempSensorReadings(Integer deviceid) {
        List<TempSensorReading> tempSensorReadingList = null;

        if (deviceid == null) {
            tempSensorReadingList = (List<TempSensorReading>) tempSensorReadingRepository.findAll();
        } else {
            tempSensorReadingList = tempSensorReadingRepository.getReadingBySensorId(deviceid);
        }

        tempSensorReadingList.sort((o1, o2) -> o1.getId() > o2.getId() ? -1 : 1);
        return tempSensorReadingList;
    }
}
