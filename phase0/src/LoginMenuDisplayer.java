import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

public class LoginMenuDisplayer {
    UserManager um = new UserManager();
    CreateUser cu = new CreateUser();
    DeleteUser du = new DeleteUser();

    /**
     * Display the start menu of the login system.
     *
     * @throws IOException if error reading from file
     */
    public void startMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 1 to login, type 2 to create a new user account");

        if (sc.hasNextInt()) {
            int input = (sc.nextInt());
            if (input == 1) {
                User currentUser = UserLogin.loginUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to Login");
                    this.startMenu();
                } else {
                    System.out.println("you are now logged in");
                    currentUser.getLoginHistory().add(LocalDateTime.now());
                    if (currentUser instanceof AdminUser) {
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }

                }
            } else if (input == 2) {
                User currentUser = cu.createUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to create a new account");
                    this.startMenu();
                } else {
                    System.out.println("New account has been created");
                    currentUser.getLoginHistory().add(LocalDateTime.now());
                    //UserData.writeData();
                    List<User> ab = UserData.getAllUsers();
                    DataManager.writeCSV("Data.csv");

                    if (currentUser instanceof AdminUser) {
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }
                }

            } else {
                System.out.println("Please enter a valid response");
                startMenu();
            }
        } else {
            System.out.println("Please enter a valid response");
            startMenu();
        }
    }

    /**
     * Display the menu after NonAdminUser logs in.
     */
    private void AfterLoginMenu(NonAdminUser user) throws IOException {
        System.out.println("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out");
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            switch (result) {
                case 1:
                    System.out.println("Please enter a new password");
                    String newPassword = sc.nextLine();
                    um.changePassword(user, newPassword);
                    break;
                case 2:
                    um.checkHistory(user);
                    break;
                case 3:
                    startMenu();
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
        AfterLoginMenu(user);

    }

    /**
     * Display the menu after AdminUser logs in.
     */
    private void AfterLoginMenu(AdminUser user) throws IOException {
        System.out.println("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            switch (result) {
                case 1:
                    System.out.println("Please enter a new password");
                    String newPassword = sc.nextLine();
                    um.changePassword(user, newPassword);
                    break;
                case 2:
                    um.checkHistory(user);
                    break;
                case 3:
                    startMenu();
                    break;
                case 4:
                    cu.creatAdminUser();
                    break;
                case 5:
                    if (du.deleteUser(user)) {
                        startMenu();
                    } else {
                        AfterLoginMenu(user);
                    }
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
        AfterLoginMenu(user);
    }

}
