import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Author Arifullah Jan
 * Date: 9/30/2018
 */
public class Main {


    public static void main(String args[]){
        List<String> list = Arrays.asList(args);

        File file = null;

        try {
            file = new File("sample.txt");
            //Create the fileReader
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }


            if (!list.contains("--nonew")) {
                DummyASCIIGenerator.writeDummyTextToFile(file, 10000000, 1000);
                System.out.println("New dummy data written");
            } else {
                System.out.println("Using previous dummy data.");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        do{


            try {


                onFileReady(file);





            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        while(true);


    }

    private static void onFileReady(File file) throws Exception {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter number of threads: ");
        int noOfThreads = reader.nextInt(); // Scans the next token of the input as an int.

        System.out.println("Enter chuck size: ");
        int chunckSize = reader.nextInt(); // Scans the next token of the input as an int.

        CountManager countManager = new CountManager(file,noOfThreads,chunckSize);
        countManager.init();


    }

}
