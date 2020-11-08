package fitr.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagesTest {
    @Test
    public void messagesTest_correctResult() {
        assertEquals("Bye. Hope to see you again soon!", Messages.MESSAGE_BYE);
        assertEquals("What can I do for you?", Messages.MESSAGE_SUGGEST_QUESTION);
    }
}
