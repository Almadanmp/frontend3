package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

/**
 * Controller class for Energy Grid Settings UI
 */


public class EnergyGridSettingsController {

    //SHARED METHODS THROUGH DIFFERENT UIS


    /**
     * @param roomList is the list of Rooms we want to print.
     * @return builds a string of all the individual rooms contained in the list.
     */

    public String buildRoomsString(RoomList roomList) {
        if (roomList == null) {
            return "The Room List wasn't properly initialized. Please try again.";
        }
        return roomList.buildRoomsString();
    }


    /**
     * @param house is the house of which we're going to print the GridList.
     * @return builds a string of all the individual EnergyGrids contained in the house's EnergyGridList.
     */
    public String buildGridListString(House house) {
        return house.buildGridListString();
    }

    /*
     * USER STORY 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse(House programHouse, EnergyGrid energyGrid) {
        return programHouse.addGrid(energyGrid);
    }

    /**
     * @param designation is the name we're going to give to the new EnergyGrid.
     * @param maxPower    is the new grid's maxPower.
     */
    public EnergyGrid createEnergyGrid(House programHouse, String designation, double maxPower) {
        return programHouse.getGridList().createEnergyGrid(designation, maxPower);
    }

    /* USER STORY 135 - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid. */

    /**
     * @param name             is the name we're going to give to the new power source.
     * @param maxPowerOutput   is the new power source's maximum power output.
     * @param maxEnergyStorage is the new power source's maximum capacity.
     */

    public PowerSource createPowerSource(EnergyGrid energyGrid, String name, double maxPowerOutput, double maxEnergyStorage) {
        return energyGrid.getListOfPowerSources().createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    /**
     * @param grid is the grid we're going to add the new powerSource to.
     * @return is true if the power source is added successfully, false if it isn't.
     */

    public boolean addPowerSourceToGrid(EnergyGrid grid, PowerSource powerSource) {
        return grid.addPowerSource(powerSource);
    }
    /* USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    energy consumption is included in that grid. MIGUEL ORTIGAO*/

    /**
     * @param grid is the grid we're going to add a room to.
     * @param room is the room we're going to add to a grid.
     * @return is true if the room is added to the grid successfully, false if it isn't.
     */

    public boolean addRoomToGrid(EnergyGrid grid, Room room) {
        return grid.addRoom(room);
    }

    /*USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed. */

    /**
     * @param grid is the grid we're going to remove a room from.
     * @param room is the room we're going to remove from the grid.
     * @return is true if the room is removed from the grid successfully, false if it isn't.
     */

    public boolean removeRoomFromGrid(EnergyGrid grid, Room room) {
        return grid.removeRoom(room);
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/

    /**
     * This method validates the list of rooms and the list of devices in all rooms.
     * If all the attributes are valid, this method will print the devices, according to their type
     *
     * @param energyGrid - This will be the parameter in which we want to search all the devices
     */

    public String buildListOfDevicesOrderedByTypeString(EnergyGrid energyGrid, House house) {
        return energyGrid.buildDeviceListWithTypeString(energyGrid, house);
    }
}