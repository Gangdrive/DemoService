package gangdrive.gang.demoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import gangdrive.gang.demoservice.db.Items;
import gangdrive.gang.demoservice.viewmodel.ShowItemListActivityViewModel;

public class ShowItemsListActivity extends AppCompatActivity implements ItemsListAdapter.HandleItemsClick {

    private int carData_id;
    private ItemsListAdapter itemsListAdapter;
    private ShowItemListActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private Items itemToUpdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items_list);

        carData_id = getIntent().getIntExtra("carData_id", 0);
        String carDataName = getIntent().getStringExtra("carData_name");

        getSupportActionBar().setTitle(carDataName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText addNewItemInput = findViewById(R.id.addNewItemInput);
        ImageView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = addNewItemInput.getText().toString();
                if (TextUtils.isEmpty(itemName)) {
                    Toast.makeText(ShowItemsListActivity.this, "Введите значение", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemToUpdate == null)
                    saveNewItem(itemName+"км");
                else
                    updateNewItem(itemName+"км");
            }
        });
        initRecyclerView();
        initViewModel();
        viewModel.getAllItemsList(carData_id);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ShowItemListActivityViewModel.class);
        viewModel.getItemsObserverList().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {
                if (items == null) {
                    recyclerView.setVisibility(View.GONE);
                    findViewById(R.id.noResult).setVisibility(View.VISIBLE);
                } else {
                    itemsListAdapter.setCarData(items);
                    findViewById(R.id.noResult).setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsListAdapter = new ItemsListAdapter(this, this);
        recyclerView.setAdapter(itemsListAdapter);
    }

    private void saveNewItem(String itemName) {
        Items item = new Items();
        item.itemName = itemName;
        item.carId = carData_id;
        viewModel.insertItems(item);
        ((EditText) findViewById(R.id.addNewItemInput)).setText("");
    }

    @Override
    public void itemClick(Items item) {
        if (item.completed) {
            item.completed = false;
        } else {
            item.completed = true;
        }
        viewModel.updateItems(item);
    }

    @Override
    public void removeItem(Items item) {
        viewModel.deleteItems(item);
    }

    @Override
    public void editItem(Items item) {
        this.itemToUpdate = item;
        ((EditText) findViewById(R.id.addNewItemInput)).setText(item.itemName);
    }

    private void updateNewItem(String newName) {
        itemToUpdate.itemName = newName;
        viewModel.updateItems(itemToUpdate);
        ((EditText) findViewById(R.id.addNewItemInput)).setText("");
        itemToUpdate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reference1_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menuReference1:
                Intent intent = new Intent(this, Reference1Activity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}