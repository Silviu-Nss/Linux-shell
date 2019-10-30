
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Silviu
 */
public class GrepCommand implements Command {

    /**
     *
     * @param args - argumentele comenzii
     * @param out - fisierul cu rezultate
     * @param err - fisierul cu erori
     */
    @Override
    public void execute(String[] args, Writer out, Writer err) {
        String regex = args[0]; /* expresia regulata a comenzii */
        regex = regex.substring(1, regex.length() - 1);
        if(!regex.subSequence(0, 1).equals("^"))
            regex = "^" + regex;
        if(!regex.subSequence(0, 1).equals("$"))
            regex += "$";
        
        String path = args[1];
        boolean recursive = false;
        if(args[2].equals("1"))
            recursive = true;
        
        GenericFile gf = CommandsManager.getFile(path);
        
        ArrayList<String> paths = new ArrayList<>();
        if(recursive)
            gf.printGrepRec(paths, path);
        else
            gf.printGrepAll(paths, path);
        
        Pattern pattern = Pattern.compile(regex);        
        for(int i = 0; i < paths.size(); i++) {
            path = paths.get(i);
            if(i % 2 == 0) { /* Scriem numele folderului */
                out.writeLine(path);
                continue;
            }
            /* Scriem continutul folderului */
            String[] names = path.split(" ");
            String msg = "";
            for (String name : names) {
                String[] listOfName = name.split("/");
                String last = listOfName[listOfName.length - 1];
                Matcher matcher = pattern.matcher(last);
                if (matcher.find()) {
                    msg += " " + name;
                }
            }
            if(msg.length() > 0)
                msg = msg.substring(1);
            out.writeLine(msg);
            out.writeNewLine();
        }
    }
    
}
