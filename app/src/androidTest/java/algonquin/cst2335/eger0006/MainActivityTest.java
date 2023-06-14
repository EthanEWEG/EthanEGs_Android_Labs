package algonquin.cst2335.eger0006;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This function tests for the password missing a upper case letter
     */
    @Test
    public void testFindMissingUC(){
        //finds the password editText and sets the text to 12345
        ViewInteraction appCompatEditText = onView( withId(R.id.password) );
        appCompatEditText.perform(replaceText("password123#$*"), closeSoftKeyboard());

        //finds the login Button and clicks it
        ViewInteraction materialButton = onView( withId(R.id.button) );
        materialButton.perform(click());

        //finds the textView and checks to make sure it says what is required
        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This function tests for the password missing a lower case letter
     */
    @Test
    public void testFindMissingLC() {
        //finds the password editText and sets the text to 12345
        ViewInteraction appCompatEditText = onView( withId(R.id.password) );
        appCompatEditText.perform(replaceText("PASSWORD123#$*"), closeSoftKeyboard());

        //finds the login Button and clicks it
        ViewInteraction materialButton = onView( withId(R.id.button) );
        materialButton.perform(click());

        //finds the textView and checks to make sure it says what is required
        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This function tests for the password missing a number
     */
    @Test
    public void testFindMissingN() {
        //finds the password editText and sets the text to 12345
        ViewInteraction appCompatEditText = onView( withId(R.id.password) );
        appCompatEditText.perform(replaceText("PASSword#$*"), closeSoftKeyboard());

        //finds the login Button and clicks it
        ViewInteraction materialButton = onView( withId(R.id.button) );
        materialButton.perform(click());

        //finds the textView and checks to make sure it says what is required
        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This function tests for the password missing a special character
     */
    @Test
    public void testFindMissingS() {
        //finds the password editText and sets the text to 12345
        ViewInteraction appCompatEditText = onView( withId(R.id.password) );
        appCompatEditText.perform(replaceText("PASSword123"), closeSoftKeyboard());

        //finds the login Button and clicks it
        ViewInteraction materialButton = onView( withId(R.id.button) );
        materialButton.perform(click());

        //finds the textView and checks to make sure it says what is required
        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This function tests for the password having all requirements
     */
    @Test
    public void testPasswordPasses() {
        //finds the password editText and sets the text to 12345
        ViewInteraction appCompatEditText = onView( withId(R.id.password) );
        appCompatEditText.perform(replaceText("PASSword123#$*"), closeSoftKeyboard());

        //finds the login Button and clicks it
        ViewInteraction materialButton = onView( withId(R.id.button) );
        materialButton.perform(click());

        //finds the textView and checks to make sure it says what is required
        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("Your password meets the requirements")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
