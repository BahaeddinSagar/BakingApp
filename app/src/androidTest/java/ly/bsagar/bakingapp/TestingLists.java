package ly.bsagar.bakingapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestingLists {

    @Rule
    public ActivityTestRule<MainActivity> newRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkList() {
        //simple test, since testing with recyclerview is not that simple
        onView(withId(R.id.textview)).check(matches(withText("Behold! our recipies:")));

    }

}
