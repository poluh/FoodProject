package thepiedpiper.com.foodproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class YearAdapter extends ArrayAdapter<Integer> {

    private List<Integer> items;
    private Activity context;

    public YearAdapter(Activity context, List<Integer> items) {
        super(context, 0, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.item_spinner, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.filter_spinner_text);
        textView.setText(items.get(position).toString());

        return listItemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.item_spinner, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.filter_spinner_text);
        textView.setText(items.get(position).toString());

        return listItemView;
    }

}
