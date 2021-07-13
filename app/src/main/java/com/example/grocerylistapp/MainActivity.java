package com.example.grocerylistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ListView listView;
    static ArrayList<String> items;
    static ListViewAdapter adapter;
    EditText input;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        input = findViewById(R.id.input);
        add = findViewById(R.id.add);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = items.get(position);
                // makeToast(s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String e = items.get(position);
                makeToast("Removed " + e);
                removeItems(position);
                return false;
            }
        });
        items = new ArrayList<>();
        items.add("Mango");
        items.add("Apple");
        items.add("Kiwi");
        items.add("Banana");
        items.add("Peach");
        adapter = new ListViewAdapter(getApplicationContext(), items);
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (text == null || text.length() == 0) {
                    makeToast("Enter an item.");
                } else {
                    addItem(text);
                    input.setText("");
                    makeToast("Added:" + text);
                }
            }
        });
    }

    public static void addItem(String item) {
        items.add(item);
        listView.setAdapter(adapter);
    }

    public static void removeItems(int item) {
        items.remove(item);
        adapter.notifyDataSetChanged();

    }

    Toast t;

    private void makeToast(String s) {
        if (t != null) t.cancel();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}