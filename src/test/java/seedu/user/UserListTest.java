package seedu.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.exception.WhereGotTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserListTest {
    
    @Test
    void addUser_addsProperly() {
        UserList users = new UserList();
        User user = new User("man","123123");
        
        users.addUser(user);
        
        assertEquals(1, users.getTotalUserCount());
    }

    @Test
    void getUser_getsProperly() throws WhereGotTimeException {
        UserList users = new UserList();
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        assertEquals("Alex", users.getUser(1).getName());
    }

    @Test
    void getUser_testExpectedException_invalidIndex() {
        UserList users = new UserList();
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            users.getUser(4);;
        });
    }

    @Test
    void getUserByName_getsProperly() {
        UserList users = new UserList();
        User user = new User("man","123123");

        users.addUser(user);
        
        assertEquals("123123", (users.getUserByName("man").password));
    }

    @Test
    void getUserByName_noMatch_returnsNull() {
        UserList users = new UserList();
        User user = new User("man","123123");

        users.addUser(user);

        assertNull((users.getUserByName("girl")));
    }
    
    @Test
    void removeUser_removesCorrectly() throws WhereGotTimeException {
        UserList users = new UserList();
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        users.removeUser("Carl", "456456");
        
        assertEquals(2, users.getTotalUserCount());
    }

    @Test
    void removeUser_testExpectedException_noMatchUserName() {
        UserList users = new UserList();
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);
        
        
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            users.removeUser("Eric", "456456");;
        });
    }

    @Test
    void removeUser_testExpectedException_wrongPassWord() {
        UserList users = new UserList();
        User user1 = new User("Alex","123123");
        User user2 = new User("Ben","321321");
        User user3 = new User("Carl","456456");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            users.removeUser("Carl", "987987");;
        });
    }

    @Test
    void removeUser_testExpectedException_emptyUserList() {
        UserList users = new UserList();

        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            users.removeUser("Carl", "987987");;
        });
    }
}