package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomConfigurationControllerTest {

    @Test
    public void seeIfSensorIsAddedToRoom() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        Room Bathroom1 = new Room("Bathroom",1,3);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        House house1 = new House();
        RoomList rlist1 = new RoomList();
        rlist1.addRoom(Bathroom1);
        HouseList hlist1 = new HouseList();
        hlist1.addHouseToHouseList(house1);
        ga1.setHouseList(hlist1);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        crl.addSensorToRoom(Bathroom1,"Vento",ga1);
        crl.addSensorToRoom(Bathroom1,"Pluviosidade",ga1);
        SensorList actualResult = crl.getSensorsFromRoom();
        SensorList expectedResult = slist1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSensorIsContainedInGA() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);


        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Sensor actualResult = crl.getSensorFromName("Vento",ga1);
        Sensor expectedResult = s1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfRoomIsContainedInRoomList() {
        //Arrange

        Room room1 = new Room("Quarto",1,5);
        Room room2 = new Room("Cozinha",1,9);
        GeographicArea ga1 = new GeographicArea();
        RoomList rlist1 = new RoomList();
        House house1 = new House();

        house1.setmRoomList(rlist1);
        rlist1.addRoom(room1);
        rlist1.addRoom(room2);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Room actualResult = crl.getRoomFromName("Quarto",house1);
        Room expectedResult = room1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSensorListIsContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        GeographicAreaList glist1 = new GeographicAreaList();
        glist1.addGeographicAreaToGeographicAreaList(ga1);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualResult = crl.doesSensorListInAGeoAreaContainASensorByName("Vento",glist1);
        //Assert
        assertTrue(actualResult);
    }
}