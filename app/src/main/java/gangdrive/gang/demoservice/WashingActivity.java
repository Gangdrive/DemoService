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


import gangdrive.gang.demoservice.db.Washing;
import gangdrive.gang.demoservice.viewmodel.WashingActivityViewModel;


public class WashingActivity extends AppCompatActivity implements WashingListAdapter.HandleWashingClick {

    private WashingActivityViewModel viewModel;
    private TextView noResultTextView;
    private RecyclerView recyclerView;
    private WashingListAdapter washingListAdapter;
    private Washing washingForEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing);

        getSupportActionBar().setTitle("Мойка");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noResultTextView = findViewById(R.id.noResult);
        recyclerView = findViewById(R.id.recyclerView);

        ImageView addNew = findViewById(R.id.addNewWashingImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWashingDialog(false);
            }
        });

        initViewModel();
        initRecyclerView();
        viewModel.getAllWashingList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        washingListAdapter = new WashingListAdapter(this, this);
        recyclerView.setAdapter(washingListAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(WashingActivityViewModel.class);
        viewModel.getWashingObserverList().observe(this, new Observer<List<Washing>>() {
            @Override
            public void onChanged(List<Washing> washings) {
                if (washings == null) {
                    noResultTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //показать RecyclerView
                    washingListAdapter.setWashing(washings);
                    recyclerView.setVisibility(View.VISIBLE);
                    noResultTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showWashingDialog(boolean isForEdit){
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.add_washing_layout, null);
        EditText enterWashingInput = dialogView.findViewById(R.id.enterWashingInput);
        TextView createButton = dialogView.findViewById(R.id.createButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);

        if (isForEdit) {
            createButton.setText("Обновить");
            enterWashingInput.setText(washingForEdit.washingPrice);
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
                String name = enterWashingInput.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(WashingActivity.this, "Добавьте стоимость", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isForEdit) {
                    washingForEdit.washingPrice = name +"руб";
                    viewModel.updateWashing(washingForEdit);
                } else {
                    //здесь нам нужно вызвать view model
                    viewModel.insertWashing(name+"руб");
                }
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    public void removeItem(Washing washing) {
        viewModel.deleteWashing(washing);
    }

    @Override
    public void editItem(Washing washing) {
        this.washingForEdit = washing;
        showWashingDialog(true);
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
            case R.id.menuAutoАccessories:
                intent = new Intent(this,AccessoriesActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}