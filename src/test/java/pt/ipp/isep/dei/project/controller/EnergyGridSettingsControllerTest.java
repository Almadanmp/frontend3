package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class EnergyGridSettingsControllerTest {


    //USER STORY 145

    @Test
    void seeIfIndexIsMatchedByString() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        List<Integer> result = ctrlUS145.matchGridIndexByString("EG2", house);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridIsPrintedByIndex() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        List<Integer> list = ctrlUS145.matchGridIndexByString("EG1", house);
        String actualResult = ctrlUS145.printEnergyGridByIndex(list, energyGridList);
        String expectedResult = "0) EG1, 400.0, pt.ipp.isep.dei.project.model.PowerSourceList@1.\n";
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfRoomsPrint() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.printRooms(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        Assert.assertEquals(expectedResult, result);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGrid() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        energyGrid1.setListOfRooms(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        boolean result = ctrlUS145.removeRoomFromGrid(energyGrid1, room);
        assertTrue(result);
    }

    @Test
    void seeIfEnergyGridPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.printEnergyGrid(energyGrid1);
        String expectedResult = "Energy Grid: EG1, Max Power: 400.0";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGridListPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.printGridList(house);
        String expectedResult = "---------------\n" +
                "0) Designation: EG1 | Max Power: 400.0\n" +
                "---------------\n";
        Assert.assertEquals(expectedResult, result);
    }
}
