package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEnoughArgsProvidedExceptionTest {
    private static NotEnoughArgsProvidedException exception;
    private static String message;

    @Test
    public void testMessage() {
        givenArgsCounts(10, 5);
        whenThrowing();
        thenMessageShouldBe("Expected 10 args, provided 5");
    }

    private void givenArgsCounts(int expected, int actual) {
        exception = new NotEnoughArgsProvidedException(expected, actual);
    }

    private void whenThrowing() {
        try {
            throw exception;
        } catch (Exception ex) {
            message = ex.getMessage();
        }
    }

    private void thenMessageShouldBe(String expected) {
        assertEquals(expected, message);
    }
}