
/**
 *
 * @author Silviu
 */
public class MvCommand implements Command {

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
            err.writeLine("mv: cannot move " + args[1] + ": No such file or directory");
            return;
        }
        GenericFile gfd = CommandsManager.getFile(dst);
        if(gfd == null) {
            err.writeLine("mv: cannot move into " + args[2] + ": No such directory");
            return;
        }
        if(((Folder)gfd).getChild(gfs.getName()) != null) {
            err.writeLine("mv: cannot move " + args[1] + ": Node exists at destination");
            return;
        }
        /* Adaugam in destinatie clona folderului si stergem originalul */
        ((Folder)gfd).add(gfs.clone((Folder)gfd));
        String absSrc = CommandsManager.getPath(src); /* cale absoluta src */
        String absDst = CommandsManager.getPath(dst); /* cale absoluta dest */
        String wdPath = CommandsManager.getWdPath();
        
        if(wdPath.length() >= absSrc.length())
            if(wdPath.substring(0, absSrc.length()).equals(absSrc)) {
                /* directorul curent trebuie mutat */
                String rest = wdPath.substring(absSrc.length());
                String names[] = absSrc.split("/");
                String name = names[names.length - 1];
                if(!name.equals(""))
                    name = "/" + name;
                String newPath = absDst + name + rest;
                CommandsManager.setWdPath(newPath);
                CommandsManager.setWd((Folder)CommandsManager.getFile(newPath));
            }
        gfs.getParent().remove(gfs);
    }
    
}