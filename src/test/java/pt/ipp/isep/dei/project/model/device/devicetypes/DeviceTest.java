package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.Room;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * device tests class.
 */

public class DeviceTest {

    @Test
    public void getDeviceTypeTest() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        DeviceType dT = DeviceType.WASHING_MACHINE;
        DeviceType expectedResult = dT;
        DeviceType result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualToSameObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        Device d2 = new Device("WMTwo", 12, new WashingMachine());

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Device d1 = new Device("frigo", 150, new Fridge());
        Room room = new Room("kitchen", 1, 1, 1, 1);
        String result = d1.buildDeviceString();
        String expectedResult = "The device Name is frigo, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Device d1 = new Device("frigo", 150, new Fridge());
        d1.setmName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
    public void hashCodeDummyTest() {
        Device d1 = new Device();
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Device d1 = new Device("heater", 150, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Double expectedResult = 33.3;
        d1.setAttributeValue("volumeOfWater", 33.3);
        Object result = d1.getAttributeValue("volumeOfWater");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        Device d1 = new Device("heater", 150, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains("volumeOfWater"));
        assertTrue(result.contains("hotWaterTemperature"));
        assertTrue(result.contains("coldWaterTemperature"));
        assertTrue(result.contains("performanceRatio"));
        assertTrue(result.contains("volumeOfWaterToHeat"));
        assertEquals(result.size(), 5);
    }

}
