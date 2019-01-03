package pt.ipp.isep.dei.project.model;


import java.util.Objects;

public class EnergyGrid {
    private String mName;
    private RoomList mListOfRooms;
    private double mMaxPower;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;
    private EnergyGridList mEnergyGridList;

    public EnergyGrid() {
    }

    public EnergyGrid(String name, RoomList listOfRooms, double totalPower, PowerSourceList listPowerSources, DeviceList deviceList, EnergyGridList energyGridList) {
        setName(name);
        setListOfRooms(listOfRooms);
        setMaxPower(totalPower);
        setListPowerSources(listPowerSources);
        setListDevices(deviceList);
        setEnergyGridList(energyGridList);
    }

    public EnergyGrid(String houseGridDesignation, double maxContractedPower) {
        setName(houseGridDesignation);
        setMaxPower(maxContractedPower);
    }

    public String getName() {
        return mName;
    }

    public RoomList getmListOfRooms() {
        return mListOfRooms;
    }

    public double getMaxPower() {
        return mMaxPower;
    }

    public double getTotalPower() {
        double sum = 0;
        for (Device d : mListDevices.getDeviceList()) {
            sum = +d.getmTotalPowerDevice();
        }
        return sum;
    }

    public void setEnergyGridList(EnergyGridList energyGridList) {
        this.mEnergyGridList = energyGridList;
    }

    public PowerSourceList getmListPowerSources() {
        return mListPowerSources;
    }

    public void setListDevices(DeviceList mListDevices) {
        this.mListDevices = mListDevices;
    }

    public void setListOfRooms(RoomList mListOfRooms) {
        this.mListOfRooms = mListOfRooms;
    }

    public void setListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            return mListPowerSources.getPowerSourceList().add(powerSource);
        }
        return false;
    }

    public void setMaxPower(double mMaxPower) {
        this.mMaxPower = mMaxPower;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean addRoomToAEnergyGrid(Room room) {
        if (this.mListOfRooms.addRoom(room)) {
            return true;
        } else {
            return false;
        }
    }

    public String printGrid(){
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getTotalPower();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyGrid eg = (EnergyGrid) o;
        return Objects.equals(mName, eg.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName);
    }
}
