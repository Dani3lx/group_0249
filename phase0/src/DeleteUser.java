import java.util.List;
import java.util.Scanner;

public class DeleteUser {

    /**
     * Delete a specific user.
     * Returns true if self is deleted.
     */
    private final Scanner sc = new Scanner(System.in);
    private final UserManager um = new UserManager();

    public boolean deleteUser(User currentUser) {
        System.out.println("Here are all the users: \n");
        UserData.displayAllUsers(UserData.getAllUsers());
        System.out.println("Please enter the username of the user you wish to delete");
        String name = sc.nextLine();
        List<User> all_users = UserData.getAllUsers();

        for (User user : all_users) {
            if (um.validateUserName(user, name)) {
                if (um.validateUserName(currentUser, name)) {
                    System.out.println("Are you sure you want to delete this account");
                    System.out.println("Type t to continue");
                    String result = sc.nextLine();
                    if (result.equals("t")) {
                        um.deleteUser(user);
                        System.out.println("You have successfully deleted your own account");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    um.deleteUser(user);
                    System.out.println("The user " + name + " has been successfully deleted");
                    return false;
                }
            }
        }
        System.out.println("The user " + name + " does not exist");
        return false;
    }
}
