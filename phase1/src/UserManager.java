import java.util.*;

public class UserManager {
    private final List<User> users;

    public UserManager() {
        users = UserData.getAllUsers();
    }

    public User validateUser(String username, String password) {
        if (Objects.isNull(users)) {
            return null;
        } else {
            for (User user : users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    if (!user.getBanStatus()) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public void changePassword(User user, String password) {
        user.setPassword(password);
    }

    public void checkHistory(User user) {
        List<String> history = new ArrayList<>(user.getLoginHistory());
        Collections.sort(history);
        System.out.println(history);
    }

    public void updateHistory(User user, String date) {
        user.getLoginHistory().add(date);
    }

    public User instantiateUser(String userName, String password, boolean banStatus, HashSet<String> history,
                                boolean adminStatus){
        if (adminStatus) {
            return new AdminUser(userName, password, banStatus, history);
        } else {
            return new NonAdminUser(userName, password, banStatus, history);
        }
    }

    public User instantiateUser(String userName, String password, boolean adminStatus){
        if (adminStatus) {
            return new AdminUser(userName, password);
        } else {
            return new NonAdminUser(userName, password);
        }
    }

    public boolean validateUserName(User user, String name) {
        return user.getUserName().equals(name);
    }

    public boolean validateBanStatus(User user) {
        return user.getBanStatus();
    }
}