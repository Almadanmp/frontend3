package pt.ipp.isep.dei.project.controller;

import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomConfigurationControllerTest {

    @Test
    public void seeIfSensorIsContainedInGA() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Sensor actualactualResult = ctrl.getSensorFromGAByName("Vento", gA1);
        Sensor expectedactualResult = s1;
        //Assert
        assertEquals(expectedactualResult, actualactualResult);
    }

    @Test
    public void seeIfRoomIsContainedInRoomList() {
        //Arrange
        Room room1 = new Room("Quarto",1,5, 1, 21);
        Room room2 = new Room("Cozinha",1,9, 3, 5);
        RoomList rList = new RoomList();
        House house1 = new House();
        house1.setRoomList(rList);
        rList.addRoom(room1);
        rList.addRoom(room2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room actualactualResult = ctrl.getRoomFromHouseByName("Quarto", house1);
        Room expectedactualResult = room1;
        //Assert
        assertEquals(expectedactualResult, actualactualResult);
    }

    @Test
    public void seeIfSensorListIsContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gList = new GeographicAreaList();
        gList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualactualResult = crl.checkIfGAContainsSensorByString("Vento", ga1);
        //Assert
        assertTrue(actualactualResult);
    }

    @Test
    public void seeIfSensorListIsNotContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gAList = new GeographicAreaList();
        gAList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        boolean actualactualResult = ctrl.checkIfGAContainsSensorByString("Chuva", ga1);
        //Assert
        assertFalse(actualactualResult);
    }

    @Test
    public void seeIfPrintsRoomList() {
        //Arrange
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl= new RoomConfigurationController();
        String expectedactualResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String actualResult = ctrl.printRoomList(house);
        //Assert
        assertEquals(expectedactualResult, actualResult);
    }

    @Test
    void seeIfPrintRoomWorks() {
        //Arrange
        Room room = new Room("room1", 1, 1,2,2);
        //Act
        RoomConfigurationController ctrl= new RoomConfigurationController();
        String actualResult = ctrl.printRoom(room);
        String expectedResult = "room1, 1, 1.0, 2.0, 2.0.\n"; 
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl= new RoomConfigurationController();
        String actualResult = ctrl.printRoomElementsByIndex(list,house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomAreaIndexMatchByString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl= new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchRoomIndexByString("sala",house);
        List<Integer> expectedResult = Collections.singletonList(roomList.getRoomList().indexOf(room1));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchSensorIndexByStringWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sL1 = new SensorList();
        sL1.addSensor(s1);
        sL1.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sL1);
        GeographicAreaList gList1 = new GeographicAreaList();
        gList1.addGeographicAreaToGeographicAreaList(gA1);
        //Act
        String string = "Pluviosidade";
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchSensorIndexByString(string, sL1);
        List<Integer> expectedResult = Collections.singletonList(sL1.getSensorList().indexOf(s2));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.printSensorList(sList);
        String expectedResult = "---------------\n" +
                "0) Name: Vento | Type: Atmosphere\n" +
                "1) Name: Pluviosidade | Type: Pluviosidade\n" +
                "---------------\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}