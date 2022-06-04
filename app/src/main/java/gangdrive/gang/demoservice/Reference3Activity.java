package gangdrive.gang.demoservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Reference3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference3);
        getSupportActionBar().setTitle("Справка");
        //кнопка назад
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
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