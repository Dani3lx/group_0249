package controllers.action.actions.videoStudioMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;

public class ViewVideoUploaded extends MenuAction implements Action {

    public ViewVideoUploaded(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    public void run(){
        NonAdminManager nam = new NonAdminManager(vm);
        mp.displayAlert(LanguagePresenter.AlertTextType.VIDEOSUPLOADED);
        mp.displayList(nam.ReturnUserVideos(currentUser, vm.getVids()));
        next();
    }

    public void next(){
        MenuBuilder menuBuilder = new MenuBuilder(userPrompt, currentUser, lp, mp);
        menuBuilder.getMenu(Menus.NONADMIN).run();
    }
}
