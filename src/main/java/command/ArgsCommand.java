package command;

import java.util.List;

public abstract class ArgsCommand implements Command {
    protected List<String> arguments;

    public ArgsCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    protected void expectArgsCount(int count) {
        if (arguments.size() < count) {
            throw new NotEnoughArgsProvidedException(count, arguments.size());
        }
    }
}
