/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Peter Gemmell
* @version 1.0
* @since   2022-09-27
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param intArray the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] intArray) {

        double sum = 0;

        for (int number : intArray) {
            sum += number;
        }

        return sum / intArray.length;
    }

    /**
    * The median() function.
    *
    * @param intArray the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] intArray) {

        final double median;
        final int arrayLength = intArray.length;

        Arrays.sort(intArray);

        if (intArray.length % 2 == 0) {
            median = ((double) intArray[arrayLength / 2]
                    + (double) intArray[arrayLength / 2 - 1]) / 2;
        } else {
            median = (double) intArray[arrayLength / 2];
        }
        return median;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        final double mean;
        final double median;
        final Integer[] arrayOfNumbers;

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        mean = mean(arrayOfNumbers);
        median = median(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);

        System.out.println("\nDone.");
    }
}
