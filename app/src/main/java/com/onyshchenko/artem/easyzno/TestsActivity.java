package com.onyshchenko.artem.easyzno;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestsActivity extends AppCompatActivity {

    private static String EXTRA_SUBJECT_ID = "com.artem.onyshchenko.easyzno.subject_id";

    public static Intent newIntent(Context context, int subjectId) {
        Intent intent = new Intent(context, TestsActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MainActivity.TAG, "TestsActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        Fragment testsFragment = getSupportFragmentManager().findFragmentById(R.id.tests_container);
        if(testsFragment == null) {
            testsFragment = TestsFragment.newInstance(getIntent().getIntExtra(EXTRA_SUBJECT_ID, 0));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tests_container, testsFragment)
                    .commit();
        }
    }
}
