package algonquin.cst2335.rust0018;


import static androidx.test.espresso.Espresso.onView;
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
     * Tests if password editText refuses passwords with uniquely numeric characters.
     */
    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall not pass")));
    }

    /**
     * Tests if password editText refuses passwords that is missing an uppercase character.
     */
    @Test
    public void testFindMissingUpperCase() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("password123#%"));

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall not pass")));
    }

    /**
     * Tests if password editText refuses passwords that is missing an lowercase character.
     */
    @Test
    public void testFindMissingLowerCase() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("PASSW123@#"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall not pass")));
    }

    /**
     * Tests if password editText refuses passwords that is missing a special character.
     */
    @Test
    public void testMissingSpecialCharacter() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("Password123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall not pass")));
    }

    /**
     * Tests if password editText refuses passwords that is missing a numeric character.
     */
    @Test
    public void testMissingNumericCharacter() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("Pass@%#"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall not pass")));
    }

    /**
     * Tests if password editText accepts passwords that meets all requirements.
     */
    @Test
    public void testHasAllRequirements() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("Passw123@#"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                withId(R.id.buttonLogin));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.textView));
        textView.check(matches(withText("you shall pass")));
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
