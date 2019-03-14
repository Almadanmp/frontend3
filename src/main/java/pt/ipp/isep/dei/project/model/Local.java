package pt.ipp.isep.dei.project.model;

import java.util.UUID;

/**
 * Class that represents the Local of an Object .
 */

public class Local {
    private double latitude;
    private double longitude;
    private double altitude;
    private UUID uniqueId;

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Builder Local(), with all the parameters to define a local.
     *
     * @param latitude  of the local
     * @param longitude of the local
     * @param altitude  of the local
     */
    public Local(double latitude, double longitude, double altitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude(altitude);
    }

    /**
     * Setter Altitude
     *
     * @param altitude of the local
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Setter Latitude
     *
     * @param latitude of the local
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Setter Longitude
     *
     * @param longitude of the local
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter Latitude
     *
     * @return Latitude Value
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Getter Longitude
     *
     * @return Longitude value
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Getter Altitude
     *
     * @return Altitude value
     */
    public double getAltitude() {
        return this.altitude;
    }


    /**
     * Method to obtain the linear distance between two locals in Km.
     * We only need the builder with two parameters - Latitude & Longitude.
     *
     * @param local1 Localization 1
     * @return linear distance from Localization 1 to Localization 2
     */
    double getLinearDistanceBetweenLocalsInKm(Local local1) {
        int earthRadiusInKm = 6371;
        double latitude1 = local1.getLatitude();
        double latitude2 = getLatitude();
        double longitude1 = local1.getLongitude();
        double longitude2 = getLongitude();
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLon = Math.toRadians(longitude2 - longitude1);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) * (Math.sin(dLon / 2) * Math.sin(dLon / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusInKm * c;
    }

    /**
     * Specific Method
     *
     * @param testLocal -
     * @return -
     */

    @Override
    public boolean equals(Object testLocal) {
        if (this == testLocal) {
            return true;
        }
        if (!(testLocal instanceof Local)) {
            return false;
        }
        Local localVariable = (Local) testLocal;
        return (java.lang.Double.compare(this.latitude, localVariable.getLatitude()) == 0 && java.lang.Double.compare(this.longitude, localVariable.getLongitude()) == 0);

    }

    /**
     * Specific Method
     *
     * @return -
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
