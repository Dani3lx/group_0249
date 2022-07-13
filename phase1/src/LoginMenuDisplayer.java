//import java.io.IOException;
//import java.util.Objects;
//
//// THIS FILE WILL BE DELETED
//public class LoginMenuDisplayer {
//    private VideoBrowser vm;
//    private VideoPresenter vp = new VideoPresenter();
//    UserInterfaceHandler UIhandler;
//
//    DataManager sm;
//
//    Presenter p = new Presenter();
//
//    public LoginMenuDisplayer(UserManager um, VideoManager vm) {
//        UIhandler = new UserInterfaceHandler(um);
//        sm = new DataManager(um, vm);
//    }
//
//    /**
//     * Display the start menu of the login system.
//     *
//     * @throws IOException if error reading from file
//     */
//    public void startMenu() throws IOException {
//        // test
//        int userChoice = p.startMenuOptions();
//
//        User currentUser;
//
//        switch (userChoice) {
//            case 1:
//                currentUser = UIhandler.loginUser();
//                if (Objects.isNull(currentUser)) {
//                    p.displayErrorMessage("login");
//                    this.startMenu();
//                } else {
//                    UIhandler.updateUserHistory(currentUser);
//                    if (currentUser.isAdminInd()) {  // add a method to UIhandler that does this check instead
//                        p.displayAlertMessage("adminLogin");
//                        AfterLoginMenu(currentUser);  // Added a menu for non admin and admin. Make a basic menu for all users so that theres no repetiting code
//                    } else {
//                        p.displayAlertMessage("nonAdminLogin");
//                        AfterLoginMenu(currentUser);
//                    }
//                }
//            case 2:
//                currentUser = UIhandler.createUser();
//                if (Objects.isNull(currentUser)) {
//                    p.displayErrorMessage("accountCreation");
//                    this.startMenu();
//                } else {
//                    UIhandler.updateUserHistory(currentUser);
//                    p.displayAlertMessage("accountCreation");
//                    AfterLoginMenu(currentUser);
//                }
//            case 3:
//                sm.saveData("phase1/Data.csv");
//                sm.saveVideoData("phase1/VideoData.csv");
//                System.exit(0);
//            default:
//                p.displayErrorMessage("userInput");
//                startMenu();
//        }
//
//    }
//
//    /**
//     * Display the menu after User logs in.
//     */
//
//    private void AfterLoginMenu(User user) throws IOException {
//
//        int result = p.afterLoginOptions(user.isAdminInd());
//            switch (result) {
//                case 1:
//                    UIhandler.changePassword(user);
//                    p.displayAlertMessage("passwordChange");
//                    break;
//                case 2:
//                    p.displayOptionMessages("checkHistory");
//                    UIhandler.displayHistory(user);
//                    p.displayOptionMessages("lineBreak");
//                    break;
//                case 3:
//                    startMenu();
//                    break;
//                case 4:
//                    if (user.isAdminInd()) {
//                        UIhandler.creatAdminUser();
//                    }
//                    break;
//                case 5:
//                    if (user.isAdminInd()) {
//                        if (UIhandler.deleteUser(user)) {
//                            startMenu();
//                        } else {
//                            AfterLoginMenu(user);
//                        }
//                    }
//                    break;
////                case 6:
////                    if (user.isAdminInd()) {
////                        UIhandler.banUser(user);
////                    }
////                    break;
////                case 7:
////                    if (user.isAdminInd()) {
////                        UIhandler.unBanUser();
////                    }
////                    break;
//            }
//
//        AfterLoginMenu(user);
//    }
//
//    public void browsingMenu(User user) throws IOException {
//        vm = new VideoBrowser(user);
//        int result = p.basicMenuOptions("Type 1 to browse by name, type 2 to browse by categories, " +
//                "type 3 to browse by uploader, type 4 to return");
//        switch (result) {
//            case 1:
//                vp.listVideos(vm.browseByName());
//                break;
//            case 2:
//                vp.listVideos(vm.browseByCategory());
//                break;
//            case 3:
//                vp.listVideos(vm.browseByUploader());
//                break;
//            case 4:
//                AfterLoginMenu(user);
//        }
//    }
//}
//
