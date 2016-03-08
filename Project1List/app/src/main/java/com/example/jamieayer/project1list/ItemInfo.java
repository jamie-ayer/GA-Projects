package com.example.jamieayer.project1list;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ItemInfo extends AppCompatActivity {

    public static final String DESCRIPTION = "itemdescription";
    public static final String ITEM_SERIALIZABLE_KEY = "itemSerializableKey";
    public static final int RESULT_DELETE = 900;

    String[] info;
    TextView itemTitle;
    TextView itemLoc;
    TextView itemDes;
    TextView itemDate;

    ArrayList<String> mListItems;
    ArrayAdapter<String> mAdapter;
    ListView todoItems;
    Button done;

    Item item;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info_activity);

        itemTitle = (TextView)findViewById(R.id.itemTitle);
        itemLoc = (TextView)findViewById(R.id.itemLocation);
        itemDes = (TextView)findViewById(R.id.itemDescription);
        todoItems = (ListView)findViewById(R.id.infoListView);
        itemDate = (TextView)findViewById(R.id.itemDate);
        done = (Button)findViewById(R.id.backButton);

        if(mListItems == null)
            mListItems = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        Intent data = getIntent();

        item = (Item)data.getSerializableExtra(ITEM_SERIALIZABLE_KEY);

        mListItems = item.getmItemsArray();


        mAdapter = new ArrayAdapter<>(ItemInfo.this, android.R.layout.simple_list_item_1, mListItems);
        todoItems = (ListView)findViewById(R.id.infoListView);
        todoItems.setAdapter(mAdapter);

        info = extras.getStringArray(DESCRIPTION);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        itemDate.setText("Created: " + dateFormat.format(item.getmItemDate()));

        itemTitle.setText(item.getmItemTitle().toUpperCase());
        itemLoc.setText("Location: " + item.getmLocation());
        itemDes.setText("Description: "+item.getmDescription());
        x = item.getmItemPosition();

        todoItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView currentItem = (TextView) view;
                strikeThroughTextView(currentItem);
                //PopupMenu popup = new PopupMenu(MainActivity.this);
            }
        });

        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListItems.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                item.setmItemsArray(mListItems);

                intent.putExtra(ITEM_SERIALIZABLE_KEY, item);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }

    public void delete(View view)
    {
        Intent intent = getIntent();
        intent.putExtra(ITEM_SERIALIZABLE_KEY, x);
        setResult(RESULT_DELETE, intent);
        finish();
    }

    public void strikeThroughTextView(TextView currentItem){
        int paintFlags = currentItem.getPaintFlags();
        int noStrikeThroughFlag = 1281;
        if(paintFlags == noStrikeThroughFlag){
            currentItem.setPaintFlags(paintFlags | Paint.STRIKE_THRU_TEXT_FLAG);
            currentItem.setBackgroundColor(Color.RED);
        } else{
            currentItem.setPaintFlags(noStrikeThroughFlag);
            currentItem.setBackgroundColor(Color.WHITE);
        }
    }

}
