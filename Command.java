
/**
 *
 * @author Silviu
 */
public interface Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    public void execute(String[] args, Writer out, Writer err);
}
