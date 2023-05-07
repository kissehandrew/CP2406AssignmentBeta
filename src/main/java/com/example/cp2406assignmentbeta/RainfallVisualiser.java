package com.example.cp2406assignmentbeta;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

/**
 * CP2406 Assignment Rainfall Visualiser Beta class - Andrew Kisseh
 * The alpha version was rebuilt to construct a StackedBarChart object based on the specified rainfall data.
 * Tooltips are also added to each bar, displaying the actual rainfall value of that bar.
 */
public class RainfallVisualiser {

    public static StackedBarChart<String, Number> getRainfallBarChart(RainfallRecord rainfallRecord) {

        // Create and name the x and y-axis
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Rainfall (mm)");

        // StackedBarChart object representing min, max and total rainfall
        StackedBarChart<String, Number> rainfallChart = new StackedBarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> totals = new XYChart.Series<>();
        totals.setName("Total Rainfall");
        XYChart.Series<String, Number> mins = new XYChart.Series<>();
        mins.setName("Minimum Rainfall");
        XYChart.Series<String, Number> maxs = new XYChart.Series<>();
        maxs.setName("Maximum Rainfall");

        if (rainfallRecord.getFilename() == null) {
            rainfallChart.setTitle("No data loaded");
        } else rainfallChart.setTitle(rainfallRecord.getDateRange());
        rainfallChart.setCategoryGap(0.0);
        rainfallChart.setVerticalGridLinesVisible(false);
        rainfallChart.setHorizontalGridLinesVisible(false);

        /* Loop over each Rainfall data item and assign the minimum, maximum, and total to
        the associated XYChart series. The minMaxDiff and totalDiff functions are used to
        scale the chart properly. Each bar in a StackedBarChart is not stacked on top of the
        preceding one. */

        for (MonthlyRainfallData monthlyRainfallData : rainfallRecord.getRainfallData()) {
            double minMaxDiff = monthlyRainfallData.getMax() - monthlyRainfallData.getMin();
            double totalDiff = monthlyRainfallData.getTotal() - minMaxDiff;

            mins.getData().add(new XYChart.Data<>(monthlyRainfallData.getDate(), monthlyRainfallData.getMin()));
            maxs.getData().add(new XYChart.Data<>(monthlyRainfallData.getDate(), minMaxDiff));
            totals.getData().add(new XYChart.Data<>(monthlyRainfallData.getDate(), totalDiff));
        }

        /* Add all the series data to the StackedBarChart
        Separated into three individual method calls to remove
        unchecked generic array creation for varargs parameter */
        rainfallChart.getData().add(mins);
        rainfallChart.getData().add(maxs);
        rainfallChart.getData().add(totals);

        // Add tooltips for rainfall values
        createTooltips(rainfallRecord, rainfallChart);

        return rainfallChart;

    }

    /**
     * Creates tooltips for each bar in the StackedBarGraph.
     * Depending on whether it is a min, max, or total amount of rain,
     * each tooltip gives the date and the rainfall.
     */
    private static void createTooltips(RainfallRecord rainfallRecord, StackedBarChart<String, Number> rainfallChart) {
        double[] minValues = new double[rainfallRecord.getNumberOfMonths()];
        int i, j = 0;
        String currentRainfallValueType = "";
        for (XYChart.Series<String, Number> newSeries : rainfallChart.getData()) {
            i = 0;
            for (XYChart.Data<String, Number> item : newSeries.getData()) {
                // Get the current data set representation as a string
                switch (j) {
                    case 0 -> currentRainfallValueType = "Minimum";
                    case 1 -> currentRainfallValueType = "Maximum";
                    case (2) -> currentRainfallValueType = "Total";
                }

                String tooltipsMessage = String.format("%s: %s is %.1f millimeters", item.getXValue(), currentRainfallValueType, (item.getYValue().doubleValue() + minValues[i]));
                Tooltip.install(item.getNode(), new Tooltip(tooltipsMessage));
                minValues[i] += item.getYValue().doubleValue();
                i++;
            }
            j++;
        }
    }

}
