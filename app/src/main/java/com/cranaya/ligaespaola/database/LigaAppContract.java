package com.cranaya.ligaespaola.database;

public class LigaAppContract {

    public interface BaseColumn {
        String ID = "_id";
        String FECHA_SISTEMA = "fechaSistema";
    }

    public interface TeamColumn extends BaseColumn {
        String ID_TEAM = "teamId";
        String NAME_TEAM = "nameTeam";
        String NAME_STADIUM_TEAM = "stadiumTeam";
        String BADGE_TEAM = "badgeTeam";
        String DESCRIPTION_TEAM = "descriptionTeam";
        String FOUNDATION_YEAR_TEAM = "foundationYear";
        String JERSEY_TEAM = "yerseyTeam";
        String WEBSITE_TEAM = "webSiteTeam";
        String FACEBOOK_TEAM = "facebookTeam";
        String TWITTER_TEAM = "twitterTeam";
        String INSTAGRAM_TEAM = "instagramTeam";
    }
}
