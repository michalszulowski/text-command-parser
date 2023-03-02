package command.factory;

import command.Command;
import command.TextCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DictionaryCommandFactory implements CommandFactory {
    private Map<String, Function<List<String>, Command>> supportedCommands;
    private TxtCommandParser commandParser;

    //TODO add parametrized delimiters
    public DictionaryCommandFactory() {
        supportedCommands = new HashMap<>();
        commandParser = new OneCharDelimiterCommandParser(' ', '\"');
    }

    @Override
    public Command getCommand(String ofInput) {
        if (ofInput.length() == 0) {
            throw new IllegalArgumentException("Empty text cannot be a command");
        }
        TextCommand textCommand = commandParser.parse(ofInput);
        return getCommandOf(textCommand);
    }

    public void addCommand(String name, Function<List<String>, Command> commandCreateFunction) {
        supportedCommands.put(name, commandCreateFunction);
    }

    private Command getCommandOf(TextCommand command) {
        if (!supportedCommands.containsKey(command.getName())) {
            throw new IllegalArgumentException("No command with name: " + command.getName());
        }
        return supportedCommands.get(command.getName()).apply(command.getArguments());
    }
}