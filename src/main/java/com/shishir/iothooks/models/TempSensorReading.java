package com.shishir.iothooks.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tempsensorreadings")
@Table(name="tempsensorreadings")
public class TempSensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp datetime;

    private Float temperature;

    //private String location;

    //private String unit;

    //private String deviceid;

    private Integer uptimeInMinutes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tempsensor_id", nullable = false)
    private TempSensor tempSensor;

}