package userInterfaces.menuFactories;

import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menus.playlistsMenu.PlaylistManageMenu;
import userInterfaces.menus.playlistsMenu.PlaylistMenu;
import userInterfaces.menus.playlistsMenu.PlaylistOrderMenu;
import userInterfaces.menus.playlistsMenu.PlaylistViewMenu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class PlaylistsMenuFactory implements MenuFactory{
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final List<Playlist> playlists;


    public PlaylistsMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.playlists = playlists;
    }

    public Menu getMenu(MenuEnums type) {

        switch (type) {
            case PLAYLIST:
                return new PlaylistMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTMANAGE:
                return new PlaylistManageMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTVIEW:
                return new PlaylistViewMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTORDER:
                return new PlaylistOrderMenu(userPrompt, user, lp, mp, playlists);
            default:
                return null;
        }
    }
}
