package org.eyematics.process.utils.measurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimeMeasurement {
    private static final Logger logger = LoggerFactory.getLogger(TimeMeasurement.class);
    private final String fileName;
    private final ArrayList<String> header;
    private final ArrayList<String> dateTime;

    public TimeMeasurement(String fileName) {
        this.fileName = fileName;
        this.header = new ArrayList<>();
        this.dateTime = new ArrayList<>();
    }

    public void setDataPoint(String header) {
        this.header.add(header);
        this.dateTime.add(String.valueOf(LocalDateTime.now()));
    }

    public void writeData() {
        File file = new File("/tmp/" + this.fileName + ".csv");
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(String.join(";", this.header));
                writer.write("\n");
                writer.write(String.join(";", this.dateTime));
                writer.write("\n");
            } catch (IOException e) {
                this.logException(e.getMessage());
            }
        } else {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(String.join(";", this.dateTime));
                writer.write("\n");
            } catch (IOException e) {
                this.logException(e.getMessage());
            }
        }
    }

    private void logException(String message) {
        logger.error("Time measurement has encountered a problem: {}", message);
    }

    public void reset() {
        this.header.clear();
        this.dateTime.clear();
    }

}
