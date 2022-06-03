package gangdrive.gang.demoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuReferenceActivity extends AppCompatActivity {

    private int carData_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reference);

        getSupportActionBar().setTitle("Рекомендации");
        //кнопка назад
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reference_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.goMainActivity:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
                //кнопка назад
            case android.R.id.home:
                finish();
        }
        return true;
    }
}