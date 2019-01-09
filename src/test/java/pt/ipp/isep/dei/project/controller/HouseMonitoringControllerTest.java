package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House Monitoring - controller Tests
 */

public class HouseMonitoringControllerTest {
//SHARED METHODS

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByString() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "cidade";
        list.addTypeArea(type);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(0);
        List<Integer> result;
        result = ctrl.matchTypeAreaIndexByString(input, list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByStringNotMatch() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "distrito";
        list.addTypeArea(type);
        List<Integer> expectedResult = new ArrayList<>();
        List<Integer> result;
        result = ctrl.matchTypeAreaIndexByString(input, list);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGAWholeList() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        list.addTypeArea(type1);
        List<Integer> listIndex = new ArrayList<>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(2);
        String expectedResult = "---------------\n" +
                "0) Type Area: cidade\n" +
                "1) Type Area: distrito\n" +
                "2) Type Area: aldeia\n" +
                "---------------\n";
        String result = ctrl.printTypeAreaElementsByIndex(listIndex, list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetsAverageRainfallOfGA() {
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(21, 33));
        House house = new House("1742", "Costa Cabral", new Local(21, 33), "4450-145");
        house.setmMotherArea(gA1); //TODO Use Constructor instead of set (once constructors are fixed)
        HouseList hL1 = new HouseList();
        hL1.addHouseToHouseList(house);
        gA1.setHouseList(hL1);
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 11, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 11, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 11, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 11, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 11, 1).getTime());
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rain"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rain"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);
        gA1.setSensorList(sensorList);

        HouseMonitoringController US623 = new HouseMonitoringController();
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = 23.5;
        double actualResult = US623.getAVGDailyRainfallOnGivenPeriod(house, dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsGeoAList() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        String result = US623.printGAList(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsGeoAListIfEmpty() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        GeographicAreaList gAL1 = new GeographicAreaList();
        String expectedResult = "Invalid List - List is Empty\n";
        String result = US623.printGAList(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseList() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        House house1 = new House("vacationHouse", "Flower Street", new Local(11, 13), "4230-111");
        House house2 = new House("workHouse", "Torrinha", new Local(12, 12), "4345-000");
        House house3 = new House("dreamHouse", "New York", new Local(122, 122), "6666-000");
        HouseList hL1 = new HouseList(house1);
        hL1.addHouseToHouseList(house2);
        hL1.addHouseToHouseList(house3);
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);
        String expectedResult = "---------------\n" +
                "0) Designation: vacationHouse | Address: Flower Street | ZipCode: 4230-111\n" +
                "1) Designation: workHouse | Address: Torrinha | ZipCode: 4345-000\n" +
                "2) Designation: dreamHouse | Address: New York | ZipCode: 6666-000\n" +
                "---------------\n";
        String result = US623.printHouseList(gA1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseListIfEmpty() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        HouseList hL1 = new HouseList();
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = US623.printHouseList(gA1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeCreateDate() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        int year = 2018;
        int month = 1;
        int day = 13;
        Date expectedResult = new GregorianCalendar(year, month, day).getTime();
        Date result = US623.createDate(year, month, day);
        assertEquals(expectedResult, result);
    }


    //***************************************************************************************************************/
    //***************************************************************************************************************/

    @Test
    public void seeIfGetSumOfReadingInGivenDay() {
        //Arrange
        ReadingList rList1 = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r0 = new Reading(5, g0.getTime());
        Reading r1 = new Reading(25, g1.getTime());
        rList1.addReading(r0);
        rList1.addReading(r1);

        ReadingList rList2 = new ReadingList();
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r2 = new Reading(1, g2.getTime());
        Reading r3 = new Reading(20, g3.getTime());
        rList2.addReading(r2);
        rList2.addReading(r3);

        ReadingList rList3 = new ReadingList();
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r4 = new Reading(1, g4.getTime());
        Reading r5 = new Reading(20, g5.getTime());
        rList3.addReading(r4);
        rList3.addReading(r5);

        ReadingList rList4 = new ReadingList();
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r6 = new Reading(1, g6.getTime());
        Reading r7 = new Reading(30, g7.getTime());
        rList4.addReading(r6);
        rList4.addReading(r7);

        ReadingList rList5 = new ReadingList();
        GregorianCalendar g8 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g9 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r8 = new Reading(1, g8.getTime());
        Reading r9 = new Reading(15, g9.getTime());
        rList5.addReading(r8);
        rList5.addReading(r9);

        ReadingList rList6 = new ReadingList();
        GregorianCalendar g10 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g11 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r10 = new Reading(5, g10.getTime());
        Reading r11 = new Reading(25, g11.getTime());
        rList6.addReading(r10);
        rList6.addReading(r11);

        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        Sensor s1 = new Sensor("XV1", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("XV2", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s3 = new Sensor("XV3", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s4 = new Sensor("XV4", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s5 = new Sensor("XV5", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s6 = new Sensor("XV6", new TypeSensor("Motion"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        s1.setReadingList(rList1);
        s2.setReadingList(rList2);
        s3.setReadingList(rList3);
        s4.setReadingList(rList4);
        s5.setReadingList(rList5);
        s6.setReadingList(rList6);
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        slist1.addSensor(s3);
        slist1.addSensor(s4);
        slist1.addSensor(s5);
        slist1.addSensor(s6);
        ga1.setSensorList(slist1);
        House casa1 = new House();

        //Act
        double expectedResult = 1;
        HouseMonitoringController ctrl = new HouseMonitoringController();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = ctrl.getTotalRainfallOnGivenDayHouseArea(ga1, dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void testeCenas() {
        //Arrange
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        SensorList mSensorList = new SensorList();
        TypeAreaList mTypeAreaList = new TypeAreaList();
        HouseList mHouseList = new HouseList();
        House house = new House("casa", "coise", new Local(4, 4), "coise");
        mHouseList.addHouseToHouseList(house);
        RoomList mRoomList = new RoomList();
        EnergyGridList mEnergyGridList = new EnergyGridList();
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, new GregorianCalendar(2018, 8, 6).getTime());
        Reading reading1 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        Reading reading3 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        readingList.addReading(reading);
        readingList.addReading(reading1);
        readingList.addReading(reading3);
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(sensor1);
        sensorList.addSensor(sensor2);
        GeographicArea geoa = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4), sensorList, mHouseList);
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4), sensorList, mHouseList);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        Room room = new Room("cozinha", 8, 2, sensorList);
        mRoomList.addRoom(room);

        //Act
        double expectedResult = 30;
        HouseMonitoringController ctrl = new HouseMonitoringController();
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 6);
        Date dateToTest = cal.getTime();
        double actualResult = ctrl.getTotalRainfallOnGivenDayHouseArea(geoa, dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetSumOfReadingInGivenDayReturn0() {
        //In case sensor empty
        //Arrange ---------------------------------------
        ReadingList rList1 = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r0 = new Reading(5, g0.getTime());
        Reading r1 = new Reading(25, g1.getTime());
        rList1.addReading(r0);
        rList1.addReading(r1);

        ReadingList rList2 = new ReadingList();
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r2 = new Reading(1, g2.getTime());
        Reading r3 = new Reading(20, g3.getTime());
        rList2.addReading(r2);
        rList2.addReading(r3);

        ReadingList rList3 = new ReadingList();
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r4 = new Reading(1, g4.getTime());
        Reading r5 = new Reading(20, g5.getTime());
        rList3.addReading(r4);
        rList3.addReading(r5);

        ReadingList rList4 = new ReadingList();
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r6 = new Reading(1, g6.getTime());
        Reading r7 = new Reading(30, g7.getTime());
        rList4.addReading(r6);
        rList4.addReading(r7);

        ReadingList rList5 = new ReadingList();
        GregorianCalendar g8 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g9 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r8 = new Reading(1, g8.getTime());
        Reading r9 = new Reading(15, g9.getTime());
        rList5.addReading(r8);
        rList5.addReading(r9);

        ReadingList rList6 = new ReadingList();
        GregorianCalendar g10 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g11 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r10 = new Reading(5, g10.getTime());
        Reading r11 = new Reading(25, g11.getTime());
        rList6.addReading(r10);
        rList6.addReading(r11);

        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        SensorList sList1 = new SensorList();
        ga1.setSensorList(sList1);
        //Act -------------------------------------------
        double expectedResult = 0;
        HouseMonitoringController ctrl = new HouseMonitoringController();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = ctrl.getTotalRainfallOnGivenDayHouseArea(ga1, dateToTest);
        //Assert ----------------------------------------
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void SeeIfGetMaxTemperatureInARoomOnAGivenDay() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2, roomList);
        double expectedResult = 30.0;
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksNegatives() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(-30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2, roomList);
        double expectedResult = 20.0;
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDates() {
        //Arrange -----------------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(20, d2);
        Reading r3 = new Reading(25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act ---------------------------------------------------------------
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d3, roomList);
        double expectedResult = 25.0;
        //Assert ------------------------------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDatesAndNeg() {
        //Arrange -----------------------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(-20, d2);
        Reading r3 = new Reading(-25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act ---------------------------------------------------------------------
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d3, roomList);
        double expectedResult = -25.0;
        //Assert ------------------------------------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }


    @Test
    public void seeIfGetSensorWithTheMinimumDistanceToHouseWorks() {
        //Arrange --------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        //Act ------------------------------------------------------
        Sensor result = ctrl.getSensorWithTheMinimumDistanceToHouse(house, ga);
        //Assert ---------------------------------------------------
        assertEquals(s1, result);
    }

    @Test
    public void seeIfGetSensorWithTheMinimumDistanceToHouseWorks2() {
        //Arrange ---------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        //Act -------------------------------------------------------
        Sensor result = ctrl.getSensorWithTheMinimumDistanceToHouse(house, ga);
        //Assert ----------------------------------------------------
        assertEquals(s2, result);
    }

    @Test
    public void seeIfGetCurrentRoomTemperatureWorks() {
        //Arrange -------------------------------------
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("Temperature");
        ReadingList listR = new ReadingList();
        Date d = new GregorianCalendar(2018, 3, 1).getTime();
        Date d1 = new GregorianCalendar(2018, 3, 1, 15, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2018, 3, 1, 17, 0, 0).getTime();
        Date d3 = new GregorianCalendar(2018, 3, 1, 16, 0, 0).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        r1 = new Reading(15, d1);
        r2 = new Reading(20, d2);
        r3 = new Reading(30, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("Temp Sensor 1", tipo, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        roomList.addRoom(room);
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Act -----------------------------------------
        double result = ctrl.getCurrentRoomTemperature(d, roomList);
        double expectedResult = 20;
        //Assert --------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
        //Arrange -----------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Reading List
        Reading r1 = new Reading(30, new GregorianCalendar(2018, 8, 9, 17, 0).getTime());
        Reading r2 = new Reading(40, new GregorianCalendar(2018, 8, 9, 22, 0).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        //Sensor List
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime(), readingList);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime(), readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        //Geo Area List
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        //House List
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        //Act ---------------------------------------------------
        double result = ctrl.getCurrentTemperatureInTheHouseArea(house, ga);
        double expectedResult = 40;
        //Assert ------------------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void SeeIfPrintGAWorks() {
        //Arrange -------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        //Act -----------------------
        String result = ctrl.printGA(ga);
        String expectedResult = "porto, cidade, 4.0º lat, 4.0º long\n";
        //Assert --------------------
        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange -----------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfmMatchHouseIndexByString() {
        //Arrange -------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house2 = new House("houseTwo", "Address2", new Local(4, 4), "3456-123");
        House house3 = new House("house3", "Address3", new Local(18, 10), "3555-555");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house2);
        houseList1.addHouseToHouseList(house3);
        //Act -----------------------------------
        List<Integer> result = ctrl.matchHouseIndexByString("house3", geoa1);
        List<Integer> expectedResult = new ArrayList<>();
        Integer i = 1;
        expectedResult.add(i);
        //Assert --------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintHouseElementsByIndex() {
        //Arrange --------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house2 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        House house3 = new House("house3", "Address3", new Local(18, 10), "3555-555");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house2);
        houseList1.addHouseToHouseList(house3);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printHouseElementsByIndex(list, geoa1);
        //Assert ---------------------------------
        String expectedResult = "1) house3, Address3, 3555-555.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintHouseWorks() {
        //Arrange ----------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Act --------------------------
        String result = ctrl.printHouse(geoa1.getHouseList().getHouseList().get(0));
        String expectedResult = "a minha rica casinha, Address2, 3456-123.\n";
        //Assert -----------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchRoomIndexByString() {
        //Arrange -----------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ---------------------------------
        List<Integer> result = ctrl.matchRoomIndexByString("kitchen", house1);
        List<Integer> expectedResult = new ArrayList<>();
        Integer i = 1;
        expectedResult.add(i);
        //Assert ------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomElementsByIndex() {
        //Arrange --------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        Room room3 = new Room("room1", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        roomList1.addRoom(room3);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printRoomElementsByIndex(list, house1);
        String expectedResult = "1) kitchen, 8, 2.0.\n";
        //Assert ---------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomListWork() {
        //Arrange ------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ----------------------------
        String result = ctrl.printRoomList(house1);
        String expectedResult = "---------------\n" +
                "0) Designation: room1 | House Floor: 19 | Dimensions: 2.3456789E7\n" +
                "1) Designation: kitchen | House Floor: 8 | Dimensions: 2.0\n" +
                "---------------\n";
        //Assert -------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomWorks() {
        //Arrange ---------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act -------------------------
        String result = ctrl.printRoom(room2);
        String expectedResult = "kitchen, 8, 2.0.\n";
        //Assert ----------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfprintSensorElementsByIndexWorks() {
        //Arrange --------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Sensors
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura"), new Local(4, 4), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        SensorList slist = new SensorList();
        slist.addSensor(s1);
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789, slist);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        house1.setmRoomList(roomList1);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 0;
        list.add(i);
        String result = ctrl.printSensorElementsByIndex(list, room1);
        String expectedResult = "0) sensor which is a temperatura sensor.\n";
        //Assert ---------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfprintSensorListWorks() {
        //Arrange --------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Sensors
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura"), new Local(4, 4), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        SensorList slist = new SensorList();
        slist.addSensor(s1);
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789, slist);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printSensorList(room1);
        String expectedResult = "---------------\n" +
                "0) Designation: sensor | Sensor Type: temperatura\n" +
                "---------------\n";
        //Assert ---------------------------------
        Assert.assertEquals(expectedResult, result);
    }


    @Test
    void seeIfMatcSensorIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        SensorList sensorList = new SensorList();
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura"), new Local(4, 4), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura"), new Local(4, 4), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        Room room = new Room("cozinha", 1, 1, sensorList);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchSensorIndexByString("sensor", room);
        List<Integer> expectedResult = Collections.singletonList(sensorList.getSensorList().indexOf(s1));
        //Assert --------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfprintSensorWorks() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura"), new Local(4, 4), new GregorianCalendar(7, 7, 7).getTime());
        String result = ctrl.printSensor(s1);
        String expected = "sensor, temperatura, 4.0º lat, 4.0º long\n";
        assertEquals(expected, result);
    }

    @Test
    public void seeIfprintSensorListWorksInvalid() {
        //Arrange --------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Sensors
        SensorList slist = new SensorList();
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789, slist);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printSensorList(room1);
        String expectedResult = "Invalid List - List is Empty\n";
        //Assert ---------------------------------
        Assert.assertEquals(expectedResult, result);
    }

}
