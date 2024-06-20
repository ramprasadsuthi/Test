package lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EDI834Processor {
    public static void main(String[] args) {
        // File path of the sample 834 file
        String filePath = "834file.txt";
        // The segment identifier to look for (e.g., "ISA")
        String segmentIdentifier = "BGN";
        // The expected value for validation
        String expectedValue = "00";

        // Call the method to process the file and validate the segment
        try {
            boolean isValid = validateSegmentValue(filePath, segmentIdentifier, expectedValue);
            if (isValid) {
                System.out.println("Segment value matches the expected result.");
            } else {
                System.out.println("Segment value does not match the expected result.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to validate the value of a specific segment
    public static boolean validateSegmentValue(String filePath, String segmentIdentifier, String expectedValue) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into segments based on the delimiter
                String[] segments = line.split("~");
                for (String segment : segments) {
                    // Further split the segment into elements
                    String[] elements = segment.split("\\*");
                    // Check if the segment identifier matches
                  
                    if (elements[0].equals(segmentIdentifier)) {
                        // Compare the segment value with the expected value
                    	System.out.println("test Print : " + elements[4]);
                        if (elements.length > 1 && elements[1].equals(expectedValue)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
