
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Silviu
 */
public class RmCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        String path = CommandsManager.modify(args[1].replace("$", ""));
        GenericFile gf = CommandsManager.getFile(path);
        
        if(gf == null) {
            if(!args[1].contains("$"))
                err.writeLine("rm: cannot remove " + args[1] + 
                        ": No such file or directory");
            return;
        }
        if(gf.equals(CommandsManager.getAbs())) {
            /* Calea contine "*" */
            int position = CommandsManager.getPosition(gf, path, 
                    args[1].replace("$", ""));
            gf = gf.getParent();
            if(path.equals("/*"))
                gf = CommandsManager.getFs();
            String[] names = args[1].replace("$", "").split("/");
            String star = "^" + names[position]; /* numele ce contine "*" */
            if(star.equals("^"))
                star += "*";
            String left = "", right = "";
            for(int i = 0; i < position; i++)
                left += names[i] + "/";
            for(int i = position + 1; i < names.length; i++)
                right += "/" + names[i];
            boolean matched = false; int count = 0;
            int j = star.indexOf("*");
            if(j > 1)
                star = star.substring(0, j) + "." + 
                        star.substring(j, star.length());
            Pattern pattern = Pattern.compile(star);
            for(int i = gf.getSize() - 1; i >= 0; i--) {
                GenericFile child = gf.getChild(i);
                Matcher matcher = pattern.matcher(child.getName());
                if (!matcher.find()) {
                    continue;}
                matched = true; count++;
                path = left + child.getName() + right; /* noua cale */
                Command rm = new RmCommand();
                args[1] = "$" + path;
                rm.execute(args, out, err);
            }
            if(matched)
            if(matched == false)
                if(!args[1].contains("$"))
                    err.writeLine("rm: cannot remove " + args[1].replace("$", "")
                            + ": No such file or directory");
            return;
        }
        String finalPath = CommandsManager.getPath(path);
        String wdPath = CommandsManager.getWdPath();
        
        if(wdPath.length() >= finalPath.length()) {
            if(!wdPath.substring(0, finalPath.length()).equals(finalPath))
                gf.getParent().remove(gf);}
        else
            gf.getParent().remove(gf);
    }
    
}