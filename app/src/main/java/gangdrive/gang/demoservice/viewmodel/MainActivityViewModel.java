package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.MainActivity;
import gangdrive.gang.demoservice.db.AppDatabase;
import gangdrive.gang.demoservice.db.CarData;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<CarData>> listOfCarData;
    AppDatabase appDatabase;

    public MainActivityViewModel(Application application) {
        super(application);
        listOfCarData = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<CarData>> getCarDataObserverList() {
        return listOfCarData;
    }

    public void getAllCarDataList() {
        List<CarData> carDataList = appDatabase.carDataDao().getAllCarDataList();
        if (carDataList.size() > 0) {
            listOfCarData.postValue(carDataList);
        } else {
            listOfCarData.postValue(null);
        }
    }

    public void insertCarData(String qwertyName) {
        CarData carData = new CarData();
        carData.carDataName = qwertyName;
        appDatabase.carDataDao().insertCarData(carData);
        getAllCarDataList();
    }

    public void updateCarData(CarData carData) {
        appDatabase.carDataDao().updateCarData(carData);
        getAllCarDataList();
    }

    public void deleteCarData(CarData carData) {
        appDatabase.carDataDao().deleteCarData(carData);
        getAllCarDataList();
    }
}
