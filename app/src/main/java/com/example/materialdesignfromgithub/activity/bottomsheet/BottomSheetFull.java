package com.example.materialdesignfromgithub.activity.bottomsheet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.materialdesignfromgithub.fragment.FragmentBottomSheetDialogFull;
import com.example.materialdesignfromgithub.R;
import com.example.materialdesignfromgithub.adapter.AdapterPeopleLeft;
import com.example.materialdesignfromgithub.data.DataGenerator;
import com.example.materialdesignfromgithub.model.People;
import com.example.materialdesignfromgithub.utils.Tools;

public class BottomSheetFull extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPeopleLeft adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_full);

        initComponent();
        initToolbar();
        Toast.makeText(this, "Swipe up bottom sheet", Toast.LENGTH_SHORT).show();
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set data and list adapter
        adapter = new AdapterPeopleLeft(this, DataGenerator.getPeopleData(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterPeopleLeft.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int pos) {
                FragmentBottomSheetDialogFull fragment = new FragmentBottomSheetDialogFull();
                fragment.setPeople(obj);
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

        // display first sheet
        FragmentBottomSheetDialogFull fragment = new FragmentBottomSheetDialogFull();
        fragment.setPeople(adapter.getItem(0));
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Full");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
