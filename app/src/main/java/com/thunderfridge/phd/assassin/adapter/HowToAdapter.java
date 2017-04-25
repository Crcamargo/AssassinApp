package com.thunderfridge.phd.assassin.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thunderfridge.phd.assassin.R;
import com.thunderfridge.phd.assassin.model.ListItem;

import java.util.List;

/**
 * Created by Crist on 4/12/2017.
 */

public class HowToAdapter extends RecyclerView.Adapter<HowToAdapter.howToHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;

    public HowToAdapter(List<ListItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public howToHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new howToHolder(view);
    }

    @Override
    public void onBindViewHolder(howToHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDecscription());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class howToHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;
        private View container;

        public howToHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.howTo_Title);
            description = (TextView) itemView.findViewById(R.id.howTo_Descrip);



            container = itemView.findViewById(R.id.cont_item_root);
        }
    }


}
