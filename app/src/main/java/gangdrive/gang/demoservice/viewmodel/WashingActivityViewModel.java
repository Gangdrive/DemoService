package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


import gangdrive.gang.demoservice.WashingActivity;
import gangdrive.gang.demoservice.db.AppDatabase;
import gangdrive.gang.demoservice.db.Washing;


public class WashingActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Washing>> listOfWashing;
    AppDatabase appDatabase;

    public WashingActivityViewModel(Application application) {
        super(application);
        listOfWashing = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Washing>> getWashingObserverList()  {
        return listOfWashing;
    }

    public void getAllWashingList() {
        List<Washing> washingList = appDatabase.washingDao().getAllWashingList();
        if (washingList.size() > 0) {
            listOfWashing.postValue(washingList);
        } else {
            listOfWashing.postValue(null);
        }
    }

    public void insertWashing(String washingPrice) {
        Washing washing = new Washing();
        washing.washingPrice = washingPrice;
        appDatabase.washingDao().insertWashing(washing);
        getAllWashingList();
    }

    public void updateWashing(Washing washing ) {
        appDatabase.washingDao().updateWashing(washing);
        getAllWashingList();
    }

    public void deleteWashing(Washing washing) {
        appDatabase.washingDao().deleteWashing(washing);
        getAllWashingList();
    }
}
