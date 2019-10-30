
import java.util.ArrayList;

/**
 *
 * @author Silviu
 */
public interface GenericFile extends Comparable<GenericFile> {
    
    /**
     *
     * @param genericFile - fisierul de adaugat
     */
    public void add(GenericFile genericFile);

    /**
     *
     * @param i - indexul elementului din vector
     * @return = elementul din vector
     */
    public GenericFile getChild(int i);

    /**
     *
     * @param name - numele elementului din vector
     * @return = elementul din vector
     */
    public GenericFile getChild(String name);

    /**
     *
     * @return = folderul ce il contine pe "this"
     */
    public Folder getParent();

    /**
     *
     * @return = numele lui "this"
     */
    public String getName();

    /**
     *
     * @return = dimensiunea vectorului
     */
    public int getSize();

    /**
     *
     * @param parent = noul folder ce il contine pe "this"
     */
    public void setParent(Folder parent);

    /**
     *
     * @param name = noul nume al lui "this"
     */
    public void setName(String name);

    /**
     *
     * @param out - fisierul cu rezultate
     * @param path - calea catre fisier
     */
    public void print(Writer out, String path);

    /**
     *
     * @param out - fisierul cu rezultate
     * @param path - calea catre fisier
     */
    public void printAll(Writer out, String path);

    /**
     *
     * @param out - fisierul cu rezultate
     * @param path - calea catre fisier
     */
    public void printRec(Writer out, String path);

    /**
     *
     * @param path - calea catre fisier
     * @return
     */
    public String printGrep(String path);

    /**
     *
     * @param al - vectorul cu rezultate
     * @param path - calea catre fisier
     */
    public void printGrepAll(ArrayList<String> al, String path);

    /**
     *
     * @param al - vectorul cu rezultate
     * @param path - calea catre fisier
     */
    public void printGrepRec(ArrayList<String> al, String path);

    /**
     *
     * @param gf - genericFileul de scos
     * @return
     */
    public boolean remove(GenericFile gf);

    /**
     *
     * @param gf - folderul de scos
     * @return
     */
    public GenericFile clone(Folder gf);
}
