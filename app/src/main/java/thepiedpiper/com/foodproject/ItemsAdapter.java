package thepiedpiper.com.foodproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import thepiedpiper.com.foodproject.logic.read.country.Item;

class ItemsAdapter extends ArrayAdapter<Item> {

    private List<Item> items;
    private Activity context;

    public ItemsAdapter(Activity context, List<Item> items) {
        super(context, 0, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.item_spinner, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.filter_spinner_text);
        textView.setText(items.get(position).getItemName());

        return listItemView;
    }
}