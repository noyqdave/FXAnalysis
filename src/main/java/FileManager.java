import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManager {

    // create a file
    public static File createFile(String path, String filename) {
        File newFile = new File(path + filename);
        try {
            if (!newFile.exists()) {
                    boolean success = newFile.createNewFile();
            } else {
                newFile = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    // write to file
    public static void writeToFile(File fileWritten, ArrayList<String> lines) {

        try {
            FileWriter myWriter = new FileWriter(fileWritten);

            for (String line : lines) {
                myWriter.write(line + "\n");
            }
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read a file, passing in a File object
    public static ArrayList<String> readFileObject(File fileReading) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(fileReading);
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lines;
    }
    // read a file, passing in a String path and name
    public static ArrayList<String> readFileByName (String pathAndName) {
        ArrayList<String> lines = new ArrayList<>();
        File thisFile = new File(pathAndName);
        if (thisFile.exists()) {
            // now that we have a File object, call the readFileObject method
            lines = readFileObject(thisFile);
        }
        return lines;
    }

    // delete a file
    public static boolean deleteFile(File fileBeingDeleted) {
        boolean result;
        try {
            result = fileBeingDeleted.delete();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
