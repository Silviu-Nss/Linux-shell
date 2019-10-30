
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Silviu
 */
public class LsCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        if(args.length == 1) {  /* Scriem ontinutul folderului curent */
            CommandsManager.getWd().printAll(out, CommandsManager.getWdPath());
            return;
        }
        String path = args[1];
        boolean recursive = false;
        if(path.equals("-R")) {
            recursive = true;
            if(args.length == 2){ // Scriem recursiv ontinutul folderului curent
                CommandsManager.getWd().printRec(out, CommandsManager.getWdPath());
                return;
            }
            path = args[2];
        }
        else if(args.length > 2 && args[2].equals("-R"))
            recursive = true;
        String newPath = CommandsManager.modify(path);
        GenericFile gf = CommandsManager.getFile(newPath);
        if(gf == null || !(gf instanceof Folder)) {
            err.writeLine("ls: " + path + ": No such directory");
            return;
        }
        if(gf.equals(CommandsManager.getAbs())) {
            /* Calea contine "*" */
            gf = gf.getParent();
            String[] names = newPath.split("/");
            String last = "^" + names[names.length - 1];// ultimul nume din cale
            int dist = newPath.length() - last.length() + 1;
            String parentPath = newPath.substring(0, dist);// Folderul parintele
            parentPath = CommandsManager.getPath(parentPath); // Calea absoluta
            if(!parentPath.equals("/"))
                parentPath += "/";
            boolean matched = false;
            Pattern pattern = Pattern.compile(last);
            for(int i = 0; i < gf.getSize(); i++) {
                GenericFile child = gf.getChild(i);
                Matcher matcher = pattern.matcher(child.getName());
                if (!matcher.find() || child instanceof Fisier)
                    continue;
                matched = true;
                child.printAll(out, parentPath + child.getName());
            }
            if(matched == false)
                err.writeLine("ls: " + path + ": No such directory");
            return;
        }
        String finalPath = CommandsManager.getPath(newPath);
        if (args.length < 4){
            if(recursive)
                gf.printRec(out, finalPath);
            else
                gf.printAll(out, finalPath);
            return;
        }
        /* Facem grep la output */
        String[] grepPath = {args[args.length - 1], finalPath, "0"};
        if(recursive)
            grepPath[2] = "1";
        Command grep = new GrepCommand();
        grep.execute(grepPath, out, err);
    }
}