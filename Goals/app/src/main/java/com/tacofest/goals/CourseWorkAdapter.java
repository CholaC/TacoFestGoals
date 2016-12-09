package com.tacofest.goals;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Objects;

/**
 * Created by ugochiokehi on 2016-11-13.
 */

public class CourseWorkAdapter extends ArrayAdapter {

    public CourseWorkAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Object object){
        super.add(object);
    }

    public int getCount(){
        return super.getCount();
    }

    public Object getAt(int position){
        return super.getItem(position);
    }
}
