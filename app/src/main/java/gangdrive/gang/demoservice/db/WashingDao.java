package gangdrive.gang.demoservice.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WashingDao {

    @Query("SELECT * FROM Washing")
    List<Washing> getAllWashingList();

    @Insert
    void insertWashing(Washing...washings);

    @Update
    void updateWashing(Washing...washings);

    @Delete
    void deleteWashing(Washing...washings);
    
    @Query("SELECT * FROM WashingItems where washingID=:wasID")
    List<WashingItems> getALlWashingItemsList(int wasID);

    @Insert
    void insertWashingItems (WashingItems washingItemsItems);

    @Update
    void updateWashingItems (WashingItems washingItemsItems);

    @Delete
    void deleteWashingItems(WashingItems washingItemsItems);

}
