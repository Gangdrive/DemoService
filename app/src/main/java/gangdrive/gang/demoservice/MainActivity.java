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

import gangdrive.gang.demoservice.db.CarData;
import gangdrive.gang.demoservice.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements CarDataListAdapter.HandleCarDataClick {

    private MainActivityViewModel viewModel;
    private TextView noResultTextView;
    private RecyclerView recyclerView;
    private CarDataListAdapter carDataListAdapter;
    private CarData carDataForEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Service Auto");

        noResultTextView = findViewById(R.id.noResult);
        recyclerView = findViewById(R.id.recyclerView);

        ImageView addNew = findViewById(R.id.addNewCarDataImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCarDataDialog(false);
            }
        });

        initViewModel();
        initRecyclerView();
        viewModel.getAllCarDataList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carDataListAdapter = new CarDataListAdapter(this, this);
        recyclerView.setAdapter(carDataListAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCarDataObserverList().observe(this, new Observer<List<CarData>>() {
            @Override
            public void onChanged(List<CarData> carData) {
                if (carData == null) {
                    noResultTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //показать RecyclerView
                    carDataListAdapter.setCarData(carData);
                    recyclerView.setVisibility(View.VISIBLE);
                    noResultTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showCarDataDialog(boolean isForEdit) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.add_cardata_layout, null);
        EditText enterCarDataInput = dialogView.findViewById(R.id.enterCarDataInput);
        TextView createButton = dialogView.findViewById(R.id.createButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);
        if (isForEdit) {
            createButton.setText("Update");
            enterCarDataInput.setText(carDataForEdit.carDataName);
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
                String name = enterCarDataInput.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "Выберите название заголовка", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isForEdit) {
                    carDataForEdit.carDataName = name;
                    viewModel.updateCarData(carDataForEdit);
                } else {
                    //здесь нам нужно вызвать view model
                    viewModel.insertCarData(name);
                }
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    public void itemClick(CarData carData) {
        Intent intent = new Intent(MainActivity.this, ShowItemsListActivity.class);
        intent.putExtra("carData_id", carData.uid);
        intent.putExtra("carData_name", carData.carDataName);

        startActivity(intent);
    }

    @Override
    public void removeItem(CarData carData) {
        viewModel.deleteCarData(carData);
    }

    @Override
    public void editItem(CarData carData) {
        this.carDataForEdit = carData;
        showCarDataDialog(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuReference:
                Intent intent = new Intent(this,MenuReferenceActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }


}