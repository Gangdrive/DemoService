package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.AccessoriesActivity;
import gangdrive.gang.demoservice.db.Accessories;
import gangdrive.gang.demoservice.db.AppDatabase;


public class AccessoriesActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Accessories>> listOfAccessories;
    AppDatabase appDatabase;

    public AccessoriesActivityViewModel(Application application) {
        super(application);
        listOfAccessories = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Accessories>> getAccessoriesObserverList() {
        return listOfAccessories;
    }

    public void getAllAccessoriesList() {
        List<Accessories> accessoriesList = appDatabase.accessoriesDao().getAllAccessoriesList();
        if (accessoriesList.size() > 0) {
            listOfAccessories.postValue(accessoriesList);
        } else {
            listOfAccessories.postValue(null);
        }
    }

    public void insertAccessories(String accessoriesName) {
        Accessories accessories = new Accessories();
        accessories.accessoriesName = accessoriesName;
        appDatabase.accessoriesDao().insertAccessories(accessories);
        getAllAccessoriesList();
    }

    public void updateAccessories(Accessories accessories) {
        appDatabase.accessoriesDao().updateAccessories(accessories);
        getAllAccessoriesList();
    }

    public void deleteCarData(Accessories accessories) {
        appDatabase.accessoriesDao().deleteAccessories(accessories);
        getAllAccessoriesList();
    }
}
