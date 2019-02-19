package pt.ipp.isep.dei.project.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class FileInputUtils {

    int deviceMeteringPeriod;
    int gridMeteringPeriod;

    /**
     * This method will read the configuration file and validate the value that corresponds
     * to the grid metering period. In case of a valid value, the value will be stored has a
     * class attribute
     *
     * @return it will return true in case the value is valid and false if not
     **/
    boolean validGridMetering() throws IOException {
        int gridMeteringPeriod = readGridMeteringPeriod();
        if (gridMeteringPeriodValidation(gridMeteringPeriod)) {
            this.gridMeteringPeriod = gridMeteringPeriod;
            return true;
        }
        return false;
    }

    /**
     * This method will read the configuration file, get the string that corresponds to the
     * grid metering period and turn it into an integer
     *
     * @return the integer that corresponds to the grid metering period
     **/
    private int readGridMeteringPeriod() throws IOException {
        String gridMeteringPeriod;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/meteringPeriods.properties")) {
            prop.load(input);
            gridMeteringPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        try {
            return Integer.parseInt(gridMeteringPeriod);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("ERROR: Configuration File value is not a numeric value.");
        }
    }

    /**
     * This method will receive an integer and check if the value is valid. The sum of
     * all metering periods in a day should be 24 hours (1440 minutes)
     *
     * @param gridMeteringPeriod integer to be tested
     * @return true in case the value is valid, false if not
     **/
    public boolean gridMeteringPeriodValidation(int gridMeteringPeriod) {
        if (gridMeteringPeriod == 0) {
            System.out.println("Grid metering value must be greater than 0.");
            return false;
        }
        return 1440 % gridMeteringPeriod == 0;
    }

    //Device

    boolean validDeviceMetering() {
        int deviceMeteringPeriod = readDeviceMeteringPeriod();

        if (deviceMeteringPeriodValidation(deviceMeteringPeriod)) {
            this.deviceMeteringPeriod = deviceMeteringPeriod;
            return true;
        }
        System.out.println("ERROR: Configuration File values are incorrect. Devices cannot be created.\n" +
                "Please fix the configuration file before continuing.");
        return false;
    }

    private int readDeviceMeteringPeriod() {
        String deviceMeteringPeriod = "";
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            deviceMeteringPeriod = prop.getProperty("DevicesMeteringPeriod");
            input.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
        } catch (IOException ioe) {
            System.out.println("ERROR: Unable to process configuration file.");
        }
        int deviceMPvalue = 0;
        try {
            deviceMPvalue = (Integer) Integer.parseInt(deviceMeteringPeriod);
        } catch (NumberFormatException nfe) {
            System.out.println("Configuration file values are not numeric.");
        }
        return deviceMPvalue;
    }

    private boolean deviceMeteringPeriodValidation(int deviceValue) {
        if (deviceValue == 0) {
            System.out.println("Device metering value must be greater than 0.");
            return false;
        }
        if (1440 % deviceValue != 0) {
            return false;
        }
        return deviceValue % this.gridMeteringPeriod == 0;
    }
}
