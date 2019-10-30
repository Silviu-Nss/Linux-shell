
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Silviu
 */
public class Reader
{
    private BufferedReader br;
  
    /**
     *
     * @param input = fisierul de input
     */
    public Reader(String input) {
        try {
            br = new BufferedReader(new FileReader(new File(input)));
        }
        catch (FileNotFoundException ex) {
            System.err.println("Nu se poate deschide fisierul");
            System.exit(1);
        }
    }
  
    /**
     *
     * @return = comenzile ce urmeaza sa fie executate
     */
    public ArrayList<String[]> read() {
        ArrayList<String[]> commands = new ArrayList<>();
        String str = null;
        for (;;) {
            try {
            str = br.readLine();
            } 
            catch (IOException ex) {
                System.err.println("Nu se poate citi din buffer");
                System.exit(1);
            }
            if (str == null) {
                break;
            }
            commands.add(str.split("\\s+"));
        }
        try {
            br.close();
        } 
        catch (IOException ex) {
            System.err.println("Nu se poate inchide buffer-ul");
            System.exit(1);
        }
        return commands;
    }
}
