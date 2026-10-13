package org.eyematics.process.spring.config.provide;

import org.eyematics.process.utils.measurement.TimeMeasurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProvideMeasurementConfig {

    private final TimeMeasurement timeMeasurement;

    public ProvideMeasurementConfig(@Value("${org.eyematics.provide.measurement.setup:0001-PATIENTS-1-LOCATIONS-PROVIDE}") String provideMeasurementSetup) {
        timeMeasurement = new TimeMeasurement(provideMeasurementSetup);
    }

    public TimeMeasurement getTimeMeasurementProvideSetup() {
        return this.timeMeasurement;
    }

}
