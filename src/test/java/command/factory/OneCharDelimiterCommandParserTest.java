package command.factory;

import command.TextCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneCharDelimiterCommandParserTest {
    private static OneCharDelimiterCommandParser parser;
    private static TextCommand command;

    @Test
    public void testSimpleArgs() {
        givenParser();
        whenParsing("a b c");
        thenCommandShouldBe(createCommandOf("a", "b", "c"));
    }

    @Test
    public void testLackingEnclosure() {
        givenParser();
        whenParsing("a b \"c d\" e f \"g h");
        thenCommandShouldBe(createCommandOf("a", "b", "c d", "e", "f", "g h"));
    }

    @Test
    public void testOneCombinedArgs() {
        givenParser();
        whenParsing("a \"b c\"");
        thenCommandShouldBe(createCommandOf("a", "b c"));
    }

    @Test
    public void testOneCombinedArgAsFirst() {
        givenParser();
        whenParsing("a \"b c\" d");
        thenCommandShouldBe(createCommandOf("a", "b c", "d"));
    }

    @Test
    public void testOneCombinedArgInTheMiddle() {
        givenParser();
        whenParsing("a b \"c d\" e");
        thenCommandShouldBe(createCommandOf("a", "b", "c d", "e"));
    }

    @Test
    public void testMultipleCombinedArgs() {
        givenParser();
        whenParsing("a b \"c d\" e f \"g h i j\" k");
        thenCommandShouldBe(createCommandOf("a", "b", "c d", "e", "f", "g h i j", "k"));
    }

    private TextCommand createCommandOf(String name, String... args) {
        return new TextCommand(name, List.of(args));
    }

    private void givenParser() {
        parser = new OneCharDelimiterCommandParser(' ', '\"');
    }

    private void whenParsing(String input) {
        command = parser.parse(input);
    }

    private void thenCommandShouldBe(TextCommand expectedCommand) {
        assertEquals(expectedCommand, command);
    }
}