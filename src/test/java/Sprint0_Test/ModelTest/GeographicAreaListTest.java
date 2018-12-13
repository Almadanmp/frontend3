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
        GeographicArea ga1 = new GeographicArea("porto",t1, l1);
        GeographicArea ga2 = new GeographicArea("porto",t1,l1);
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
    public void seeIfGetNameThatMatchesNameFromFirstGeoAreaInList() {
        //Arrange
        String stringToTest = "Porto";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicArea expectedResult = new GeographicArea(t1,l1);
        GeographicArea actualResult;
        //Act
        expectedResult.setName(n1);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetNameThatMatchesNameFromMiddletGeoAreaInList() {
        //Arrange
        String stringToTest = "Braga";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicArea expectedResult = new GeographicArea(t1,l2);
        GeographicArea actualResult;
        //Act
        expectedResult.setName(n2);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetNameThatMatchesNameFromLastGeoAreaInList() {
        //Arrange
        String stringToTest = "Lisboa";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);


        GeographicArea expectedResult = new GeographicArea(t1,l3);
        GeographicArea actualResult;
        //Act
        expectedResult.setName(n3);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetsNullWhenListIsEmpty() {
        //Arrange
        String stringToTest = "Lisboa";
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea expectedResult = null;
        GeographicArea actualResult;
        //Act
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsNamesOfGeoAreaList() {
        //Arrange
        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);


        String expectedResult = "Geographic Area List: \n-Porto; \n-Braga; \n-Lisboa;";
        String actualResult;
        //Act
        actualResult = geographicAreaList.printGeoAreaList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfPrintsNamesOfEmptyGeoAreaList() {
        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        String expectedResult = "The list is empty.";
        String actualResult;
        //Act
        actualResult = geographicAreaList.printGeoAreaList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfTrueWhenGeographicAreaWithNameGivenIsFirstInGeographicAreaList() {
        //Arrange
        String stringToTest = "Porto";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.validateIfGeographicAreaToGeographicAreaList(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTrueWhenGeographicAreaWithNameGivenIsSecondInGeographicAreaList() {
        //Arrange
        String stringToTest = "Braga";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.validateIfGeographicAreaToGeographicAreaList(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTrueWhenGeographicAreaWithNameGivenIsLastInGeographicAreaList() {
        //Arrange
        String stringToTest = "Lisboa";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.validateIfGeographicAreaToGeographicAreaList(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfFalseWhenGANameGivenIsNotPresentInGeographicAreaList() {
        //Arrange
        String stringToTest = "Madrid";
        boolean expectedResult = false;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.validateIfGeographicAreaToGeographicAreaList(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfTrueWhenGivenGeoAreaIsFirstInGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(t1,l1);
        gaToTest.setName(n1);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTrueWhenGivenGeoAreaIsInMiddleOfGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(t1,l2);
        gaToTest.setName(n2);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTrueWhenGivenGeoAreaIsInLastOfGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1,l3);
        ga3.setName(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(t1,l3);
        gaToTest.setName(n3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        //Arrange
        boolean expectedResult = false;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        Local l3 = new Local(87, 67);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(t1, l3);
        ga3.setName(n3);

        Local localTest = new Local(98, 54);
        GeographicArea gaToTest = new GeographicArea(t1,localTest);
        gaToTest.setName("Madrid");

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
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
    public void seeIfGetGeographicAreaListOfSameTypeAsLastGeoAreaInList() {
        //Arrange
        String typeToTest = "Freguesia";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74);
        GeographicArea ga3 = new GeographicArea(t3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga3);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetGeographicAreaListOfSameTypeAsFirstGeoAreaInList() {
        //Arrange
        String typeToTest = "Rua";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74);
        GeographicArea ga3 = new GeographicArea(t3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetGeographicAreaListOfSameTypeAsGeoAreaInMiddleOfList() {
        //Arrange
        String typeToTest = "País";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74);
        GeographicArea ga3 = new GeographicArea(t3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetEmptyGeographicAreaListFromTypeDifferentOfTypeInList() {
        //Arrange
        String typeToTest = "Cidade";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetEmptyGeographicAreaListFromEmptyStartingList() {
        //Arrange
        String typeToTest = "Cidade";

        GeographicAreaList geographicAreaList = new GeographicAreaList();

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreasWithoutVerticesFail() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        boolean result = ga1.doGeographicAreasHaveVerticesDetermined(ga1, ga2);
        assertEquals(false, result);
    }

    @Test
    public void seeIfEqualsWhenObjectsAreDifferentButWithSameContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga1);
        geographicAreaList2.addGeographicAreaToGeographicAreaList(ga2);

        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfEqualsToSameObject() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga1);
        geographicAreaList2.addGeographicAreaToGeographicAreaList(ga2);

        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfFalseWhenObjectsAreDifferentWithDifferentContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(54, 98);
        GeographicArea ga3 = new GeographicArea(t3, l3);

        TypeArea t4 = new TypeArea("Freguesia");
        Local l4 = new Local(73, 74);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga3);
        geographicAreaList2.addGeographicAreaToGeographicAreaList(ga4);

        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfFalseWhenObjectsAreFromDifferentClass() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71);
        GeographicArea ga2 = new GeographicArea(t2, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);


        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(l2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}