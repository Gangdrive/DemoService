package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.db.AppDatabaseTo;
import gangdrive.gang.demoservice.db.Tototo;

public class ToActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tototo>> listOfTo;
    AppDatabaseTo appDatabaseto;

    public ToActivityViewModel(Application application) {
        super(application);
        listOfTo = new MutableLiveData<>();
        appDatabaseto = AppDatabaseTo.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Tototo>> getToObserverList() {
        return listOfTo;
    }

    public void getAllToList() {
        List<Tototo> toList = appDatabaseto.toDao().getAllToList();
        if (toList.size() > 0) {
            listOfTo.postValue(toList);
        } else {
            listOfTo.postValue(null);
        }
    }

    public void insertTo(String qwertyName) {
        Tototo to = new Tototo();
        to.toName = qwertyName;
        appDatabaseto.toDao().insertTo(to);
        getAllToList();
    }

    public void updateTo(Tototo to) {
        appDatabaseto.toDao().updateTo(to);
        getAllToList();
    }

    public void deleteTo(Tototo to) {
        appDatabaseto.toDao().deleteTo(to);
        getAllToList();
    }
}
