package com.shishir.iothooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shishir.iothooks.models.TempSensorModel;

@Repository
public interface TempSensorRepository extends CrudRepository<TempSensorModel, Long> {

    @Query(value = "select * from tempsensors where deviceid = ?1 and " +
            "datetime = (select max(datetime) from tempsensors where " +
            "deviceid = ?1)",nativeQuery = true)
    TempSensorModel getMostRecentTempReading(String deviceid);
}