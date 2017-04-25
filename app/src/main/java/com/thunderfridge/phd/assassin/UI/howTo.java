package com.thunderfridge.phd.assassin.UI;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.text.style.*;

import com.thunderfridge.phd.assassin.R;
import com.thunderfridge.phd.assassin.adapter.HowToAdapter;
import com.thunderfridge.phd.assassin.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class howTo extends AppCompatActivity {

    private RecyclerView recView;
    private HowToAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        recView = (RecyclerView) findViewById(R.id.rec_list);
        //Layout manager: Grid or Staggered
        recView.setLayoutManager(new LinearLayoutManager(this));

        Resources res = getResources();
        String[] titles = res.getStringArray(R.array.HowToTitleStrings);
        String[] descriptions = res.getStringArray(R.array.HowToDescripStrings);
        List<ListItem> data = new ArrayList<>();

        for(int i = 0; i < titles.length; i++){
            ListItem item = new ListItem();
            item.setTitle(titles[i]);
            item.setDecscription(descriptions[i]);
            data.add(item);
        }

        adapter = new HowToAdapter(data, this);
        recView.setAdapter(adapter);
    }
}
