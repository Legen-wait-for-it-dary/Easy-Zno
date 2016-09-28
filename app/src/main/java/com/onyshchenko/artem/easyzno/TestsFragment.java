package com.onyshchenko.artem.easyzno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onyshchenko.artem.easyzno.model.Test;
import com.onyshchenko.artem.easyzno.model.TestsContainer;

import java.util.List;

public class TestsFragment extends Fragment {
    private String TAG = "TestsFragment";

    private RecyclerView testsRecyclerView;
    private int currentSubject = 0;

    private String KEY_CURRENT_SUBJECT = "KEY_CURRENT_SUBJECT";

    private static String ARG_CURRENT_SUBJECT = "ARG_CURRENT_SUBJECT";

    public static TestsFragment newInstance(int currentSubjectId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_SUBJECT, currentSubjectId);
        TestsFragment testsFragment = new TestsFragment();
        testsFragment.setArguments(args);
        return testsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(MainActivity.TAG, "TestsFragment onCreate");
        super.onCreate(savedInstanceState);
        currentSubject = getArguments().getInt(ARG_CURRENT_SUBJECT, 0);
        if(savedInstanceState != null) {
            currentSubject = savedInstanceState.getInt(KEY_CURRENT_SUBJECT, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(MainActivity.TAG, "TestsFragment onCreateView");
        View v = inflater.inflate(R.layout.tests_fragment, container, false);
        testsRecyclerView = (RecyclerView) v.findViewById(R.id.testsRecyclerView);
        testsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI(currentSubject);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_SUBJECT, currentSubject);
    }

    public void updateUI(int currentSubject) {
        this.currentSubject = currentSubject;
        testsRecyclerView.setAdapter(new TestsAdapter(TestsContainer.getInstance().getTests(currentSubject)));
    }

    private class TestsHolder extends RecyclerView.ViewHolder {

        TextView nameTxtView;
        TextView yearTxtView;
        TextView numberQuestionsTxtView;
        TextView averageMarkTxtView;
        TextView averageTimeTxtView;

        public TestsHolder(View itemView) {
            super(itemView);
            nameTxtView = (TextView)itemView.findViewById(R.id.nameTxtView);
            yearTxtView = (TextView)itemView.findViewById(R.id.yearTxtView);
            numberQuestionsTxtView = (TextView)itemView.findViewById(R.id.numberQuestionsTxtView);
            averageMarkTxtView = (TextView)itemView.findViewById(R.id.averageMarkTxtView);
            averageTimeTxtView = (TextView)itemView.findViewById(R.id.averageTimeTxtView);
        }

        public void bindView(Test test) {
            Log.d(TAG, (nameTxtView == null) ? "null":"not null");
            nameTxtView.setText(test.getName());
            yearTxtView.setText(String.valueOf(test.getYear()));
            numberQuestionsTxtView.setText(String.valueOf(test.getNumberQuestions()));
            averageMarkTxtView.setText(String.valueOf(test.getAverageMark()));
            averageTimeTxtView.setText(String.valueOf(test.getAverageTime()));
        }
    }

    private class TestsAdapter extends RecyclerView.Adapter<TestsHolder> {
        private List<Test> tests;

        public TestsAdapter(List<Test> tests) {
            this.tests = tests;
        }

        @Override
        public TestsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_test, parent, false);
            return new TestsHolder(view);
        }

        @Override
        public void onBindViewHolder(TestsHolder holder, int position) {
            holder.bindView(tests.get(position));
        }

        @Override
        public int getItemCount() {
            return tests.size();
        }
    }
}
