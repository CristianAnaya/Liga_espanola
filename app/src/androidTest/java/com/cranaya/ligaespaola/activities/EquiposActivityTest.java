package com.cranaya.ligaespaola.activities;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.cranaya.ligaespaola.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class EquiposActivityTest {

    public static final int ITEM_BELOW_FIND_IN_LIST = 5;

    @Rule
    public ActivityTestRule<EquiposActivity> equiposActivityActivityTestRule = new ActivityTestRule<>(EquiposActivity.class);



    /**
     * Select list item, nav to Detail
     * correct name team is in view?
     */
    @Test
    public void ItemTest(){

        Espresso.onView(ViewMatchers.withId(R.id.viewListTeam)).perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_FIND_IN_LIST,click()));
        String itemval = "Barcelona";
        Espresso.onView(withText(itemval)).check(matches(isDisplayed()));
    }



    /**
     * Select list item, nav to Detail
     * correct stadium team is in view?
     */
    @Test
    public void nameStadiumTeamTest(){

        Espresso.onView(ViewMatchers.withId(R.id.viewListTeam)).perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_FIND_IN_LIST,click()));

        String itemval = "Benito Villamarín";
        Espresso.onView(withText(itemval)).check(matches(isDisplayed()));
    }

}