
/**
 *
 * @author Silviu
 */
public class CommandsFactory {
    private static final CommandsFactory INSTANCE = new CommandsFactory();
    
    private CommandsFactory() {}
    
    /**
     *
     * @return = singura Instanta a acestei clase
     */
    public static CommandsFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     *
     * @param type - tipul comenzii
     * @return = Comanda corespunzatoare tipului nevoit
     */
    public Command createCommand(String type) {
        switch (type) {
            case "ls": return new LsCommand();
            case "pwd": return new PwdCommand();
            case "cd": return new CdCommand();
            case "cp": return new CpCommand();
            case "mv": return new MvCommand();
            case "rm": return new RmCommand();
            case "mkdir": return new MkdirCommand();
            case "touch": return new TouchCommand();
            }
            throw new IllegalArgumentException("Command type not found");
    }
}
