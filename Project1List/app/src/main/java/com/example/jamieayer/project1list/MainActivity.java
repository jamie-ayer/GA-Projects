package com.example.jamieayer.project1list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String REQUEST_CODE = "requestCode";
    public static final String ITEM_SERIALIZABLE_KEY = "itemSerializableKey";
    public static final int RESULT_DELETE = 900;
    public static final int ADD_ITEM = 100;

    ArrayList<Item> mItemArrayList;
    ArrayAdapter<Item> mAdapter;
    ListView mItemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(mItemArrayList == null)
            mItemArrayList = new ArrayList<>();

        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mItemArrayList);
        mItemListView = (ListView)findViewById(R.id.item_list);
        mItemListView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create new item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addItem();
            }
        });

        mItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = mItemArrayList.get(position);

                item.setmItemPosition(position);

                Intent intent = new Intent(MainActivity.this, ItemInfo.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable(ITEM_SERIALIZABLE_KEY, item);

                intent.putExtras(bundle);
                startActivityForResult(intent, RESULT_CANCELED);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addItem()
    {
        Intent intent = new Intent(MainActivity.this, CreateItem.class);
        intent.putExtra(REQUEST_CODE, ADD_ITEM);
        startActivityForResult(intent, ADD_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Item createdItem = (Item)data.getSerializableExtra(CreateItem.ITEM_SERIALIZABLE_KEY);
            mItemArrayList.add(createdItem);
            Log.i("Create result", "created item");
            mAdapter.notifyDataSetChanged();
        }
        else if(resultCode == RESULT_DELETE){
            Integer x = (Integer)data.getSerializableExtra(ItemInfo.ITEM_SERIALIZABLE_KEY);
            mItemArrayList.remove(x.intValue());
            Log.i("Delete result", "removed item");
            mAdapter.notifyDataSetChanged();
        }
        else if(resultCode == RESULT_CANCELED){
            Item createdItem = (Item)data.getSerializableExtra(ItemInfo.ITEM_SERIALIZABLE_KEY);
            Log.i("Change result", "changed item " + createdItem.getmItemPosition());
            mItemArrayList.set(createdItem.getmItemPosition(), createdItem);
            mAdapter.notifyDataSetChanged();
        }
    }
}
