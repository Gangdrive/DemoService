package gangdrive.gang.demoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gangdrive.gang.demoservice.db.Accessories;
import gangdrive.gang.demoservice.db.CarData;
import gangdrive.gang.demoservice.viewmodel.AccessoriesActivityViewModel;
import gangdrive.gang.demoservice.viewmodel.MainActivityViewModel;


public class AccessoriesActivity extends AppCompatActivity implements AccessoriesListAdapter.HandleAccessoriesClick {

    private AccessoriesActivityViewModel viewModel;
    private TextView noResultTextView;
    private RecyclerView recyclerView;
    private AccessoriesListAdapter accessoriesListAdapter;
    private Accessories accessoriesForEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        getSupportActionBar().setTitle("Автомобильные аксессуары");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noResultTextView = findViewById(R.id.noResult);
        recyclerView = findViewById(R.id.recyclerView);

        ImageView addNew = findViewById(R.id.addNewAccessoriesImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccessoriesDialog(false);
            }
        });

        initViewModel();
        initRecyclerView();
        viewModel.getAllAccessoriesList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        accessoriesListAdapter = new AccessoriesListAdapter(this, this);
        recyclerView.setAdapter(accessoriesListAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AccessoriesActivityViewModel.class);
        viewModel.getAccessoriesObserverList().observe(this, new Observer<List<Accessories>>() {
            @Override
            public void onChanged(List<Accessories> accessories) {
                if (accessories == null) {
                    noResultTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //показать RecyclerView
                    accessoriesListAdapter.setAccessories(accessories);
                    recyclerView.setVisibility(View.VISIBLE);
                    noResultTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showAccessoriesDialog(boolean isForEdit) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.add_accessories_layout, null);
        EditText enterAccessoriesInput = dialogView.findViewById(R.id.enterAccessoriesInput);
        TextView createButton = dialogView.findViewById(R.id.createButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);

        if (isForEdit) {
            createButton.setText("Update");
            enterAccessoriesInput.setText(accessoriesForEdit.accessoriesName);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = enterAccessoriesInput.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(AccessoriesActivity.this, "Выберите название заголовка", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isForEdit) {
                    accessoriesForEdit.accessoriesName = name;
                    viewModel.updateAccessories(accessoriesForEdit);
                } else {
                    //здесь нам нужно вызвать view model
                    viewModel.insertAccessories(name);
                }
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
   /* @Override
    public void itemClick(CarData carData) {
        Intent intent = new Intent(AccessoriesActivity.this, ShowItemsListActivity.class);
        intent.putExtra("carData_id", carData.uid);
        intent.putExtra("carData_name", carData.carDataName);

        startActivity(intent);
    }*/

    @Override
    public void removeItem(Accessories accessories) {
        viewModel.deleteCarData(accessories);
    }

    @Override
    public void editItem(Accessories accessories) {
        this.accessoriesForEdit = accessories;
        showAccessoriesDialog(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accessories_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menuReferece3:
                intent = new Intent(this, Reference3Activity.class);
                startActivity(intent);
                break;
            case R.id.goMainActivity:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menuRecommendations:
                intent = new Intent(this, MenuRecommendationsActivity.class);
                startActivity(intent);
                break;
            case R.id.menuTo:
                intent = new Intent(this, ToActivity.class);
                startActivity(intent);
                break;
          /*  case R.id.menuWashing:
                intent = new Intent(this,WashingActivity.class);
                startActivity(intent);
                break;*/

        }
        return true;
    }

}