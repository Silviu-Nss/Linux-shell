
import java.util.ArrayList;

/**
 *
 * @author Silviu
 */
public class CommandsManager {
    private final ArrayList<String[]> commands;
    private final Writer out, err;
    private static Folder fs, wd, abs;
    private static String wdPath;

    /**
     *
     * @param commands - comenzile de rulat
     * @param out - numele fisierului cu rezultate
     * @param err - numele fisierului cu erori
     */
    public CommandsManager(ArrayList<String[]> commands, String out, String err)
    {
        this.commands = commands;
        this.out = new Writer(out);
        this.err = new Writer(err);
        CommandsManager.fs = new Folder(null, null);
        CommandsManager.wd = fs;
        CommandsManager.abs = new Folder(null, null);
        wdPath = "/";
    }
  
    /**
     * ruleaza comenzile date ca input
     */
    public void run() {
        CommandsFactory cf = CommandsFactory.getInstance();
        for (int i = 0; i < commands.size(); i++) {
            String count = Integer.toString(i + 1);
            out.writeLine(count);
            err.writeLine(count);
            Command cmd = cf.createCommand(commands.get(i)[0]);
            cmd.execute(commands.get(i), out, err);
        }
        out.close();
        err.close();
    }

    /**
     *
     * @return = sistemul de fisiere
     */
    public static Folder getFs() {
        return fs;
    }

    /**
     *
     * @return = directorul curent
     */
    public static Folder getWd() {
        return wd;
    }

    /**
     *
     * @param wd - noul director curent
     */
    public static void setWd(Folder wd) {
        CommandsManager.wd = wd;
    }
    
    /**
     *
     * @return = folderul abstract
     */
    public static Folder getAbs() {
        return abs;
    }
    
    /**
     *
     * @return = calea catre directorul curent
     */
    public static String getWdPath() {
        return wdPath;
    }

    /**
     *
     * @param wdPath - noua cale catre directorul curent
     */
    public static void setWdPath(String wdPath) {
        CommandsManager.wdPath = wdPath;
    }
    
    /**
     *
     * @param path - calea primita ca input
     * @return = fisierul/folderul corespunzator caii date
     */
    public static GenericFile getFile(String path) {
        if(path.equals("/") || path.equals(""))
            return fs;
        if(String.valueOf(path.charAt(0)).equals("/"))
            path = path.substring(1);
        else
            if(!wdPath.equals("/"))
                path = wdPath.substring(1) + "/" + path;
        path = path.replace("$", "");
        GenericFile target = fs;
        int i = -1;
        while(target != null && path != null && !path.equals("")) {
            String name = path.split("/")[0]; /* primul genericFile din cale */
            path = path.substring(name.length());
            i++;
            if(path.length() > 0)
                path = path.substring(1);
            switch (name) {
                case "..":
                    target = target.getParent(); // mergem la directorul anterior
                    break;
                case ".":
                    break; /* nu facem nimic daca gasim "." */
                default:
                    if(name.contains("*")) {
                        if(target instanceof Fisier)
                            return null;
                        abs.setName(i + "");
                        abs.setParent((Folder)target);
                        return abs;
                    }
                    target = target.getChild(name); // noul genericFile din cale
                    break;
            }
        }
        return target;
    }
    
    /**
     *
     * @param path - calea primita ca input
     * @return = calea absoluta corespunzatoare caii date
     */
    public static String getPath(String path) {
        if(path.equals("/") || path.equals(""))
            return "/";
        if(String.valueOf(path.charAt(0)).equals("/"))
            path = path.substring(1);
        else
            if(!wdPath.equals("/"))
                path = wdPath.substring(1) + "/" + path;
        path = path.replace("$", "");
        String finalPath = "";
        GenericFile target = fs;
        while(target != null && path != null && !path.equals("")) {
            String name = path.split("/")[0];
            path = path.substring(name.length());
            if(path.length() > 0 && String.valueOf(path.charAt(0)).equals("/"))
                path = path.substring(1);
            switch (name) {
                case "..":   /* Stergem ultimul nume de genericFile din cale */ 
                    target = target.getParent();
                    String names[] = finalPath.split("/");
                    String last = names[names.length - 1];
                    finalPath = finalPath.substring(0, finalPath.length() -
                            last.length() - 1);
                    break;
                case ".":
                    break; /* Trecem mai departe */
                default:
                    target = target.getChild(name);
                    finalPath += "/" + name; /* concatenam noul nume la cale */
                    break;
            }
        }
        if(finalPath.equals(""))
            return "/";
        return finalPath;
    }
    
    /**
     *
     * @param path - calea data ca input
     * @return = noua cale, posibil modificata
     */
    public static String modify(String path) {
        String first = String.valueOf(path.charAt(0));
        if(!first.equals(".") && !first.equals("..") && !first.equals("/"))
            path = "./" + path;
        return path;
    }
    
    /**
     *
     * @param gf - folderul ce contine ca nume pozitia din cale
     * @param path - calea posibil modificata input
     * @param arg - calea initiala
     * @return = indicele de pozitie din cale
     */
    public static int getPosition(GenericFile gf, String path, String arg) {
        int position = Integer.valueOf(gf.getName());
        if(path.substring(0, 1).equals(".") && !wdPath.equals("/"))
            position -= wdPath.substring(1).split("/").length;
        if(!path.equals(arg))
            position--;
        if(arg.substring(0, 1).equals("/"))
            position++;
        return position;
    }
}
