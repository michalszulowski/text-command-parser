package command;

import command.factory.DictionaryCommandFactory;
import command.impl.ListArgsCommand;

public class Example {
    public static void main(String[] args) {
        DictionaryCommandFactory commandFactory = new DictionaryCommandFactory();
        commandFactory.addCommand("list-args", ListArgsCommand::new);
        String input = "list-args a b \"c d\"";
        Command command = commandFactory.getCommand(input);
        command.invoke();
    }
}
