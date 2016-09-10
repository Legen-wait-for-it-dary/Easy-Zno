package com.onyshchenko.artem.easyzno;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private static final String [] subjects = {"Математика", "Українська мова і література", "Англійська мова"};
    private static final Integer [] numberTests = {20, 10, 16};

    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.subjectsListView);
        SubjectsAdapter adapter = new SubjectsAdapter(this, subjects, numberTests);
        listView.setAdapter(adapter);
    }

    public static class SubjectsAdapter extends BaseAdapter {

        private final String [] subjects;
        private final Integer [] numberTests;
        private final Context context;
        private final LayoutInflater layoutInflater;

        public SubjectsAdapter(Context context, String [] subjects, Integer [] numberTests) {
            this.context = context;
            this.subjects = subjects;
            this.numberTests = numberTests;
            layoutInflater = LayoutInflater.from(this.context);
        }

        static class ViewHolder {
            TextView subjectNameTxtView;
            TextView subjectNameAbbreviationTxtView;
            TextView testsNumberTxtView;
            TextView rightBorderTxtView;
        }

        @Override
        public int getCount() {
            return subjects.length;
        }

        @Override
        public Object getItem(int position) {
            return subjects[position];
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
                convertView = layoutInflater.inflate(R.layout.subject_item_list_row, null);
                viewHolder = new ViewHolder();
                viewHolder.subjectNameTxtView = (TextView) convertView.findViewById(R.id.subjectNameTxtView);
                viewHolder.subjectNameAbbreviationTxtView = (TextView) convertView.findViewById(R.id.subjectNameAbbreviationTxtView);
                viewHolder.testsNumberTxtView = (TextView) convertView.findViewById(R.id.testsNumberTxtView);
                viewHolder.rightBorderTxtView = (TextView) convertView.findViewById(R.id.rightBorderTxtView);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            color = context.getResources().getIntArray(R.array.flat_colors)[position];

            viewHolder.subjectNameAbbreviationTxtView.setText(String.valueOf(subjects[position].charAt(0)));
            viewHolder.subjectNameAbbreviationTxtView.setBackgroundColor(color);
            viewHolder.subjectNameTxtView.setText(subjects[position]);
            viewHolder.testsNumberTxtView.setText(numberTests[position].toString());
            viewHolder.rightBorderTxtView.setBackgroundColor(color);
            return convertView;
        }
    }
}
