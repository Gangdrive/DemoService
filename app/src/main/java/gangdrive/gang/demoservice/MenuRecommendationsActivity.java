package gangdrive.gang.demoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuRecommendationsActivity extends AppCompatActivity {

    private int carData_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recommendations);

        getSupportActionBar().setTitle("Рекомендации");
        //кнопка назад
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recommendations_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            //кнопка назад
            case android.R.id.home:
                finish();
                break;
            case R.id.goMainActivity:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menuTo:
                intent = new Intent(this, ToActivity.class);
                startActivity(intent);
                break;
            case R.id.menuAutoАccessories:
                intent = new Intent(this, AccessoriesActivity.class);
                startActivity(intent);
                break;
/*case R.id.menuWashing:
                intent = new Intent(this,WashingActivity.class);
                startActivity(intent);
                break;*/

        }
        return true;
    }
}