
/**
 *
 * @author Silviu
 */
public class CpCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        String src = CommandsManager.modify(args[1]);
        String dst = CommandsManager.modify(args[2]);        
        GenericFile gfs = CommandsManager.getFile(src);
        
        if(gfs == null) {
            err.writeLine("cp: cannot copy " + args[1] + ": No such file or directory");
            return;
        }
        
        GenericFile gfd = CommandsManager.getFile(dst);
        if(gfd == null) {
            err.writeLine("cp: cannot copy into " + args[2] + ": No such directory");
            return;
        }
        
        if(((Folder)gfd).getChild(gfs.getName()) != null) {
            err.writeLine("cp: cannot copy " + args[1] + ": Node exists at destination");
            return;
        }
        ((Folder)gfd).add(gfs.clone((Folder)gfd));
    }
    
}