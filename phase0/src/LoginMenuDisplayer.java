import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoginMenuDisplayer {
    UserManager um = new UserManager();
    CreateUser cu = new CreateUser();
    DeleteUser du = new DeleteUser();
    Presenter p = new Presenter();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    BanUser bu = new BanUser();

    /**
     * Display the start menu of the login system.
     *
     * @throws IOException if error reading from file
     */
    public void startMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(p.startMenuOption("Type 1 to login, type 2 to create a new user account, type 3" +
                " to exit program"));
        if (sc.hasNextInt()) {
            int input = (sc.nextInt());
            if (input == 1) {
                User currentUser = UserLogin.loginUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to Login");
                    this.startMenu();
                } else {

                    currentUser.getLoginHistory().add(LocalDateTime.now().format(formatter));
                    if (currentUser instanceof AdminUser) {
                        System.out.println(p.alertText("you are now logged in to an admin account"));
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        System.out.println(p.alertText("you are now logged in to a non-admin account"));
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }

                }
            } else if (input == 2) {
                User currentUser = cu.createUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to create a new account");
                    this.startMenu();
                } else {
                    currentUser.getLoginHistory().add(LocalDateTime.now().format(formatter));
                    System.out.println("New account has been created");


                    if (currentUser instanceof AdminUser) {
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }
                }

            } else if (input == 3){
                DataManager sm = new DataManager();
                sm.setUsers((ArrayList<User>) UserData.getAllUsers()); //todo try to make a solution without casting, preferably choosing either datamanager or Userdata to hold onto the instanced data
                sm.saveData("Data.csv");
                System.exit(0);
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

        System.out.println("""
                Please input one of the following number to proceed\s
                 1 - Change Password\s
                 2 - Check login history\s
                 3 - Log out\s


                """);

        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            switch (result) {
                case 1 -> {
                    System.out.println("Please enter a new password");
                    String newPassword = sc.nextLine();
                    um.changePassword(user, newPassword);
                    System.out.println("Password change was successful\n");
                }
                case 2 -> {
                    System.out.println("Checking history:");
                    um.checkHistory(user);
                    System.out.println("\n");
                }
                case 3 -> startMenu();
                default -> System.out.println("Please enter a valid input");
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
        System.out.println("""
                Please input one of the following number to proceed\s
                 1 - Change Password\s
                 2 - Check login history\s
                 3 - Log out\s
                 4 - Create AdminUser\s
                 5 - Delete User\s
                 6 - Ban User\s
                 7 - UnBan User\s
                """);
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            switch (result) {
                case 1:
                    System.out.println("Please enter a new password");
                    String newPassword = sc.nextLine();
                    um.changePassword(user, newPassword);
                    System.out.println("Password change was successful\n");
                    break;
                case 2:
                    System.out.println("Checking history:");
                    um.checkHistory(user);
                    System.out.println("\n");
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
                case 6:
                    bu.banUser(user);
                    break;
                case 7:
                    bu.unBanUser();
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
