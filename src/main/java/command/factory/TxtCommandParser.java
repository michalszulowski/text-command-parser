package command.factory;

import command.TextCommand;

public interface TxtCommandParser {
    TextCommand parse(String input);
}
