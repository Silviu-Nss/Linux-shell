
import java.util.ArrayList;

/**
 *
 * @author Nastasescu George-Silviu, 321CB
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reader Rd = new Reader(args[0]);
        ArrayList<String[]> commands = Rd.read();
        CommandsManager mng = new CommandsManager(commands, args[1], args[2]);
        mng.run();
    }
    
}