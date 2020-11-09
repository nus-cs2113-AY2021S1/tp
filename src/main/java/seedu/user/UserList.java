package seedu.user;

import seedu.exception.WhereGotTimeException;

import java.util.ArrayList;

public class UserList {

    public static final int MAX_SIZE = 100;
    private final ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>(MAX_SIZE);
    }

    public UserList(ArrayList<User> users) {
        this.users = new ArrayList<>(users);
    }

    public void addUser(User user) {
        users.add(user);
    }
    
    public ArrayList<User> getUserList() {
        return users;
    }

    public User getUser(int index) throws WhereGotTimeException {
        try {
            return users.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WhereGotTimeException("Invalid user number!");
        }
    }

    public int getTotalUserCount() {
        return users.size();
    }

    public User getUserByName(String userName) {
        for (User user : this.users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    
    public void removeUser(String userName, String passWord)  throws WhereGotTimeException {
        boolean isMatch = false;
        
        if (users.isEmpty()) {
            throw new WhereGotTimeException("No users exist!");
        }
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(userName)) {
                if (users.get(i).getPassword().equals(passWord)) {
                    users.remove(i);
                    System.out.println(userName + " has been removed.");
                    isMatch = true;
                } else {
                    throw new WhereGotTimeException("Wrong Password");
                }
            }
        }

        if (isMatch == false) {
            throw new WhereGotTimeException(userName + " does not exist!");
        }
    }
}
