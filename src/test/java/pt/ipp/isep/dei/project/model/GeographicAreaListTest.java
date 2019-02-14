package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */

class GeographicAreaListTest {

    @Test
    void seeIfConstructorGeographicAreaListWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto",t1,2,3,l1);
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
    void seeIfAddsGeographicAreaToGeographicAreaListIfSameAsConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfAddsGeographicAreaToGeographicAreaListIfDifferentFromConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        Local l2 = new Local(87, 67, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t1,2,3,l2);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetNameThatMatchesNameFromFirstGeoAreaInList() {
        //Arrange
        String stringToTest = "Porto";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        ga1.setId(n1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        ga2.setId(n2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);
        ga3.setId(n3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicArea expectedResult = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea actualResult;
        //Act
        expectedResult.setId(n1);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfGetNameThatMatchesNameFromMiddleGeoAreaInList() {
        //Arrange
        String stringToTest = "Braga";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicArea expectedResult = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea actualResult;
        //Act
        expectedResult.setId(n2);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfGetNameThatMatchesNameFromLastGeoAreaInList() {
        //Arrange
        String stringToTest = "Lisboa";

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        GeographicArea expectedResult = new GeographicArea(n3,t1,2,3,l3);
        GeographicArea actualResult;
        //Act
        expectedResult.setId(n3);
        actualResult = geographicAreaList.matchGeoArea(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfGetsNullWhenListIsEmpty() {
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
    void seeIfTrueWhenGeographicAreaWithNameGivenIsFirstInGeographicAreaList() {
        //Arrange
        String stringToTest = "Porto";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.checkIfContainsGAByString(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfTrueWhenGeographicAreaWithNameGivenIsSecondInGeographicAreaList() {
        //Arrange
        String stringToTest = "Braga";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.checkIfContainsGAByString(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfTrueWhenGeographicAreaWithNameGivenIsLastInGeographicAreaList() {
        //Arrange
        String stringToTest = "Lisboa";
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.checkIfContainsGAByString(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfFalseWhenGANameGivenIsNotPresentInGeographicAreaList() {
        //Arrange
        String stringToTest = "Madrid";
        boolean expectedResult = false;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";


        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.checkIfContainsGAByString(stringToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTrueWhenGivenGeoAreaIsFirstInGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(n1,t1,2,3,l1);
        gaToTest.setId(n1);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfTrueWhenGivenGeoAreaIsInMiddleOfGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";


        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);


        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(n2,t1,2,3,l2);
        gaToTest.setId(n2);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTrueWhenGivenGeoAreaIsInLastOfGeographicAreaList() {
        //Arrange
        boolean expectedResult = true;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        GeographicArea gaToTest = new GeographicArea(n3,t1,2,3,l3);
        gaToTest.setId(n3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        //Arrange
        boolean expectedResult = false;
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        Local localTest = new Local(98, 54, 100);
        GeographicArea gaToTest = new GeographicArea("Coimbra",t1,2,3,localTest);
        gaToTest.setId("Madrid");

        GeographicArea ga1 = new GeographicArea(n1,t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea(n2,t1,2,3,l2);
        GeographicArea ga3 = new GeographicArea(n3,t1,2,3,l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetGeographicAreaListOfSameTypeAsLastGeoAreaInList() {
        //Arrange
        String typeToTest = "Freguesia";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);
        GeographicArea ga3 = new GeographicArea("Lisboa",t3,2,3,l3);

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
    void seeIfGetGeographicAreaListOfSameTypeAsFirstGeoAreaInList() {
        //Arrange
        String typeToTest = "Rua";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);
        GeographicArea ga3 = new GeographicArea("Lisboa",t3,2,3,l3);

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
    void seeIfGetGeographicAreaListOfSameTypeAsGeoAreaInMiddleOfList() {
        //Arrange
        String typeToTest = "País";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);
        GeographicArea ga3 = new GeographicArea("Lisboa",t3,2,3,l3);

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
    void seeIfGetEmptyGeographicAreaListFromTypeDifferentOfTypeInList() {
        //Arrange
        String typeToTest = "Cidade";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);

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
    void seeIfGetEmptyGeographicAreaListFromEmptyStartingList() {
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
    void seeIfEqualsWhenObjectsAreDifferentButWithSameContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);

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
    void seeIfEqualsToSameObject() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga1);
        geographicAreaList2.addGeographicAreaToGeographicAreaList(ga2);

        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList1);
        //Assert
        assertTrue(actualResult);
    }
    @Test
    void seeIfFalseWhenObjectsAreDifferentWithDifferentContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(54, 98, 100);

        TypeArea t4 = new TypeArea("Freguesia");
        Local l4 = new Local(73, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);
        GeographicArea ga3 = new GeographicArea("Braga",t3,2,3,l3);
        GeographicArea ga4 = new GeographicArea("Lisboa",t4,2,3,l4);


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
    void seeIfFalseWhenObjectsAreFromDifferentClass() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);

        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Coimbra",t2,2,3,l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicAreaToGeographicAreaList(ga2);


        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(l2);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 10, 20, new Local(21, 33, 18));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 10, 20, new Local(14, 14, 18));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 10, 20, new Local(3, 3, 18));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        String result = gAL1.buildGaWholeListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        GeographicAreaList gAL1 = new GeographicAreaList();
         String expectedResult = "Invalid List - List is Empty\n";
        String result = gAL1.buildGaWholeListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void hashCodeDummyTest(){
        GeographicAreaList gAL1 = new GeographicAreaList();
        int expectedResult = 1;
        int actualResult = gAL1.hashCode();
        Assertions.assertEquals(expectedResult,actualResult);
    }
}