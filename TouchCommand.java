
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Silviu
 */
public class TouchCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        String path = CommandsManager.modify(args[1]);
        String names[] = path.split("/");
        String name = names[names.length - 1];
        String parentPath = path.substring(0, path.length() - name.length() - 1);
        GenericFile gf = CommandsManager.getFile(parentPath);
        
        if(gf == null || !(gf instanceof Folder)) {
            if(!args[1].equals(path))
                parentPath = parentPath.substring(2);
            err.writeLine("touch: " + parentPath + ": No such directory");
            return;
        }
        
        if(gf.equals(CommandsManager.getAbs())) {
            /* Calea contine "*" */
            int position = CommandsManager.getPosition(gf, path, args[1]);
            gf = gf.getParent();
            names = args[1].split("/");
            String star = "^" + names[position]; /* numele ce contine "*" */
            String left = "", right = "";
            for(int i = 0; i < position; i++)
                left += names[i] + "/";
            for(int i = position + 1; i < names.length; i++)
                right += "/" + names[i];
            boolean matched = false;
            int j = star.indexOf("*");
            if(j > 1)
                star = star.substring(0, j) + "." + star.substring(j, 
                        star.length());
            Pattern pattern = Pattern.compile(star);
            for(int i = 0; i < gf.getSize(); i++) {
                GenericFile child = gf.getChild(i);
                Matcher matcher = pattern.matcher(child.getName());
                if (!matcher.find() || child instanceof Fisier)
                    continue;
                matched = true;
                path = left + child.getName() + right; /* noua cale */
                Command touch = new TouchCommand();
                args[1] = path;
                touch.execute(args, out, err);
            }
            if(matched == false)
                err.writeLine("touch: " + parentPath + ": No such directory");
            return;
        }
        
        if(((Folder)gf).getChild(name) != null) {
            path = CommandsManager.getPath(path);
            err.writeLine("touch: cannot create file " + path + ": Node exists");
            return;
        }

        GenericFile newGF = new Fisier(name, ((Folder)gf));
        ((Folder)gf).add(newGF);
    }
    
}