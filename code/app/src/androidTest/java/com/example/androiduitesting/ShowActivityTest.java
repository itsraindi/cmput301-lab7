package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    public void addCity(String cityName) {
        // Add testCity
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(cityName));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    public void clickOnCity() {
        onData(is(instanceOf(String.class))).inAdapterView((withId(R.id.city_list))).atPosition(0).perform(click());
    }

    // Test 1 - check whether activity successfully switched
    @Test
    public void testSwitchToShowActivity() {
        // setup
        String testCity = "Edmonton";
        addCity(testCity); clickOnCity();

        // Check elements present in the ShowActivity
        onView(withId(R.id.showactivity_city_text)).check(matches(isDisplayed()));
        onView(withId(R.id.showactivity_return_button)).check(matches(isDisplayed()));
    }
    // Test 2 - test whether city name is consistent
    @Test
    public void testCityName() {
        // setup
        String testCity = "Edmonton";
        addCity(testCity); clickOnCity();

        // Check text is present
        onView(withText(testCity)).check(matches(isDisplayed()));
    }

    // Test 3 - test the "back" button
    @Test
    public void testBackButton() {
        // setup
        String testCity = "Edmonton";
        addCity(testCity); clickOnCity();

        // click on return
        onView(withId(R.id.showactivity_return_button)).perform(click());
        // After returning, confirm element does not exist
        onView(withId(R.id.showactivity_return_button)).check(doesNotExist());
    }
}
