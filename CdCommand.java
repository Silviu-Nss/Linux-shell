
/**
 *
 * @author Silviu
 */
public class CdCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        String path = CommandsManager.modify(args[1]);
        GenericFile gf = CommandsManager.getFile(path);
        if(gf == null || !(gf instanceof Folder)) {
            err.writeLine("cd: " + args[1] + ": No such directory");
            return;
        }
        CommandsManager.setWd((Folder)gf);
        CommandsManager.setWdPath(CommandsManager.getPath(path));
    }
    
}