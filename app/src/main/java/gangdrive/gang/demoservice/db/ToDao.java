package gangdrive.gang.demoservice.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDao {
    @Query("SELECT * FROM Tototo")
    List<Tototo> getAllToList();

    @Insert
    void insertTo(Tototo...to);

    @Update
    void updateTo(Tototo...to);

    @Delete
    void deleteTo(Tototo...to);

    @Query("SELECT * FROM ItemsTo where toID =:tototoID")
    List<ItemsTo> getALlItemsList(int tototoID);

    @Insert
    void insertItemsTo (ItemsTo items);

    @Update
    void updateItemsTo (ItemsTo items);

    @Delete
    void deleteItemsTo (ItemsTo items);

}
