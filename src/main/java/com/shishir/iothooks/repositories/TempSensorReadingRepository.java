package com.shishir.iothooks.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shishir.iothooks.models.TempSensorReading;

import java.util.List;

@Repository
public interface TempSensorReadingRepository extends CrudRepository<TempSensorReading, Long> {

    @Query(value = "select * from tempsensors where deviceid = ?1 and " +
            "datetime = (select max(datetime) from tempsensors where " +
            "deviceid = ?1)",nativeQuery = true)
    TempSensorReading getMostRecentTempReading(String deviceid);

    @Query(value="select * from tempsensorreadings where tempsensor_id = ?1", nativeQuery = true)
    List<TempSensorReading> getReadingBySensorId(String deviceid);

}