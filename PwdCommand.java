
/**
 *
 * @author Silviu
 */
public class PwdCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        out.writeLine(CommandsManager.getWdPath());
    }
    
}