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
public class TempSensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime;
    private Float temperature;
    private String location;
    private String unit;
    private String deviceid;
    private Integer uptimeInMinutes;
    //private String userid;

    /*@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private UserModel userModel;*/

}