package uk.co.swa.swapp.controller;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.swa.swapp.model.CompetitionEntrant;
import uk.co.swa.swapp.model.Member;

/**
 * Created by oliver on 03/04/2016.
 */
public class FilterableMemberListAdapter extends BaseAdapter implements Filterable {

    private List<Member> originalData = null;
    private List<Member> filteredData = null;
    private int resource;
    private LayoutInflater layoutInflater;

    private static class ViewHolder {
        TextView text1;
        TextView text2;
    }

    FilterableMemberListAdapter(Context context,
                                @LayoutRes int resource,
                                List<Member> competitionEntrants) {

        this.originalData = competitionEntrants;
        this.filteredData = competitionEntrants;
        this.resource = resource;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filteredData.get(position).getAppID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder keeps reference to child views to avoid unnecessary calls to findViewById().
        ViewHolder holder;

        // When convertView is not null, reuse it directly, there is no need to re-inflate it.
        // Only new to inflate a new View when convertView is null.
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, parent, false);

            // Create a new ViewHolder and store references to the two child views to bind data to.
            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(android.R.id.text2);

            // Bind the data efficiently to the holder.
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(filteredData.get(position).getName());
        holder.text2.setText(filteredData.get(position).getUniversity().getUniversityName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String[] filters = constraint.toString().toLowerCase().split(" ");

                FilterResults filterResults = new FilterResults();

                List<Member> list = originalData;

                int count = list.size();
                List<Member> filteredList = new ArrayList<>(count);

                String filterableName;
                String filterableUni;
                boolean addEntrant;

                for (Member member : list) {
                    filterableName = member.getName().toLowerCase();
                    filterableUni = member.getUniversity().getUniversityName().toLowerCase();

                    addEntrant = true;

                    for (String filter : filters) {
                        if (!filterableName.contains(filter) && !filterableUni.contains(filter)) {
                            addEntrant &= false;
                        }
                    }

                    if (addEntrant) {
                        filteredList.add(member);
                    }
                }

                filterResults.values = filteredList;
                filterResults.count = filteredList.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (List<Member>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
