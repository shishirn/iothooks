package com.shishir.iothooks.repositories;

import com.shishir.iothooks.models.TempSensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempSensorRepository extends CrudRepository<TempSensor,Long> {
}
