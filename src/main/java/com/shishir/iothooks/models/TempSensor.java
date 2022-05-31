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
@Entity(name="tempsensors")
@Table(name="tempsensors")
public class TempSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceid;

    private String unit;

    /*@Column(name="sensortype", nullable = false, length = 10)
    private SensorType sensorType;*/

    private Timestamp installationDate;

    private Integer upperTemperatureLimit;

    private Integer maxUptimeInMinutes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    private String location;

}
