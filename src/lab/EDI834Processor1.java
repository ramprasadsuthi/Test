package lab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EDI834Processor1 {
    public static void main(String[] args) {
        // File path of the sample 834 file
        String filePath = "834file.txt";
        // The segment identifier to look for (e.g., "BGN")
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

    // Method to validate the value of a specific segment using List and parsing
    public static boolean validateSegmentValue(String filePath, String segmentIdentifier, String expectedValue) throws IOException {
        // Read all lines from the file into a single string
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        
        // Split the content into segments based on the delimiter "~"
        List<String> segments = List.of(content.split("~"));
        
        // Use streams to filter and find the segment matching the identifier
        Optional<String> matchingSegment = segments.stream()
                .filter(segment -> segment.startsWith(segmentIdentifier + "*"))
                .findFirst();

        // Check if the matching segment is found and validate its value
        if (matchingSegment.isPresent()) {
            // Split the segment into elements
            List<String> elements = List.of(matchingSegment.get().split("\\*"));
            // Check if the second element matches the expected value
            return elements.size() > 1 && elements.get(1).equals(expectedValue);
        }
        
        return false;
    }
}
