package gangdrive.gang.demoservice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gangdrive.gang.demoservice.db.AppDatabase;
import gangdrive.gang.demoservice.db.ItemsTo;

public class ShowItemToListActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<ItemsTo>> listOfItems;
    AppDatabase appDatabase;

    public ShowItemToListActivityViewModel(Application application) {
        super(application);
        listOfItems = new MutableLiveData<>();
        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<ItemsTo>> getItemsObserverList() {
        return listOfItems;
    }

    public void getAllItemsList(int toID) {
        List<ItemsTo> itemsToList = appDatabase.toDao().getALlItemsList(toID);
        if (itemsToList.size() > 0) {
            listOfItems.postValue(itemsToList);
        } else {
            listOfItems.postValue(null);
        }
    }

    public void insertItemsTo(ItemsTo item) {
        appDatabase.toDao().insertItemsTo(item);
        getAllItemsList(item.toId);
    }

    public void updateItemsTo(ItemsTo item) {
        appDatabase.toDao().updateItemsTo(item);
        getAllItemsList(item.toId);
    }

    public void deleteItemsTo(ItemsTo item) {
        appDatabase.toDao().deleteItemsTo(item);
        getAllItemsList(item.toId);
    }
}
