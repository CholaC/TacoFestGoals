package com.tacofest.goals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Julie on 2016-10-31.
 */

public class CourseListArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private final String[] values;

    public CourseListArrayAdapter(Context context, String[] values) {
        super(context, R.layout.course_list_item, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.course_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txtCourseName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgProgressBar);
        textView.setText(values[position]);
        imageView.setImageResource(R.drawable.full_battery);

        return rowView;
    }
}
