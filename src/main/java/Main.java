import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author Arifullah Jan
 * Date: 9/30/2018
 */
public class Main {


    public static void main(String args[]){
        List<String> list = Arrays.asList(args);





        try {
            File file = new File("sample.txt");
            //Create the file
            if (file.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }



            if(list.contains("--new")){
                DummyASCIIGenerator.writeDummyTextToFile(file,500000,1000);
                System.out.println("New dummy data written");
            }
            else {
                System.out.println("Using previous dummy data.");
            }



            onFileReady(file);





        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static void onFileReady(File file) {
        FileReader fileReader;
        fileReader.
    }

}
