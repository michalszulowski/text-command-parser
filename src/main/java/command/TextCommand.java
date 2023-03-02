package command;

import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode
public class TextCommand {
    private final String name;
    private final List<String> arguments;

    public TextCommand(String name, List<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
