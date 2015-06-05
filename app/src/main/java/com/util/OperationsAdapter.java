package com.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.Operation;
import com.save.R;

import java.util.ArrayList;

public class OperationsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Operation> op;

    public OperationsAdapter(Context context, ArrayList<Operation> op){
        this.context=context;
        this.op=op;
    }

    @Override
    public int getCount() {
        return op.size();
    }

    @Override
    public Object getItem(int position) {
        return op.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate((R.layout.operations),null);

        TextView tv1 =(TextView) layout.findViewById(R.id.tv_name_op);
        TextView tv2 =(TextView) layout.findViewById(R.id.tv_value_op);

        tv1.setText(op.get(position).getName());
        tv2.setText(Double.toString(op.get(position).getValue()));
        return layout;
    }
}
