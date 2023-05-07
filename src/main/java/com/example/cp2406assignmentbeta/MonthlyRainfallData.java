package com.example.cp2406assignmentbeta;

/**
 * CP2406 Assignment - Andrew Kisseh
 * Constructor class for storing data for monthly rainfall.
 * Contains getter methods for each variable
 */
public class MonthlyRainfallData {

        private final double total;
        private final double min;
        private final double max;
        private final int month;
        private final int year;

        public MonthlyRainfallData(double newTotal, double newMin, double newMax, int newMonth, int newYear) {
                total = newTotal;
                min = newMin;
                max = newMax;
                month = newMonth;
                year = newYear;
        }

        @Override
        public String toString() {
                return (month + "/" + year + " had " + total + " millimeters of rain");
        }

        public String getDate() {
                return month + "/" + year;
        }

        public double getTotal() {
                return total;
        }

        public double getMin() {
                return min;
        }

        public double getMax() {
                return max;
        }

        public int getMonth() {
                return month;
        }

        public int getYear() {
                return year;
        }

}
