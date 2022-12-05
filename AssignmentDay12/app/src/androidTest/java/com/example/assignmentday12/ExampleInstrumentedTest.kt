package com.example.assignmentday12


import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.example.assignmentday12.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(InterruptedException::class)
    fun visibilityTest() {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerUser))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun recycleViewTest() {
        var recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.recyclerUser)
        var itemCount = recyclerView.adapter?.itemCount
        if (itemCount != null) {
            Espresso.onView(withId(R.id.recyclerUser)).perform(ScrollToBottomAction())
        }
        Thread.sleep(1000)
    }

    @Test
    @Throws(InterruptedException::class)
    fun clickTest() {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerUser))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun itemViewTest() {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerUser))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        1, Matchers.allOf(
                            ViewMatchers.withId(R.id.custom_layout), ViewMatchers.isDisplayed()
                        )
                    )
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun menuTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        Espresso.onView(withText("FlowFragment")).perform(click())
        Thread.sleep(2000)
    }

    @Test
    @Throws(InterruptedException::class)
    fun searchTest() {
        Espresso.onView(withId(R.id.item_search)).perform(click())
        Espresso.onView(isAssignableFrom(SearchView::class.java)).perform(typeSearchViewText("A9"))
        Thread.sleep(3000)
    }

    fun typeSearchViewText(text: String?): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }

    private fun withViewAtPosition(
        position: Int,
        itemMatcher: Matcher<View?>
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    class ScrollToBottomAction : ViewAction {
        override fun getDescription(): String {
            return "Scroll to bottom"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount
            val position = itemCount?.minus(1) ?: 0
            recyclerView.scrollToPosition(position)
            uiController?.loopMainThreadUntilIdle()
        }
    }
}


