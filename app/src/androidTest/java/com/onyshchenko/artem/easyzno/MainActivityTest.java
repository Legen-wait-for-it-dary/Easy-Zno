package com.onyshchenko.artem.easyzno;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import com.onyshchenko.artem.easyzno.model.Subject;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSubjectsListView() {
        onData(allOf(is(instanceOf(Subject.class)), withSubjectTitle("Матматика"))).perform(click());
    }

    public static Matcher<Object> withSubjectTitle(final String subjectName) {
        return new BoundedMatcher<Object, Subject>(Subject.class) {
            @Override
            protected boolean matchesSafely(Subject subject) {
                return subjectName.equals(subject.getName());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with id: " + subjectName);
            }
        };
    }


}
