package com.onyshchenko.artem.easyzno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.onyshchenko.artem.easyzno.model.Subject;
import com.onyshchenko.artem.easyzno.model.SubjectContainer;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "EasyZno";
    private int currentSubjectIndex = 0;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gatherWidgets();
        setEventListeners();
        listView.setAdapter(new SubjectsAdapter(this, SubjectContainer.getInstance().getSubjects()));

        if(isMultiPane()) {
            Fragment testsFragment = getSupportFragmentManager().findFragmentById(R.id.tests_container);
            if(testsFragment == null) {
                testsFragment = TestsFragment.newInstance(currentSubjectIndex);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.tests_container, testsFragment)
                        .commit();
            }
        }
    }

    private void gatherWidgets() {
        listView = (ListView)findViewById(R.id.subjectsListView);
    }

    private void setEventListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int subjectId = ((Subject)listView.getItemAtPosition(position)).getId();
                TestsFragment testsFragment = (TestsFragment) getSupportFragmentManager().findFragmentById(R.id.tests_container);
                if (isMultiPane()) {
                    testsFragment.updateUI(subjectId);
                } else {
                    Intent intent = TestsActivity.newIntent(getApplicationContext(), subjectId);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isMultiPane() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static class SubjectsAdapter extends BaseAdapter {

        private final List<Subject> subjects;
        private final Context context;
        private final LayoutInflater layoutInflater;

        public SubjectsAdapter(Context context, List<Subject> subjects) {
            this.context = context;
            this.subjects = subjects;
            layoutInflater = LayoutInflater.from(this.context);
        }

        static class ViewHolder{
            private int subjectId;
            TextView subjectNameTxtView;
            TextView subjectNameAbbreviationTxtView;
            TextView testsNumberTxtView;
            TextView rightBorderTxtView;

            public ViewHolder(int subjectId) {
                this.subjectId = subjectId;
            }

        }

        @Override
        public int getCount() {
            return subjects.size();
        }

        @Override
        public Object getItem(int position) {
            return subjects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int color = 0;
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.tests_item_list_row, null);
                viewHolder = new ViewHolder(SubjectContainer.getInstance().getSubjects().get(position).getId());
                viewHolder.subjectNameTxtView = (TextView) convertView.findViewById(R.id.subjectNameTxtView);
                viewHolder.subjectNameAbbreviationTxtView = (TextView) convertView.findViewById(R.id.subjectNameAbbreviationTxtView);
                viewHolder.testsNumberTxtView = (TextView) convertView.findViewById(R.id.testsNumberTxtView);
                viewHolder.rightBorderTxtView = (TextView) convertView.findViewById(R.id.rightBorderTxtView);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            color = context.getResources().getIntArray(R.array.flat_colors)[position];

            viewHolder.subjectNameAbbreviationTxtView.setText(String.valueOf(subjects.get(position).getName().charAt(0)));
            viewHolder.subjectNameAbbreviationTxtView.setBackgroundColor(color);
            viewHolder.subjectNameTxtView.setText(subjects.get(position).getName());
            viewHolder.testsNumberTxtView.setText(String.valueOf(subjects.get(position).getNumberTests()));
            viewHolder.rightBorderTxtView.setBackgroundColor(color);
            return convertView;
        }
    }
}
