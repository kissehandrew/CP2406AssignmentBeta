package com.example.cp2406assignmentbeta;

import java.io.File;
import java.util.ArrayList;

/**
 * CP2406 Assignment - Andrew Kisseh
 * RainfallRecord is used as an array of MonthlyRainfallData objects.
 * Includes methods to analyse the full rainfall data.
 */
public class RainfallRecord {

    private final ArrayList<MonthlyRainfallData> totalRainfallData = new ArrayList<>();
    private String filename = null;

    /**
     * Add a single months' rainfall data to the array created by the RainfallRecord Object.
     * This class has no other setter methods, so any rainfall data added to a RainfallRecord
     * object must contain all constructor values.
     */
    public void addRainfallData(double newTotal, double newMin, double newMax, int newMonth, int newYear) {
        MonthlyRainfallData newData = new MonthlyRainfallData(newTotal, newMin, newMax, newMonth, newYear);
        totalRainfallData.add(newData);
    }

    /**
     * Return the private variable totalRainfallData.
     * Used to iterate through the data of a RainfallRecord object.
     */
    public ArrayList<MonthlyRainfallData> getRainfallData() {
        return totalRainfallData;
    }

    /**
     * Get the number of entries recorded in totalRainfallData.
     */
    public int getNumberOfMonths() {
        return totalRainfallData.size();
    }

    /**
     * Get the date range of a RainfallRecord object.
     * Sets the earliest month and last month to an arbitrary value then calculates the starting month/year
     * and ending month/year
     */
    public String getDateRange() {
        int firstMonth = 13;
        int lastMonth = 0;
        int firstYear = 9999;
        int lastYear = 0;

        for (MonthlyRainfallData monthlyRainfallData : totalRainfallData) {
            if (monthlyRainfallData.getYear() < firstYear) {
                firstYear = monthlyRainfallData.getYear();
                if (monthlyRainfallData.getMonth() < firstMonth)
                    firstMonth = monthlyRainfallData.getMonth();
            }
            else if (monthlyRainfallData.getYear() > lastYear) {
                lastYear = monthlyRainfallData.getYear();
                if (monthlyRainfallData.getMonth() > lastMonth)
                    lastMonth = monthlyRainfallData.getMonth();
            }
        }
        return ("Rainfall data from " + firstMonth + "/" + firstYear + " to " + lastMonth + "/" + lastYear);

    }

    /**
     * Get the maximum total recorded rainfall over the entire recorded data.
     */
    public double getMaxTotalRainfall() {
        double maxTotalRainfall = Double.NEGATIVE_INFINITY;

        for (MonthlyRainfallData monthlyRainfallData : totalRainfallData) {
            if (monthlyRainfallData.getTotal() > maxTotalRainfall)
                maxTotalRainfall = monthlyRainfallData.getTotal();
        }
        return maxTotalRainfall;
    }

    /**
     * Get the lowest recorded rainfall for a single month.
     */
    public double getMinRainfall() {
        double minRainfall = Double.POSITIVE_INFINITY;

        for (MonthlyRainfallData monthlyRainfallData : totalRainfallData) {
            if (monthlyRainfallData.getMin() < minRainfall)
                minRainfall = monthlyRainfallData.getMin();
        }

        return minRainfall;
    }

    /**
     * Get the highest recorded rainfall for a single month.
     */
    public double getMaxRainfall() {
        double maxRainfall = Double.NEGATIVE_INFINITY;

        for (MonthlyRainfallData monthlyRainfallData : totalRainfallData) {
            if (monthlyRainfallData.getMax() > maxRainfall)
                maxRainfall = monthlyRainfallData.getMax();
        }
        return maxRainfall;
    }

    /**
     * Assigns a filename to the RainfallRecord object.
     */
    public void setFilename(String path) {
        File file = new File(path);
        String[] filenameElements = file.getName().trim().split("\\.");
        filename = filenameElements[0];
    }

    /**
     * Get the assigned filename for the RainfallRecord object.
     */
    public String getFilename() { return filename; }

}
