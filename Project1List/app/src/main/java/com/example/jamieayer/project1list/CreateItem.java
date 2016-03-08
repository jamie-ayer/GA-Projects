package com.example.jamieayer.project1list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;

public class CreateItem extends AppCompatActivity {

    EditText description;
    EditText title;
    EditText location;
    EditText addItems;
    ListView todoItems;
    ArrayList<String> mListItems;
    ArrayAdapter<String> mAdapter;

    public static final String ITEM_SERIALIZABLE_KEY = "itemSerializableKey";
    private int mRequestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_item_activity);

        description = (EditText)findViewById(R.id.itemDescription);
        addItems = (EditText)findViewById(R.id.editText);
        title = (EditText)findViewById(R.id.itemName);
        location = (EditText)findViewById(R.id.itemLocation);
        todoItems = (ListView)findViewById(R.id.itemStrings);
        mRequestCode = getIntent().getIntExtra(MainActivity.REQUEST_CODE,-1);

        if(mListItems == null)
            mListItems = new ArrayList<>();

        mAdapter = new ArrayAdapter<>(CreateItem.this, android.R.layout.simple_list_item_1, mListItems);
        todoItems = (ListView)findViewById(R.id.itemStrings);
        todoItems.setAdapter(mAdapter);
    }

    public void finish(View view){

        if(title.getText().toString().trim().equalsIgnoreCase(""))
            title.setError("Must name item");
        else {

            Item item = new Item();
            Intent intent = new Intent();

            item.setmItemTitle(title.getText().toString());

            if(description.getText().toString().trim().equalsIgnoreCase(""))
                item.setmDescription("No Description");
            else
                item.setmDescription(description.getText().toString());

            if(location.getText().toString().trim().equalsIgnoreCase(""))
                item.setmLocation("No Location Specified");
            else
                item.setmLocation(location.getText().toString());

            Date date = new Date();
            item.setmItemDate(date);

            item.setmItemsArray(mListItems);

            intent.putExtra(ITEM_SERIALIZABLE_KEY, item);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void add(View view)
    {
        if(addItems.getText().toString().trim().equalsIgnoreCase(""))
            addItems.setError("Enter item");
        else {
            mListItems.add("• " + addItems.getText().toString());
            mAdapter.notifyDataSetChanged();
            addItems.setText("");
        }
    }
}
