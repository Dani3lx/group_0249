package controllers.action.actions.playlistActions;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class ReturnPL extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    public ReturnPL(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, null);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
    }
}
