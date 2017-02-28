import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Anton Skudarnov on 22.02.2017.
 */

/**
 * That class write data in output File.
 */
public class Write {
    private FileWriter writer;
    Write() throws IOException {
        writer = new FileWriter("output.txt");
    }

    /**
     *
     * @param filename The name of output file
     *
     */
    Write(String filename) throws  IOException{
        writer = new FileWriter(filename);
    }
    public void writeData(LinkedList data) throws IOException {
        for( Object d:data){
            writer.write(d.toString()+" ");
        }
        writer.close();
    }
    public void writeInt(int number) throws IOException {
        writer.write(String.valueOf(number));
        writer.close();
    }
    public void writeDouble(double number) throws IOException {
        writer.write(String.valueOf(number));
        writer.close();
    }

    public void writeArray(double[][] data, int height, int width) throws IOException {
        for (int i = 0; i<height;i++){
            for(int j = 0; j<width; j++){
                writer.write(data[i][j]+" ");
            }
            writer.write("\n");
        }
        writer.close();
    }

    public void writeString(String string) throws IOException {
        writer.write(string);
        writer.close();
    }
}
