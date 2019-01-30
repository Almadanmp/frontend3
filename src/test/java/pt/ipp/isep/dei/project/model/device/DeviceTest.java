package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * device tests class.
 */

public class DeviceTest {

    @Test
    public void getDeviceTypeTest() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue("capacity", 12D);
        String dT = "WashingMachine";
        String expectedResult = dT;
        String result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualToSameObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue("capacity", 12D);
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 34);
        Device d2 = new Device("WMTwo", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Device d1 = new Device("frigo", 150, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Room room = new Room("kitchen", 1, 1, 1, 1);
        String result = d1.buildDeviceString();
        String expectedResult = "The device Name is frigo, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Device d1 = new Device("frigo", 150, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 23D);
        d1.setmName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
    public void hashCodeDummyTest() {
        Device d1 = new Device("FridgeTwo", 12, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Double expectedResult = 33.3;
        d1.setAttributeValue("Volume Of Water", 33.3);
        Object result = d1.getAttributeValue("Volume Of Water");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        String expectedResult = "L";
        d1.setAttributeValue("Volume Of Water", 33.3);
        Object result = d1.getAttributeUnit("Volume Of Water");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit2() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        String expectedResult = "ºC";
        d1.setAttributeValue("Hot Water Temperature", 33.3);
        Object result = d1.getAttributeUnit("Hot Water Temperature");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit3() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        String expectedResult = "";
        d1.setAttributeValue("Performance Ratio", 33.3);
        Object result = d1.getAttributeUnit("Performance Ratio");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit4() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.setAttributeValue(TestUtils.NOMINAL_POWER, 234D);
        String expectedResult = "kW";
        d1.setAttributeValue("nominal power", 33.3);
        Object result = d1.getAttributeUnit("nominal power");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(WaterHeaterSpec.ATTRIBUTE_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(WaterHeaterSpec.ATTRIBUTE_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(WaterHeaterSpec.ATTRIBUTE_PERFORMANCE_RATIO, 234D);
        d1.setAttributeValue(WaterHeaterSpec.NOMINAL_POWER, 234D);
        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains(WaterHeaterSpec.ATTRIBUTE_VOLUME_OF_WATER));
        assertTrue(result.contains(WaterHeaterSpec.ATTRIBUTE_HOT_WATER_TEMP));
        assertTrue(result.contains(WaterHeaterSpec.ATTRIBUTE_PERFORMANCE_RATIO));
        assertTrue(result.contains(WaterHeaterSpec.NOMINAL_POWER));
        assertEquals(result.size(), 4);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        boolean expectedResult = true;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatDeviceIsNotProg() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.isProgrammable();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatDeviceIsProg() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WASHINGMACHINE);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.deactivate();
        boolean expectedResult = true;
        boolean actualResult = d1.isProgrammable();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumption() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.deactivate();
        double expectedResult = 0;
        double actualResult = d1.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getProgramList() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WASHINGMACHINE);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = d1.getProgramList();
        listProgram.addProgram(program1);
        ProgramList result = d1.getProgramList();
        assertEquals(listProgram, result);
    }

    @Test
    void getProgramListFalse() {

        assertThrows(NullPointerException.class,
                () -> {
                    Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
                    d1.getProgramList();
                });
    }
    @Test
    void getLogList(){
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        Log log = new Log(1,new GregorianCalendar(2019,1,1).getTime(),
                new GregorianCalendar(2019,1,1).getTime());
        LogList logList = d1.getLogList();
        d1.addLog(log);
        LogList result = d1.getLogList();
        assertEquals(logList,result);
    }
    @Test
    void addLogListFalse(){
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        Log log = new Log(1,new GregorianCalendar(2019,1,1).getTime(),
                new GregorianCalendar(2019,1,1).getTime());
        d1.addLog(log);
        assertFalse(d1.addLog(log));
    }
}