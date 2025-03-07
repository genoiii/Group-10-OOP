/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for calculating deductions.
 *
 * <p>This class provides static methods to calculate various deductions based on employee data,
 * salary, or other parameters. Extend this class with specific deduction calculation logic as needed.</p>
 */
public class DeductionCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Calculates the SSS deduction based on the gross salary.
     *
     * <p>This method uses a predefined SSS chart stored in a TreeMap, where each key represents a salary threshold and
     * each value represents the corresponding SSS deduction. The method finds the greatest key less than or equal to the
     * given gross salary and returns the corresponding deduction value. If no entry is found (i.e., the gross salary is
     * below the lowest threshold), the method returns 0.0.</p>
     *
     * @param gross the gross salary.
     * @return the SSS deduction corresponding to the gross salary, or 0.0 if below threshold.
     */
    public static double calculateSSS(double gross) {
        // Create a TreeMap to store salary thresholds and corresponding SSS deduction amounts.
        TreeMap<Double, Double> sssChart = new TreeMap<>();
        sssChart.put(3249.00, 135.00);
        sssChart.put(3250.00, 157.50);
        sssChart.put(3750.00, 180.00);
        sssChart.put(4250.00, 202.50);
        sssChart.put(4750.00, 225.00);
        sssChart.put(5250.00, 247.50);
        sssChart.put(5750.00, 270.00);
        sssChart.put(6250.00, 292.50);
        sssChart.put(6750.00, 315.00);
        sssChart.put(7250.00, 337.50);
        sssChart.put(7750.00, 360.00);
        sssChart.put(8250.00, 382.50);
        sssChart.put(8750.00, 405.00);
        sssChart.put(9250.00, 427.50);
        sssChart.put(9750.00, 450.00);
        sssChart.put(10250.00, 472.50);
        sssChart.put(10750.00, 495.00);
        sssChart.put(11250.00, 517.50);
        sssChart.put(11750.00, 540.00);
        sssChart.put(12250.00, 562.50);
        sssChart.put(12750.00, 585.00);
        sssChart.put(13250.00, 607.50);
        sssChart.put(13750.00, 630.00);
        sssChart.put(14250.00, 652.50);
        sssChart.put(14750.00, 675.00);
        sssChart.put(15250.00, 697.50);
        sssChart.put(15750.00, 720.00);
        sssChart.put(16250.00, 742.50);
        sssChart.put(16750.00, 765.00);
        sssChart.put(17250.00, 787.50);
        sssChart.put(17750.00, 810.00);
        sssChart.put(18250.00, 832.50);
        sssChart.put(18750.00, 855.00);
        sssChart.put(19250.00, 877.50);
        sssChart.put(19750.00, 900.00);
        sssChart.put(20250.00, 922.50);
        sssChart.put(20750.00, 945.00);
        sssChart.put(21250.00, 967.50);
        sssChart.put(21750.00, 990.00);
        sssChart.put(22250.00, 1012.50);
        sssChart.put(22750.00, 1035.00);
        sssChart.put(23250.00, 1057.50);
        sssChart.put(23750.00, 1080.00);
        sssChart.put(24250.00, 1102.50);
        sssChart.put(24750.00, 1125.00);
        
        // Get the entry with the greatest key less than or equal to the gross salary.
        Map.Entry<Double, Double> entry = sssChart.floorEntry(gross);
        
        if (entry == null) {
            return 0.0; // Return 0.0 if the gross salary is below the lowest threshold.
        }

        return entry.getValue(); // Return the corresponding SSS deduction value.
    }

    /**
     * Calculates the PhilHealth deduction based on the gross salary.
     *
     * <p>This method calculates the deduction by taking 3% of the gross salary and then applying 50%
     * to that amount. The result is formatted using a predefined decimal format and then converted back
     * to a double.</p>
     *
     * @param gross the gross salary.
     * @return the calculated PhilHealth deduction.
     */
    public static double calculatePhilHealth(double gross) {
        return Double.parseDouble(decimalFormat.format((gross * 0.03) * 0.5)); // Calculate 3% of the gross salary and then take half of that value, format the result.
    }

    /**
     * Calculates the Pag-IBIG deduction based on the gross salary.
     *
     * <p>This method uses a predefined Pag-IBIG chart stored in a TreeMap where each key represents a salary
     * threshold and the associated value represents the deduction rate. The method finds the appropriate rate for
     * the given gross salary, calculates the deduction, caps it at 100 if necessary, formats the result, and returns it as a double.</p>
     *
     * @param gross the gross salary.
     * @return the calculated Pag-IBIG deduction.
     */
    public static double calculatePagIbig(double gross) {
        // Create a TreeMap to store salary thresholds and corresponding Pag-IBIG deduction rates.
        TreeMap<Double, Double> pagibigChart = new TreeMap<>();
        pagibigChart.put(1500.00, 0.01);
        pagibigChart.put(1501.00, 0.02);

        double pagibig = gross * pagibigChart.floorEntry(gross).getValue(); // Get the deduction rate for the given gross salary.
        return Double.parseDouble(decimalFormat.format((pagibig >= 100) ? 100 : pagibig)); // Cap the deduction at 100, format the result, and parse it back to a double.
    }

    /**
     * Calculates the total government contribution.
     *
     * <p>This method adds the SSS, PhilHealth, and Pag-IBIG contributions together, formats the sum using a
     * predefined decimal format, and returns the result as a double.</p>
     *
     * @param sssContri the SSS contribution.
     * @param philhealthContri the PhilHealth contribution.
     * @param pagibigContri the Pag-IBIG contribution.
     * @return the total government contribution.
     */
    public static double calculateGovernmentContribution(double sssContri, double philhealthContri, double pagibigContri) {
        return Double.parseDouble(decimalFormat.format(sssContri + philhealthContri + pagibigContri)); // Sum the contributions and format the total.
    }
    
    /**
     * Calculates the total deductions.
     *
     * <p>This method sums the government contribution and withholding tax, formats the result using a predefined
     * decimal format, and returns the formatted value as a double.</p>
     *
     * @param governmentContribution the total government contribution.
     * @param withHoldingTax the withholding tax amount.
     * @return the total deductions.
     */
    public static double calculateTotalDeductions(double governmentContribution, double withHoldingTax) {
        return Double.parseDouble(decimalFormat.format(governmentContribution + withHoldingTax)); // Sum the government contribution and withholding tax, format the result, and return as a double.
    }
}
