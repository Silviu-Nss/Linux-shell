
import java.util.ArrayList;

/**
 *
 * @author Silviu
 */
public class Fisier implements GenericFile {
    private String name;
    private Folder parent;

    /**
     *
     * @param name - numele folderului
     * @param parent - folderul parinte
     */
    public Fisier(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    /** 
     * Parametrii metodelor si valorile returnate au aceeasi semnificatie
     * ca si cele descrise pentru interfata GenericFile
     */
    
    @Override
    public void add(GenericFile genericFile) {}

    @Override
    public GenericFile getChild(int i) {
        return null;
    }

    @Override
    public GenericFile getChild(String name) {
        return null;
    }

    @Override
    public Folder getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public boolean remove(GenericFile gf) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void print(Writer out, String path) {
        path = path.replace(" ", "");
        if(!path.equals("/"))
            path += "/";
        out.write(path + name);
    }
    
    @Override
    public void printAll(Writer out, String path) {
   }

    @Override
    public void printRec(Writer out, String path) {
    }

    @Override
    public String printGrep(String path) {
        if(!path.equals("/"))
            path += "/";
        return path + name;
    }

    @Override
    public void printGrepAll(ArrayList<String> al, String path) {
    }
            
    @Override
    public void printGrepRec(ArrayList<String> al, String path) {
    }

    @Override
    public GenericFile clone(Folder parent) {
        return new Fisier(name, parent);
    }
    
    @Override
    public int compareTo(GenericFile gf) {
        return this.getName().compareTo(gf.getName());
    }
    
}
