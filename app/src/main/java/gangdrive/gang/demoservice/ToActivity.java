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

import gangdrive.gang.demoservice.db.Tototo;
import gangdrive.gang.demoservice.viewmodel.ToActivityViewModel;

public class ToActivity extends AppCompatActivity implements ToListAdapter.HandleToClick {

    private ToActivityViewModel viewModel;
    private TextView noResultTextView;
    private RecyclerView recyclerView;
    private ToListAdapter toListAdapter;
    private Tototo toForEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
        getSupportActionBar().setTitle("Пройденные ТО");
        noResultTextView = findViewById(R.id.noResult);
        recyclerView = findViewById(R.id.recyclerView);

        ImageView addNew = findViewById(R.id.addNewToImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToDialog(false);
            }
        });

        initViewModel();
        initRecyclerView();
        viewModel.getAllToList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toListAdapter = new ToListAdapter(this, this);
        recyclerView.setAdapter(toListAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ToActivityViewModel.class);
        viewModel.getToObserverList().observe(this, new Observer<List<Tototo>>() {
            @Override
            public void onChanged(List<Tototo> to) {
                if (to == null) {
                    noResultTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //показать RecyclerView
                    toListAdapter.setTo(to);
                    recyclerView.setVisibility(View.VISIBLE);
                    noResultTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showToDialog(boolean isForEdit) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.add_cardata_layout, null);
        EditText enterToInput = dialogView.findViewById(R.id.enterCarDataInput);
        TextView createButton = dialogView.findViewById(R.id.createButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);
        if (isForEdit) {
            createButton.setText("Update");
            enterToInput.setText(toForEdit.toName);
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
                String name = enterToInput.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(ToActivity.this, "Выберите название заголовка", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isForEdit) {
                    toForEdit.toName = name;
                    viewModel.updateTo(toForEdit);
                } else {
                    //здесь нам нужно вызвать view model
                    viewModel.insertTo(name);
                }
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    public void itemClick(Tototo to) {
        Intent intent = new Intent(ToActivity.this, ShowItemsToListActivity.class);
        intent.putExtra("to_id", to.tid);
        intent.putExtra("to_name", to.toName);

        startActivity(intent);
    }

    @Override
    public void removeItem(Tototo to) {
        viewModel.deleteTo(to);
    }

    @Override
    public void editItem(Tototo to) {
        this.toForEdit = to;
        showToDialog(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.to_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.goMainActivity:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menuReference:
                intent = new Intent(this, MenuReferenceActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

}