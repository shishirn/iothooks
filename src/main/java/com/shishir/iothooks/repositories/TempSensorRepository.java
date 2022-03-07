package com.shishir.iothooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shishir.iothooks.models.TempSensorModel;

@Repository
public interface TempSensorRepository extends JpaRepository<TempSensorModel, Long> {
}