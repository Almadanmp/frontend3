package pt.ipp.isep.dei.project.model.house;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.EnergyGridService;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType;
import pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House tests class.
 */
@ExtendWith(MockitoExtension.class)
class HouseTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private GeographicArea validArea;
    private AreaSensor firstValidAreaSensor;
    private RoomService roomService;
    private EnergyGridService energyGridService;
    private List<String> deviceTypeString;

    @Mock
    RoomRepository roomRepository;

    @Mock
    EnergyGridRepository energyGridRepository;
    @Mock
    RoomSensorRepository roomSensorRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;

    @BeforeEach
    void arrangeArtifacts() {
        roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        energyGridService = new EnergyGridService(energyGridRepository);
        deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validArea = new GeographicArea("Europe", new AreaType("Continent"), 3500, 3000,
                new Local(20, 12, 33));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        firstValidAreaSensor = new AreaSensor("RF12345", "tempOne", new SensorType("Temperature", "Celsius"), new Local(
                30, 20, 10), new Date(), 6008L);
        AreaSensor secondValidAreaSensor = new AreaSensor("RF17745", "rainOne", new SensorType("Rainfall", "l/m2"), new Local(21,
                40, 15), new Date(), 6008L);
        validHouse.setMotherArea(validArea);
    }

    @Test
    void seeIfConstructorWorks() {
        //Arrange

        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        Local expectedLocal = new Local(2, 2, 2);
        List<DeviceType> expectedList = new ArrayList<>();
        expectedList.add(new FridgeType());

        // Act

        String actualResult1 = house.getId();
        Local actualResult2 = house.getLocation();
        int actualResult3 = house.getGridMeteringPeriod();
        int actualResult4 = house.getDeviceMeteringPeriod();
        List<DeviceType> actualResult5 = house.getDeviceTypeList();


        // Assert
        assertEquals("12", actualResult1);
        assertEquals(expectedLocal, actualResult2);
        assertEquals(2, actualResult3);
        assertEquals(2, actualResult4);
        assertEquals(expectedList.get(0).getDeviceType(), actualResult5.get(0).getDeviceType());
    }


    @Test
    void seeIfGetsId() {
        //Arrange

        House house = new House(); //Want to test constructor as well
        house.setId("ISEP");

        // Act

        String actualResult = house.getId();

        // Assert

        assertEquals("ISEP", actualResult);
    }

    @Test
    void seeIfIsRoomListEmptyWorks() {
        //Arrange

        House house = new House(roomService, energyGridService);
        Mockito.when(roomRepository.findAll()).thenReturn(null);

        // Act

        boolean actualResult = house.isRoomListEmpty();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsRoomListEmptyWorksWhenListIsNotEmpty() {
        //Arrange

        House house = new House(roomService, energyGridService);

        Room roomOne = new Room("Kitchen", "Equipped Kitchen", 1, 20, 30, 10, "Room1", "Grid1");
        List<Room> list = new ArrayList<>();
        list.add(roomOne);
        house.addRoom(roomOne);

        Mockito.when(roomRepository.findAll()).thenReturn((list));

        // Act

        boolean actualResult = house.isRoomListEmpty();
        ;

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfSetDeviceTypeListWorks() {
        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);

        List<DeviceType> expectedResult = new ArrayList<>();
        expectedResult.add(new FridgeType());

        // Act

        validHouse.setDeviceTypeList(deviceTypeString);
        List<DeviceType> actualResult = validHouse.getDeviceTypeList();

        // Assert

        assertEquals(expectedResult.get(0).getDeviceType(), actualResult.get(0).getDeviceType());
    }


    @Test
    void seeDistanceToSensor() {
        // Act

        double actualResult = validHouse.calculateDistanceToSensor(firstValidAreaSensor);

        // Assert

        assertEquals(1111.9492664455872, actualResult, 0.01);
    }

    @Test
    void seeIfEqualWorksWithNull() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

        // Act

        boolean actualResult = validHouse.equals(null);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualWorksEqualObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        // Act

        boolean actualResult = validHouse.equals(validHouse); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP1", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotInstanceOf() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 30, 10, "Room1", "Grid1");

        // Act

        boolean actualResult = validHouse.equals(testRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomWorks() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 30, 10, "Room1", "Grid1");

        // Act

        boolean actualResult = validHouse.addRoom(testRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomWorksDuplicate() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Double Bedroom", 2, 30, 30, 10, "Room1", "Grid1");
        validHouse.addRoom(testRoom);

        // Act

        boolean actualResult = validHouse.addRoom(testRoom);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetHouseLocationWorks() {
        // Arrange

        Local expectedResult = new Local(7, 78, 50);

        // Act

        validHouse.setLocation(7, 78, 50);
        Local actualResult = validHouse.getLocation();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetHouseLocationWorksAltitude() {
        // Arrange

        double expectedResult = 70;

        // Act

        validHouse.setLocation(7, 78, 70);
        double actualResult = validHouse.getAltitude();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        //Arrange

        Room testRoom = new Room("Kitchen", "Ground Floor Kitchen", 0, 12, 30, 10, "Room1", "Grid1");
        Device testDevice = new WaterHeater(new WaterHeaterSpec());
        testDevice.setNominalPower(30.0);
        testRoom.addDevice(testDevice);
        validHouse.addRoom(testRoom);
        double expectedResult = 30;

        // Act

        double actualResult = validHouse.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionPerTypeTest() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 15D);
        Room testRoom = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1", "Grid1");
        testRoom.addDevice(waterHeater);
        validHouse.addRoom(testRoom);
        double expectedResult = 0.4;

        // Act

        double result = validHouse.getDailyConsumptionByDeviceType("WaterHeater", 1440);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsMotherAreaNullBothConditions() {
        // Act

        boolean actualResult1 = validHouse.isMotherAreaNull();
        validHouse.setMotherArea(null);
        boolean actualResult2 = validHouse.isMotherAreaNull();

        // Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setName("WaterHeaterOne");
        Room testRoom = new Room("Kitchen", "Ground Floor Kitchen", 0, 15, 15, 10, "Room1", "Grid1");
        testRoom.addDevice(waterHeater);
        validHouse.addRoom(testRoom);
        DeviceList expectedResult = new DeviceList();
        expectedResult.add(waterHeater);

        // Act

        DeviceList actualResult = validHouse.getDevicesOfGivenType("WaterHeater");

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceListWorksEmptyList() {
        // Arrange

        List<Device> expectedResult = new ArrayList<>();

        // Act

        List<Device> actualResult = validHouse.getDeviceList().getList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildDeviceTypeStringWorks() {
        // Arrange

        String expectedResult = "0) DeviceType: Fridge\n";

        // Act

        String actualResult = validHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildDeviceTypeStringWorksEmptyList() {
        // Arrange

        House testHouse = new House("Mock", new Address("Mock", "Mock", "Mock", "Mock", "Mock"),
                new Local(4, 5, 50), 20,
                5, new ArrayList<>());
        testHouse.setMotherArea(new GeographicArea("Mock", new AreaType("Mock"),
                60, 180, new Local(30, 40, 30)));
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String result = testHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDeviceTypeListWorks() {
        // Arrange

        List<String> deviceTypePaths = new ArrayList<>();
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType");
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType");

        // Act

        validHouse.buildDeviceTypeList(deviceTypePaths);
        List<DeviceType> deviceTypeList = validHouse.getDeviceTypeList();

        // Assert

        assertEquals(deviceTypeList.size(), 2);
        assertTrue(deviceTypeList.get(0) instanceof DishwasherType);
        assertTrue(deviceTypeList.get(1) instanceof WaterHeaterType);
    }

    @Test
    void seeIfGetDeviceTypeListWorksIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    List<String> deviceTypePaths = new ArrayList<>();
                    deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.Dish");

                    validHouse.buildDeviceTypeList(deviceTypePaths);
                });
    }

    @Test
    void setGridMeteringPeriod() {
        // Act

        validHouse.setGridMeteringPeriod(8);
        validHouse.setDeviceMeteringPeriod(10);
        double actualResultGrid = validHouse.getGridMeteringPeriod();
        double actualResultDevice = validHouse.getDeviceMeteringPeriod();

        // Assert

        assertEquals(8, actualResultGrid);
        assertEquals(10, actualResultDevice);
    }

//    @Test
//    void seeIfCreateRoomWorks() {
//        // Arrange
//
//        Room expectedResult = new Room("Kitchen", "1st Floor Kitchen", 1, 1, 1, 1,"Room1","Grid1");
//
//        // Act
//
//        Room actualResult = validHouse.createRoom("Kitchen", "1st Floor Kitchen", 1, 1, 1,
//                1,"Room1","Grid1");
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfGetEnergyConsumptionWorksZero() {
        // Arrange

        double expectedResult = 0.0;

        // Act

        double result = validHouse.getEnergyConsumption(10);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 310;
        Device device = new Fridge(new FridgeSpec());
        device.setNominalPower(31);
        Room tempRoom = new Room("tempRoom", "Sensor's Room", 1, 20, 20, 10, "Room1", "Grid1");
        validHouse.addRoom(tempRoom);
        tempRoom.addDevice(device);

        // Act

        double result = validHouse.getEnergyConsumption(10);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceTypeListSizeWorks() {
        //Act

        int actualResult1 = validHouse.deviceTypeListSize();

        //Assert

        assertEquals(1, actualResult1);
    }

    @Test
    void seeIfSetMotherAreaWorks() {
        //Act
        GeographicArea geoArea = new GeographicArea("Porto", new AreaType("City"), 50, 13, new Local(5, 5, 5));
        validHouse.setMotherArea(geoArea);

        //Assert
        assertEquals(validHouse.getMotherArea(), geoArea);
    }

    @Test
    void seeIfSetGetAddressWorks() {
        //Act
        Address address = new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal");
        validHouse.setAddress(address);

        //Assert
        assertEquals(validHouse.getAddress(), new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal"));
    }

    @Test
    void seeIfIsMotherAreaNullWorks() {
        //Act

        boolean actualResult1 = validHouse.isMotherAreaNull();

        //Assert

        assertFalse(actualResult1);

        //Arrange As Null

        validHouse.setMotherArea(null);

        //Act

        boolean actualResult2 = validHouse.isMotherAreaNull();

        //Assert

        assertTrue(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHouse.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}