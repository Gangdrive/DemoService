package gangdrive.gang.demoservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gangdrive.gang.demoservice.db.ItemsTo;
import gangdrive.gang.demoservice.viewmodel.ShowItemToListActivityViewModel;

public class ShowItemsToListActivity extends AppCompatActivity implements ItemsToListAdapter.HandleItemsToClick {

    private int to_id;
    private ItemsToListAdapter itemsToListAdapter;
    private ShowItemToListActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private ItemsTo itemToUpdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items_to_list);

        to_id = getIntent().getIntExtra("to_id", 0);
        String toName = getIntent().getStringExtra("to_name");

        getSupportActionBar().setTitle(toName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText addNewItemInput = findViewById(R.id.addNewItemInput);
        ImageView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = addNewItemInput.getText().toString();
                if (TextUtils.isEmpty(itemName)) {
                    Toast.makeText(ShowItemsToListActivity.this, "Введите значение", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemToUpdate == null)
                    saveNewItem(itemName);
                else
                    updateNewItem(itemName);
            }
        });
        initRecyclerView();
        initViewModel();
        viewModel.getAllItemsList(to_id);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ShowItemToListActivityViewModel.class);
        viewModel.getItemsObserverList().observe(this, new Observer<List<ItemsTo>>() {
            @Override
            public void onChanged(List<ItemsTo> items) {
                if (items == null) {
                    recyclerView.setVisibility(View.GONE);
                    findViewById(R.id.noResult).setVisibility(View.VISIBLE);
                } else {
                    itemsToListAdapter.setTo(items);
                    findViewById(R.id.noResult).setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsToListAdapter = new ItemsToListAdapter(this, this);
        recyclerView.setAdapter(itemsToListAdapter);
    }

    private void saveNewItem(String itemName) {
        ItemsTo item = new ItemsTo();
        item.itemNameTo = itemName;
        item.toId = to_id;
        viewModel.insertItemsTo(item);
        ((EditText) findViewById(R.id.addNewItemInput)).setText("");
    }

    @Override
    public void itemToClick(ItemsTo item) {
        if (item.completedTo) {
            item.completedTo = false;
        } else {
            item.completedTo = true;
        }
        viewModel.updateItemsTo(item);
    }

    @Override
    public void removeToItem(ItemsTo item) {
        viewModel.deleteItemsTo(item);
    }

    @Override
    public void editToItem(ItemsTo item) {
        this.itemToUpdate = item;
        ((EditText) findViewById(R.id.addNewItemInput)).setText(item.itemNameTo);
    }

    private void updateNewItem(String newName) {
        itemToUpdate.itemNameTo = newName;
        viewModel.updateItemsTo(itemToUpdate);
        ((EditText) findViewById(R.id.addNewItemInput)).setText("");
        itemToUpdate = null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reference2_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menuReference2:
                Intent intent = new Intent(this, Reference2Activity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}