package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogInCommandTest {
    Ui ui = new Ui();
    
    
    
    @Test
    void execute_executesProperly() throws WhereGotTimeException {
        Ui ui = new Ui();
        UserList users = new UserList();
        User nowUser = null;
        
        LogInCommand login = new LogInCommand("/man /123123");
        login.execute(users,ui,nowUser);

        nowUser = login.getCurrentUser();
        
        assertEquals("man", nowUser.getName());
    }

    @Test
    void execute_executesProperlyReturningUser() throws WhereGotTimeException {
        UserList users = new UserList();
        
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        Ui ui = new Ui();
        User nowUser = null;
        
        LogInCommand login = new LogInCommand("/Carl /456456");
        login.execute(users,ui,nowUser);

        nowUser = login.getCurrentUser();

        assertEquals("Carl", nowUser.getName());
    }
}