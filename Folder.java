
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Silviu
 */
public class Folder implements GenericFile {
    private String name;
    private Folder parent;
    private List<GenericFile> genericFiles;

    /**
     *
     * @param name - numele folderului
     * @param parent - folderul parinte
     */
    public Folder(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
        genericFiles = new ArrayList<>();
    }
    
    /** 
     * Parametrii metodelor si valorile returnate au aceeasi semnificatie
     * ca si cele descrise pentru interfata GenericFile
     */
    
    @Override
    public void add(GenericFile genericFile) {
        genericFiles.add(genericFile);
	Collections.sort(genericFiles);
    }

    @Override
    public GenericFile getChild(int i) {
        return genericFiles.get(i);
    }
    
    @Override
    public GenericFile getChild(String name) {
        for(GenericFile gf : genericFiles)
            if(gf.getName().equals(name))
                return gf;
        return null;
    }

    @Override
    public Folder getParent() {
        return parent;
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return genericFiles.size();
    }

    @Override
    public void print(Writer out, String path) {
        if(!path.equals("/"))
            path += "/";
        out.write(path + name);
    }

    @Override
    public void printAll(Writer out, String path) {
        out.writeLine(path + ":");
        for(int i = 0; i < genericFiles.size(); i++){
            genericFiles.get(i).print(out, path);
            if(i < genericFiles.size() - 1)
                out.write(" ");
        }
        out.writeNewLine();
        out.writeNewLine();
    }

    @Override
    public void printRec(Writer out, String path) {
        printAll(out, path);
        if(path.length() > 1)
            path += "/";
        for(int i = 0; i < genericFiles.size(); i++){
            GenericFile genericFile = genericFiles.get(i);
            genericFile.printRec(out, path + genericFile.getName());
        }
    }

    @Override
    public String printGrep(String path) {
        if(!path.equals("/"))
            path += "/";
        return path + name;
    }

    @Override
    public void printGrepAll(ArrayList<String> al, String path) {
        al.add(path + ":");
        String msg = "";
        for(GenericFile gf : genericFiles)
            msg += gf.printGrep(path) + " ";
        al.add(msg);
    }
            
    @Override
    public void printGrepRec(ArrayList<String> al, String path) {
        printGrepAll(al, path);
        if(path.length() > 1)
            path += "/";
        for(int i = 0; i < genericFiles.size(); i++){
            GenericFile genericFile = genericFiles.get(i);
            genericFile.printGrepRec(al, path + genericFile.getName());
        }
    }

    @Override
    public boolean remove(GenericFile gf) {
        return genericFiles.remove(gf);
    }
    
    @Override
    public GenericFile clone(Folder parent) {
        GenericFile gf = new Folder(name, parent);
        genericFiles.forEach((gf1) -> {
            gf.add(gf1.clone((Folder)gf));
        });
        return gf;
    }
    
    @Override
    public int compareTo(GenericFile gf) {
        return this.getName().compareTo(gf.getName());
    }
    
}
