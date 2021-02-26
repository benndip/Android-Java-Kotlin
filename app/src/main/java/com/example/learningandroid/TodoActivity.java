package com.example.learningandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    private void setUpListViewListener() {
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Context context = getApplicationContext();
               Toast.makeText(context,"item removed",Toast.LENGTH_SHORT).show();
               items.remove(position);
               itemsAdapter.notifyDataSetChanged();
               return true;
           }
       });
    }

    private void addItem(View view) {
        EditText inputTodo = findViewById(R.id.inputTodo);
        String itemTxt = inputTodo.getText().toString();
        if(!itemTxt.equals("")){
            itemsAdapter.add(itemTxt);
            inputTodo.setText("");
        }else{
            Toast.makeText(getApplicationContext(),"Please enter a text",Toast.LENGTH_SHORT).show();
        }
    }
}