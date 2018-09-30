import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public class DummyASCIIGenerator {


    public static char[] characters = ((String)"abcdefghijklmnopqrstuvwyzABCDEFGHIJKLMNOPQRSTUVXYZ~!@#$%^&*()").toCharArray();


    /**
     * Please give divisible values for total and chunk size
     * @param file
     * @param numberOfChars
     * @param chunkSize
     * @throws IOException
     */
    public static void writeDummyTextToFile(File file,int numberOfChars,int chunkSize) throws IOException {
        FileWriter fileWriter = new FileWriter(file);

        for(int i=0; i<numberOfChars/chunkSize;i++){
            String texty = getRandomASCII(chunkSize);
            fileWriter.append(texty);
        }

        fileWriter.close();

    }

    private static String getRandomASCII(int chunkSize) {
        char[] temp = new char[chunkSize];
        for(int i =0; i<chunkSize;i++){
            temp[i] = characters[(int)(Math.random()*characters.length)];
        }
        String s = new String(temp);
        return s;
    }


}
