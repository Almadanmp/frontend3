package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controllerCLI.utils.LogUtils;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaRepository {
    @Autowired
    private static GeographicAreaCrudeRepo geographicAreaCrudeRepo;
    @Autowired
    private static AreaTypeCrudeRepo areaTypeCrudeRepo;
    @Autowired
    SensorTypeCrudeRepo sensorTypeCrudeRepo;

    private static final String BUILDER = "---------------\n";
    private static final String THE_READING = "The reading ";
    private static final String FROM = " from ";


    public GeographicAreaRepository(GeographicAreaCrudeRepo geographicAreaCrudeRepo, AreaTypeCrudeRepo areaTypeCrudeRepo, SensorTypeCrudeRepo sensorTypeCrudeRepo) {
        GeographicAreaRepository.geographicAreaCrudeRepo = geographicAreaCrudeRepo;
        GeographicAreaRepository.areaTypeCrudeRepo = areaTypeCrudeRepo;
        this.sensorTypeCrudeRepo = sensorTypeCrudeRepo;
    }

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public List<GeographicArea> getAll() {
        return geographicAreaCrudeRepo.findAll();
    }

    /**
     * Method that receives a geographic area as a parameter and adds that
     * GA to the list in case it is not contained in that list already.
     *
     * @param geographicAreaToAdd geographic area to be added
     * @return returns true in case the geographic area is added and false if not
     **/
    public boolean addAndPersistGA(GeographicArea geographicAreaToAdd) {
        List<GeographicArea> geographicAreas = getAll();
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            geographicAreaCrudeRepo.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    public void updateGeoArea(GeographicArea area) {
        geographicAreaCrudeRepo.save(area);
    }

    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildStringRepository(List<GeographicArea> geographicAreas) {
        StringBuilder result = new StringBuilder(new StringBuilder(BUILDER));
        if (geographicAreas.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (GeographicArea ga : geographicAreas) {
            result.append(ga.getId()).append(") Name: ").append(ga.getName()).append(" | ");
            result.append("Type: ").append(ga.getAreaTypeID()).append(" | ");
            result.append("Latitude: ").append(ga.getLocal().getLatitude()).append(" | ");
            result.append("Longitude: ").append(ga.getLocal().getLongitude()).append("\n");
        }
        result.append(BUILDER);
        return result.toString();
    }

    /**
     * Method to create a new geographic area before adding it to a GA List.
     *
     * @param newName      input string for geographic area name for the new geographic area
     * @param areaTypeName input string for type area for the new geographic area
     * @param length       input number for length for the new geographic area
     * @param width        input number for width for the new geographic area
     * @param local        input number for latitude, longitude and altitude of the new geographic area
     * @return a new geographic area.
     */
    public GeographicArea createGA(String newName, String areaTypeName, double length, double width, Local local) {
        Logger logger = LogUtils.getLogger("areaTypeLogger", "resources/logs/areaTypeLogHtml.html", Level.FINE);
        AreaType areaType = getAreaTypeByName(areaTypeName, logger);
        LogUtils.closeHandlers(logger);
        if (areaType != null) {
            return new GeographicArea(newName, areaTypeName, length, width, local);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method that returns a GeographicAreaList with a given type.
     *
     * @param typeAreaName is the type of the area we want to get all the geographicAreas.
     * @return a GeographicAreaList with a given type.
     */
    public List<GeographicArea> getGeoAreasByType(List<GeographicArea> geographicAreas, String typeAreaName) {
        List<GeographicArea> finalList = new ArrayList<>();
        for (GeographicArea ga : geographicAreas) {
            String gaType = ga.getAreaTypeID();
            if (gaType.equals(typeAreaName)) {
                finalList.add(ga);
            }
        }
        return finalList;
    }

    /**
     * Checks the geographic area list size and returns the size as int.\
     *
     * @return GeographicAreaList size as int
     **/
    public int size() {
        return getAll().size();
    }

    /**
     * This method receives an index as parameter and gets a geographic area from geographic
     * area list.
     *
     * @param id the index of the GA.
     * @return returns geographic area that corresponds to index.
     */
    public GeographicArea get(long id) {
        Optional<GeographicArea> value = geographicAreaCrudeRepo.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Geographic Area with the selected ID.");
    }

    /**
     * This method checks if a geographic area list is empty
     *
     * @return true if empty, false otherwise
     **/
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    AreaType getAreaTypeByName(String name, Logger logger) {
        Optional<AreaType> value = areaTypeCrudeRepo.findByName(name);
        if (!(value.isPresent())) {
            logger.fine("The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.");
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.orElseGet(() -> new AreaType(name));
        }
    }

    //METHODS FROM AREA SENSOR REPOSITORY

    public AreaSensor createAreaSensor(String id, String name, String sensorName, String sensorUnit, Local local, Date dateStartedFunctioning,
                                       Long geographicAreaId) {

        SensorType sensorType = getTypeSensorByName(sensorName, sensorUnit);
        if (sensorType != null) {
            return new AreaSensor(id, name, sensorType, local, dateStartedFunctioning, geographicAreaId);
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    private SensorType getTypeSensorByName(String name, String unit) {
        Logger logger = LogUtils.getLogger("SensorTypeLogger", "resources/logs/sensorTypeLogHtml.html", Level.FINE);
        Optional<SensorType> value = sensorTypeCrudeRepo.findByName(name);
        if (!(value.isPresent())) {
            String message = "The Sensor Type " + name + "with the unit " + unit + " does not yet exist in the Data Base. Please create the Sensor" +
                    "Type first.";
            logger.fine(message);
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.orElseGet(() -> new SensorType(name, unit));
        }
    }

    /**
     * This method will receive a list of readings, a string of a path to a log file,
     * and a geographic area service and will try to add readings to the given sensors
     * in the given geographic area from the repository.
     *
     * @param readings a list of readings
     * @param logPath  string of a log file path
     * @return the number of readings added
     **/
    public int addReadingsToGeographicAreaSensors(List<Reading> readings, String logPath) {
        Logger logger = LogUtils.getLogger("areaReadingsLogger", logPath, Level.FINE);
        int addedReadings = 0;
        List<String> sensorIds = ReadingUtils.getSensorIDs(readings);
        for (String sensorID : sensorIds) {
            List<Reading> subArray = ReadingUtils.getReadingsBySensorID(sensorID, readings);
            addedReadings += addAreaReadings(sensorID, subArray, logger);
        }
        return addedReadings;
    }

    /**
     * This method receives a String of a given sensor ID, a list of Readings and a Logger,
     * and tries to add the readings to the sensor with the given sensor ID. The sensor will be
     * fetched from the geographic area from the geographic area repository.
     *
     * @param sensorID a string of the sensor ID
     * @param readings a list of readings to be added to the given sensor
     * @param logger   logger
     * @return the number of readings added
     **/
    int addAreaReadings(String sensorID, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        try {
            GeographicArea geographicArea = getGeographicAreaContainingSensorWithGivenId(sensorID);
            AreaSensor areaSensor = geographicArea.getAreaSensorByID(sensorID);
            addedReadings = addReadingsToAreaSensor(areaSensor, readings, logger);
            geographicAreaCrudeRepo.save(geographicArea);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
            }
        }
        return addedReadings;
    }

    /**
     * This method receives a string of a sensor ID and will look in the repository
     * for the geographic area that contains the sensor with the given sensor ID.
     *
     * @param sensorID string of the sensor ID
     * @return the geographic area that contains the sensor with the given ID
     **/
    GeographicArea getGeographicAreaContainingSensorWithGivenId(String sensorID) {
        List<GeographicArea> geographicAreas = geographicAreaCrudeRepo.findAll();
        for (GeographicArea ga : geographicAreas) {
            List<AreaSensor> areaSensors = ga.getAreaSensors();
            for (AreaSensor sensor : areaSensors) {
                String tempSensorID = sensor.getId();
                if (tempSensorID.equals(sensorID)) {
                    return ga;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method receives an Area Sensor, a list of readings and a logger, tries to add the
     * readings to the given Area Sensor, returning the number of readings that were added.
     * The method will log every reading that wasn't added to the Area Sensor.
     *
     * @param areaSensor given Area Sensor
     * @param readings   list of readings to be added to the given Area Sensor
     * @param logger     logger
     * @return number of readings added to the Area Sensor
     **/
    int addReadingsToAreaSensor(AreaSensor areaSensor, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (areaSensor.readingWithGivenDateExists(readingDate)) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because it already exists.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
            } else if (!areaSensor.activeDuringDate(readingDate)) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
            } else {
                areaSensor.addReading(r);
                addedReadings++;
            }
        }
        return addedReadings;
    }
}