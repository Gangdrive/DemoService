package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.db.AppDatabase;
import gangdrive.gang.demoservice.db.Tototo;

public class ToActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tototo>> listOfTo;
    AppDatabase appDatabase;

    public ToActivityViewModel(Application application) {
        super(application);
        listOfTo = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Tototo>> getToObserverList() {
        return listOfTo;
    }

    public void getAllToList() {
        List<Tototo> toList = appDatabase.toDao().getAllToList();
        if (toList.size() > 0) {
            listOfTo.postValue(toList);
        } else {
            listOfTo.postValue(null);
        }
    }

    public void insertTo(String qwertyName) {
        Tototo to = new Tototo();
        to.toName = qwertyName;
        appDatabase.toDao().insertTo(to);
        getAllToList();
    }

    public void updateTo(Tototo to) {
        appDatabase.toDao().updateTo(to);
        getAllToList();
    }

    public void deleteTo(Tototo to) {
        appDatabase.toDao().deleteTo(to);
        getAllToList();
    }
}
