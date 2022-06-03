package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.db.AppDatabase;
import gangdrive.gang.demoservice.db.CarData;
import gangdrive.gang.demoservice.db.Items;

public class ShowItemListActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Items>> listOfItems;
    AppDatabase appDatabase;

    public ShowItemListActivityViewModel(Application application) {
        super(application);
        listOfItems = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Items>> getItemsObserverList() {
        return listOfItems;
    }

    public void getAllItemsList(int carDataID) {
        List<Items> itemsList = appDatabase.carDataDao().getALlItemsList(carDataID);
        if (itemsList.size() > 0) {
            listOfItems.postValue(itemsList);
        } else {
            listOfItems.postValue(null);
        }
    }

    public void insertItems(Items item) {
        appDatabase.carDataDao().insertItems(item);
        getAllItemsList(item.carId);
    }

    public void updateItems(Items item) {
        appDatabase.carDataDao().updateItems(item);
        getAllItemsList(item.carId);
    }

    public void deleteItems(Items item) {
        appDatabase.carDataDao().deleteItems(item);
        getAllItemsList(item.carId);
    }
}
