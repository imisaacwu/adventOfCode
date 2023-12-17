package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> read(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        List<String> input = new ArrayList<>();
        while(file.hasNextLine()) { input.add(file.nextLine()); }
        return input;
    }
}
