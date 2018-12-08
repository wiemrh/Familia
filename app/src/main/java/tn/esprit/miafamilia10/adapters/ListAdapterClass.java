package tn.esprit.miafamilia10.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tn.esprit.miafamilia10.Models.Student;
import tn.esprit.miafamilia10.Models.Task;
import tn.esprit.miafamilia10.R;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Task> valueList;

    public ListAdapterClass(List<Task> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_item, null);

            viewItem.TextViewTaskName = convertView.findViewById(R.id.tvi);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewTaskName.setText(valueList.get(position).TaskName);

        return convertView;
    }
}

class ViewItem
{
    TextView TextViewTaskName;

}
