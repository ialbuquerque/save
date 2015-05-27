package com.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.Account;
import com.domain.Expense;
import com.domain.Income;
import com.domain.Operator;
import com.save.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 26/05/2015.
 */
public class OperationsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Operator> op;

    public OperationsAdapter(Context context, ArrayList<Operator> op){
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
        Operator operator1 = new Operator();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate((R.layout.operations),null);

        TextView tv1 =(TextView) layout.findViewById(R.id.tv1);
        TextView tv2 =(TextView) layout.findViewById(R.id.tv2);
        TextView tv3 =(TextView) layout.findViewById(R.id.tv3);
        tv1.setText(operator1.getName());
        tv2.setText(operator1.getType());
        tv3.setText(Double.toString(operator1.getValue()));
        return layout;
    }
}
