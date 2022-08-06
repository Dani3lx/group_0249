package userInterfaces.menus;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.PlaylistActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoStudioMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{""});

    public VideoStudioMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new PlaylistActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.VIDEOSTUDIO, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}