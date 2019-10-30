
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Silviu
 */
public class Writer
{
    private BufferedWriter bw;
  
    /**
     *
     * @param name - numele fisierului de output
     */
    public Writer(String name) {
        try {
            bw = new BufferedWriter(new FileWriter(new File(name)));
        }
        catch (IOException ex) {
            System.err.println("Nu se poate deschide bufferul");
            System.exit(1);
        }
    }
  
    /**
     *
     * @param info - informatia ce trebuie scrisa
     */
    public void write(String info) {
        try {
            bw.write(info);
        }
        catch (IOException ex) {
            System.err.println("Nu se poate scrie in buffer");
            System.exit(1);
        }
    }
    
    /**
     *
     * @param info - informatia ce trebuie scrisa
     */
    public void writeLine(String info) {
        write(info);
        writeNewLine();
    }
  
    /**
     *
     * scrie o linie goala
     */
    public void writeNewLine() {
        try {
            bw.newLine();
        }
        catch (IOException ex) {
            System.err.println("Nu se poate scrie in buffer");
            System.exit(1);
        }
    }    
  
    /**
     * inchide bufferul
     */
    public void close() {
        try {
            bw.close();
        }
        catch (IOException ex) {
            System.err.println("Nu se poate inchide bufferul");
            System.exit(1);
        }
    }
}
