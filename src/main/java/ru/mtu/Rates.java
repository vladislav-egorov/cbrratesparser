package ru.mtu;

import lombok.Data;
import org.dalesbred.annotation.DalesbredInstantiator;

import java.time.LocalDateTime;

@Data
public class Rates {
    Integer id;
    LocalDateTime timeStamp;
    Float usdRate;
    Float eurRate;

    @DalesbredInstantiator
    public Rates(Integer id, LocalDateTime timeStamp, Float usdRate, Float eurRate) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.usdRate = usdRate;
        this.eurRate = eurRate;
    }

    public Rates(Float usdRate, Float eurRate) {
        this(null, LocalDateTime.now(), usdRate, eurRate);
    }


}
