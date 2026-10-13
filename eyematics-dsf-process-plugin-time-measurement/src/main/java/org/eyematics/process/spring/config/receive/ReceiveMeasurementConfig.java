package org.eyematics.process.spring.config.receive;

import org.eyematics.process.utils.measurement.TimeMeasurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ReceiveMeasurementConfig {

    private final TimeMeasurement timeMeasurement;

    public ReceiveMeasurementConfig(@Value("${org.eyematics.receive.measurement.setup:0001-PATIENTS-1-LOCATIONS-RECEIVE}") String provideMeasurementSetup) {
        timeMeasurement = new TimeMeasurement(provideMeasurementSetup);
    }

    public TimeMeasurement getTimeMeasurementReceiveSetup() {
        return this.timeMeasurement;
    }

}
