package gangdrive.gang.demoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Reference1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference1);
        getSupportActionBar().setTitle("Справка");
        //кнопка назад
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
                //кнопка назад
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}