package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    public static List<String> read(String filename) {
        Scanner file;
        try {
            file = new Scanner(new File(filename));
            List<String> input = new ArrayList<>();
            while(file.hasNextLine()) { input.add(file.nextLine()); }
            file.close();
            return input;
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found: " + e);
        }
        return new ArrayList<>();
    }
}
