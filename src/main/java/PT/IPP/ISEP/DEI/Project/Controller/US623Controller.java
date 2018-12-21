package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.House;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * US623UI: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 **/

public class US623Controller {

    public double getAVGDailyRainfallOnGivenPeriod(House house, Date minDay, Date maxDay) {
        GeographicArea geoAreaHouse = house.getmMotherGA();
        return geoAreaHouse.getAvgReadingsFromSensorTypeInGA("Rain", minDay, maxDay);
    }

    public Date createDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        return date;
    }
}

