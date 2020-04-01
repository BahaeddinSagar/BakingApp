package ly.bsagar.bakingapp;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import static org.hamcrest.core.StringContains.containsString;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestingLists {

    @Rule
    public ActivityTestRule<MainActivity> newRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkList0() throws InterruptedException {
        //simple test, since testing with recyclerview is not that simple
        Thread.sleep(2000);
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.ingredient_contains)).check(matches(withText(containsString("Nutella Pie"))));
        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.preperation_steps)).check(matches(withText("Preperation Steps:")));

    }
    @Test
    public void checkList1() throws InterruptedException {
        //simple test, since testing with recyclerview is not that simple
        Thread.sleep(2000);
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        Thread.sleep(2000);
        onView(withId(R.id.ingredient_contains)).check(matches(withText(containsString("Brownies"))));
        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.preperation_steps)).check(matches(withText("Preperation Steps:")));

    }
    @Test
    public void checkList2() throws InterruptedException {
        //simple test, since testing with recyclerview is not that simple
        Thread.sleep(2000);
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        Thread.sleep(2000);
        onView(withId(R.id.ingredient_contains)).check(matches(withText(containsString("Yellow Cake"))));
        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.preperation_steps)).check(matches(withText("Preperation Steps:")));

    }
    @Test
    public void checkList3() throws InterruptedException {
        //simple test, since testing with recyclerview is not that simple
        Thread.sleep(2000);
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        Thread.sleep(2000);
        onView(withId(R.id.ingredient_contains)).check(matches(withText(containsString("Cheesecake"))));
        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.preperation_steps)).check(matches(withText("Preperation Steps:")));

    }


}
