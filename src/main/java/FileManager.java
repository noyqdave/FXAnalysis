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
                    newFile.createNewFile();
                // happy
            } else {
                newFile = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    // write to file
    public static String writeToFile(File fileWritten, ArrayList<String> lines) {
        String result = new String();
        try {
            FileWriter myWriter = new FileWriter(fileWritten);
            Enumeration<String> lineEnumeration = Collections.enumeration(lines);

            // Enumerate through the lines
            while(lineEnumeration.hasMoreElements())
                myWriter.write(lineEnumeration.nextElement() + "\n");

            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // read a file, passing in a File object
    public static ArrayList<String> readFileObject(File fileReading) {
        ArrayList<String> lines = new ArrayList<String>();
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

    public static ArrayList<String> readFileByName (String pathAndName) {
        ArrayList<String> lines = new ArrayList<String>();
        //Access the file by path and name, returning a File object
        File thisFile = new File(pathAndName);
        if (thisFile.exists()) {
            //Call the readFileObject method
            lines = readFileObject(thisFile);
        }
        return lines;
    }

    // delete a file
    public static Boolean deleteFile(File fileBeingDeleted) {
        boolean result = false;
        try {
            fileBeingDeleted.delete();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
