package com.example.materialdesignfromgithub.activity.progressactivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.example.materialdesignfromgithub.R;
import com.example.materialdesignfromgithub.adapter.AdapterListFolderFile;
import com.example.materialdesignfromgithub.model.FolderFile;
import com.example.materialdesignfromgithub.utils.ItemAnimation;
import com.example.materialdesignfromgithub.utils.Tools;
import com.example.materialdesignfromgithub.utils.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

public class ProgressDotsGrow extends AppCompatActivity {

    private View parent_view;
    private final static int LOADING_DURATION = 3500;

    private RecyclerView recyclerView;
    private AdapterListFolderFile mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dots_grow);
        parent_view = findViewById(android.R.id.content);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.orange_500);

        loadingAndDisplayContent();
    }

    private void loadingAndDisplayContent() {
        final LinearLayout lyt_progress = (LinearLayout) findViewById(R.id.lyt_progress);
        lyt_progress.setVisibility(View.VISIBLE);
        lyt_progress.setAlpha(1.0f);
        recyclerView.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewAnimation.fadeOut(lyt_progress);
            }
        }, LOADING_DURATION);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initComponent();
            }
        }, LOADING_DURATION + 400);
    }

    private void initComponent() {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<FolderFile> items = new ArrayList<>();

        items.add(new FolderFile("Folders", true));  // add section

        items.add(new FolderFile("Photos", "Jan 9, 2014", R.drawable.ic_folder, true));
        items.add(new FolderFile("Recipes", "Jan 17, 2014", R.drawable.ic_folder, true));
        items.add(new FolderFile("Work", "Jan 28, 2014", R.drawable.ic_folder, true));

        items.add(new FolderFile("Files", true));   // add section

        items.add(new FolderFile("Vacation itinerary", "Jan 20, 2014", R.drawable.ic_insert_drive, false));
        items.add(new FolderFile("Kitchen Remodel", "Jan 10, 2014", R.drawable.ic_insert_drive, false));
        items.add(new FolderFile("To Do Note", "Des 25, 2013", R.drawable.ic_insert_drive, false));

        items.add(new FolderFile("", true));   // add section

        //set data and list adapter
        mAdapter = new AdapterListFolderFile(this, items, ItemAnimation.FADE_IN);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListFolderFile.OnItemClickListener() {
            @Override
            public void onItemClick(View view, FolderFile obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_refresh) {
            loadingAndDisplayContent();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
