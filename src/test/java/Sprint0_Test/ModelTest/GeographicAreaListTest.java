package Sprint0_Test.ModelTest;

import Sprint0.Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class GeographicAreaListTest {

    @Test
    public void seeIfConstructorGeographicAreaListWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga = new GeographicArea(t1, l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga);
        List<GeographicArea> expectedResult = new ArrayList<>();
        List<GeographicArea> actualResult;
        //Act
        expectedResult.add(ga);
        actualResult = geographicAreaList.getGeographicAreaList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddsGeographicAreaToGeographicAreaListIfSameAsConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t1,l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfAddsGeographicAreaToGeographicAreaListIfDifferentFromConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        Local l2 = new Local(87, 67);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t1,l2);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetGeographicAreaList() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga = new GeographicArea(t1, l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga);
        List<GeographicArea> expectedResult = new ArrayList<>();
        List<GeographicArea> actualResult;
        //Act
        expectedResult.add(ga);
        actualResult = geographicAreaList.getGeographicAreaList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetGeographicAreaListOfTypeGiven() {
        //Arrange
        String typeToTest = "Freguesia";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaListOfTypeGiven(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetGeographicAreasAddValidGAs(){
        //Arrange
        Local l1 = new Local(43,55);
        TypeArea t1 = new TypeArea("Cidade");
        Local l2 = new Local(40,50);
        TypeArea t2 = new TypeArea("Rua");
        GeographicArea ga1 = new GeographicArea("Porto",t1, l1);
        GeographicArea ga2 = new GeographicArea("Rua Portuense",t2, l2);
        GeographicAreaList lista = new GeographicAreaList(ga1);
        lista.addGeographicAreaToGeographicAreaList(ga2);
        //Act
        boolean result = lista.setContainerAreaAndContainedArea("Porto","Rua Portuense");
        //Assert
        assertEquals(true,result);
    }

    @Test
    public void seeIfSetGeographicAreasAddInvalidGAs(){
        //Arrange
        Local l1 = new Local(43,55);
        TypeArea t1 = new TypeArea("Cidade");
        Local l2 = new Local(40,50);
        TypeArea t2 = new TypeArea("Rua");
        GeographicArea ga1 = new GeographicArea("Porto",t1, l1);
        GeographicArea ga2 = new GeographicArea("Rua Portuense",t2, l2);
        GeographicAreaList lista = new GeographicAreaList(ga1);
        lista.addGeographicAreaToGeographicAreaList(ga2);
        //Act
        boolean result = lista.setContainerAreaAndContainedArea("Porto","Rua dos Bragas");
        //Assert
        assertEquals(false,result);
    }
}