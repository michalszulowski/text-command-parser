package command.impl;

import command.ArgsCommand;

import java.util.List;

public class ListArgsCommand extends ArgsCommand {

    public ListArgsCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    public void invoke() {
        int counter = 1;
        for (String argument : arguments) {
            System.out.println(counter + ". " + argument);
            counter++;
        }
    }
}
