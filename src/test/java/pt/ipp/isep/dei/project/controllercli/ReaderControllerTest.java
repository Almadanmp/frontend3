package pt.ipp.isep.dei.project.controllercli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.ReaderXMLGeoArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * ReaderController test class.
 */
@ExtendWith(MockitoExtension.class)
class ReaderControllerTest {

    // Common artifacts for testing in this class.

    private EnergyGridRepository energyGridRepository;
    private ReaderXMLGeoArea validReaderXMLGeoArea;
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private Date validDate4 = new Date();

    private AreaSensor validAreaSensor1;
    private RoomSensor validRoomSensor1;
    private ReadingUtils readingUtils;

    private RoomRepository roomRepository;
    private GeographicArea validGeographicArea;
    private GeographicArea validGeographicArea2;
    private SensorType validSensorTypeTemp;
    private AreaTypeRepository areaTypeRepository;


    private static final String validLogPath = "dumpFiles/dumpLogFile.html";
    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log"; //Não apagar p.f.

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    GeographicAreaCrudeRepo geographicAreaCrudeRepo;

    @Mock
    HouseCrudeRepo houseCrudeRepo;

    @Mock
    RoomCrudeRepo roomCrudeRepo;

    @Mock
    EnergyGridCrudeRepo energyGridCrudeRepo;

    @Mock
    AreaTypeCrudeRepo areaTypeCrudeRepo;

    @Mock
    SensorTypeCrudeRepo sensorTypeCrudeRepo;

    @InjectMocks
    private ReaderController readerController;

    @BeforeEach
    void arrangeArtifacts() {
        this.energyGridRepository = new EnergyGridRepository(energyGridCrudeRepo);
        this.roomRepository = new RoomRepository(roomCrudeRepo);
        validReaderXMLGeoArea = new ReaderXMLGeoArea();
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("2016-11-15");
            validDate2 = validSdf.parse("2016-11-15");
            validDate3 = validSdf.parse("2017-11-15");
            validDate4 = validSdf.parse("2017-11-16");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validGeographicArea = new GeographicArea("ISEP", "urban area", 0.249, 0.261,
                new Local(41.178553, -8.608035, 111));
        validGeographicArea2 = new GeographicArea("Porto", "city", 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));
        GeographicArea emptyGeographicArea = new GeographicArea("Lisbon", "city", 0.299, 0.291,
                new Local(41.178553, 8.608035, 117));
        validAreaSensor1 = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", "rain",
                new Local(41.179230, -8.606409, 125),
                validDate1);
        AreaSensor validAreaSensor2 = new AreaSensor("TT12346", "Meteo station ISEP - temperature", "rain2",
                new Local(41.179230, -8.606409, 125),
                validDate2);
        AreaSensor validAreaSensor3 = new AreaSensor("RF12334", "Meteo station CMP - rainfall", "rain2",
                new Local(41.179230, -8.606409, 139),
                validDate3);
        AreaSensor validAreaSensor4 = new AreaSensor("TT1236A", "Meteo station CMP - temperature", "rain2",
                new Local(41.179230, -8.606409, 139),
                validDate4);
        validSensorTypeTemp = new SensorType("Temperature", "C");
        validRoomSensor1 = new RoomSensor("SensorID1", "SensorOne", validSensorTypeTemp.getName(), validDate1);
        areaTypeRepository = new AreaTypeRepository(areaTypeCrudeRepo);
    }

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        logger.setLevel(Level.FINE);
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void seeIfAddReadingsToRoomSensorsWorks() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Room room = new Room("Room1", "Description", 1, 1, 1, 1, "House");
        rooms.add(room);
        room.addSensor(validRoomSensor1);

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setSensorId("SensorID1");
        readingDTO1.setValue(20D);
        readingDTO1.setUnit("C");
        readingDTO1.setDate(validDate4);
        readingDTOS.add(readingDTO1);

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setSensorId("SensorID1");
        readingDTO2.setValue(20D);
        readingDTO2.setUnit("C");
        readingDTO2.setDate(validDate1);
        readingDTOS.add(readingDTO2);

        Mockito.when(roomCrudeRepo.findAll()).thenReturn(rooms);

        // Act

        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath, roomRepository);

        // Assert

        assertEquals(2, actualResult);
    }

    @Test
    void seeIfAddReadingsToGeographicAreaSensorsWorks() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setSensorId("RF12345");
        readingDTO1.setValue(20D);
        readingDTO1.setUnit("C");
        readingDTO1.setDate(validDate1);
        readingDTOS.add(readingDTO1);

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setSensorId("RF12345");
        readingDTO2.setValue(20D);
        readingDTO2.setUnit("C");
        readingDTO2.setDate(validDate3);
        readingDTOS.add(readingDTO2);

        Mockito.when(geographicAreaRepository.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath)).thenReturn(2);
        // Act
        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath, geographicAreaRepository);

        // Assert

        assertEquals(2, actualResult);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWrongPath() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(0, areasAdded);
    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksWithoutGeoAreas() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_no_GAs.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(0, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWrongPathNotXml() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.json");
        String absolutePath = fileToRead.getAbsolutePath();

        // Assert

        assertThrows(IllegalArgumentException.class, () -> validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository));
    }

    // TODO - move tests to correct class (should be ReaderXMLGeoAreaTest)
//    @Test
//    void seeIfReadFileXMLGeoAreaWorks() {
//        // Arrange
//
//        // Act
//
//        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
//        String absolutePath = fileToRead.getAbsolutePath();
//
//        AreaType city = new AreaType("city");
//        AreaType urbanArea = new AreaType("urban area");
//
//        Mockito.when(areaTypeCrudeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
//        Mockito.when(areaTypeCrudeRepo.findByName("city")).thenReturn(Optional.of(city));
//
//        SensorType rainfall = new SensorType("rainfall", "mm");
//        SensorType temperature = new SensorType("temperature", "C");
//
//        Mockito.when(sensorTypeCrudeRepo.findByName("rainfall")).thenReturn(Optional.of(rainfall));
//        Mockito.when(sensorTypeCrudeRepo.findByName("temperature")).thenReturn(Optional.of(temperature));
//
//        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(2, areasAdded);
//
//    }


    // TODO - move tests to correct class (should be ReaderXMLGeoAreaTest)
//    @Test
//    void seeIfReadFileXMLGeoAreaWorksWithAnotherDateFormat() {
//        // Arrange
//
//        // Act
//
//        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_wrong_date.xml");
//        String absolutePath = fileToRead.getAbsolutePath();
//
//        AreaType city = new AreaType("city");
//        AreaType urbanArea = new AreaType("urban area");
//
//        Mockito.when(areaTypeCrudeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
//
//        Mockito.when(areaTypeCrudeRepo.findByName("city")).thenReturn(Optional.of(city));
//
//        SensorType rainfall = new SensorType("rainfall", "mm");
//        SensorType temperature = new SensorType("temperature", "C");
//
//        Mockito.when(geographicAreaRepository.createGA(validGeographicArea.getName(), validGeographicArea.getAreaTypeID(), validGeographicArea.getWidth(), validGeographicArea.getLength(), validGeographicArea.getLocal())).thenReturn(validGeographicArea);
//        Mockito.when(geographicAreaRepository.createGA(validGeographicArea.getName(), validGeographicArea2.getAreaTypeID(), validGeographicArea2.getWidth(), validGeographicArea2.getLength(), validGeographicArea2.getLocal())).thenReturn(validGeographicArea2);
//        Mockito.when(geographicAreaRepository.addAndPersistGA(validGeographicArea)).thenReturn(true);
//        Mockito.when(geographicAreaRepository.addAndPersistGA(validGeographicArea2)).thenReturn(true);
//        Mockito.when(geographicAreaRepository.updateGeoArea(validGeographicArea)).thenReturn(validGeographicArea);
//        Mockito.when(geographicAreaRepository.updateGeoArea(validGeographicArea2)).thenReturn(validGeographicArea);
//
//
//        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(2, areasAdded);
//    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksWithOneGeoArea() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeCrudeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));

        Mockito.when(geographicAreaRepository.createGA(validGeographicArea.getName(), validGeographicArea.getAreaTypeID(), validGeographicArea.getWidth(), validGeographicArea.getLength(), validGeographicArea.getLocal())).thenReturn(validGeographicArea);
        Mockito.when(geographicAreaRepository.addAndPersistGA(validGeographicArea)).thenReturn(true);
        Mockito.when(geographicAreaRepository.updateGeoArea(validGeographicArea)).thenReturn(validGeographicArea);

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(1, areasAdded);
    }

    // TODO - move tests to correct class (should be ReaderXMLGeoAreaTest)
//    @Test
//    void seeIfReadAndAddAreasWorks() {
//        // Arrange
//
//        AreaType city = new AreaType("city");
//        AreaType urbanArea = new AreaType("urban area");
//
//        Mockito.when(areaTypeCrudeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
//        Mockito.when(areaTypeCrudeRepo.findByName("city")).thenReturn(Optional.of(city));
//
//        SensorType rainfall = new SensorType("rainfall", "mm");
//        SensorType temperature = new SensorType("temperature", "C");
//
//        Mockito.when(sensorTypeCrudeRepo.findByName("rainfall")).thenReturn(Optional.of(rainfall));
//        Mockito.when(sensorTypeCrudeRepo.findByName("temperature")).thenReturn(Optional.of(temperature));
//
//        // Act
//
//        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
//        String absolutePath = fileToRead.getAbsolutePath();
//
//        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(2, areasAdded);
//    }

    // TODO - move tests to correct class (should be ReaderXMLGeoAreaTest)
//    @Test
//    void seeIfReadAndAddAreasWorksWithRepeatedArea() {
//        // Arrange
//
//        AreaType city = new AreaType("city");
//        AreaType urbanArea = new AreaType("urban area");
//
//        Mockito.when(areaTypeCrudeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
//        Mockito.when(areaTypeCrudeRepo.findByName("city")).thenReturn(Optional.of(city));
//
//        SensorType rainfall = new SensorType("rainfall", "mm");
//        SensorType temperature = new SensorType("temperature", "C");
//
//        Mockito.when(sensorTypeCrudeRepo.findByName("rainfall")).thenReturn(Optional.of(rainfall));
//        Mockito.when(sensorTypeCrudeRepo.findByName("temperature")).thenReturn(Optional.of(temperature));
//
//        GeographicAreaDTO firstArea = new GeographicAreaDTO();
//        firstArea.setName("ISEP");
//        firstArea.setDescription("Campus do ISEP");
//        firstArea.setTypeArea("urban area");
//        firstArea.setWidth(0.261);
//        firstArea.setLength(0.249);
//        firstArea.setLocalDTO(new LocalDTO(41.178553, -8.608035, 111));
//
//        // Populate expectedResult array
//
//        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);
//
//        List<GeographicArea> validList = new ArrayList<>();
//        validList.add(areaOne);
//
//        Mockito.when(geographicAreaCrudeRepo.findAll()).thenReturn(validList);
//
//        // Act
//
//        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
//        String absolutePath = fileToRead.getAbsolutePath();
//
//        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(0, areasAdded);
//    }

    //   @Test
//    void addReadingsToGeographicAreaSensorsWorks() { //TODO TERESA revisitar este teste
//        //Arrange
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("TT");
//        readingDTO1.setUnit("C");
//        readingDTO1.setValue(2D);
//        readingDTO1.setDate(validDate1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("TT");
//        readingDTO2.setUnit("C");
//        readingDTO2.setValue(2D);
//        readingDTO2.setDate(validDate3);
//
//        readingDTOS.add(readingDTO1);
//        readingDTOS.add(readingDTO2);
//
//        AreaSensor sensor = new AreaSensor("TT", "Sensor", new SensorType(), new Local(2, 2, 2), validDate1, 2L);
//
//        Mockito.when(areaSensorRepository.findById("TT")).thenReturn(Optional.of(sensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, "TT")).thenReturn((null));
//
//        //Act
//
//        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }

//    @Test
//    void seeIfAddReadingsToHouseSensorsWorks() {
//        //Arrange
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("TT");
//        readingDTO1.setUnit("C");
//        readingDTO1.setValue(2D);
//        readingDTO1.setDate(validDate1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("TT");
//        readingDTO2.setUnit("C");
//        readingDTO2.setValue(2D);
//        readingDTO2.setDate(validDate3);
//
//        readingDTOS.add(readingDTO1);
//        readingDTOS.add(readingDTO2);
//
//        RoomSensor sensor = new RoomSensor("TT", "Sensor", new SensorType("temperature", "C"), validDate1, "B104");
//
//        Mockito.when(houseSensorRepository.findById("TT")).thenReturn(Optional.of(sensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, "TT")).thenReturn((null));
//
//        //Act
//
//        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }

//    @Test
//    void seeIfReadJSONAndDefineHouseWorks() {
//        //Arrange
//
//        List<String> deviceTypes = new ArrayList<>();
//        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
//        String filePath = "src/test/resources/houseFiles/DataSet_sprint06_House.json";
//        Address expectedResult = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
//
//        //Assert
//
//        assertTrue(readerController.readJSONAndDefineHouse(house, filePath, energyGridRepository, houseRepository, roomService));
//        assertEquals(expectedResult, house.getAddress());
//    }

    @Test
    void seeIfReadJSONAndDefineHouseThrowsException() {
        List<String> deviceTypes = new ArrayList<>();
        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
        String filePath = "src/test/resources/readingsFiles/DataSet_sprint05_SensorData.json";
        assertThrows(IllegalArgumentException.class,
                () -> readerController.readJSONAndDefineHouse(house, filePath, energyGridRepository, houseCrudeRepo, roomRepository));

    }
}
