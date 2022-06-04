package gangdrive.gang.demoservice.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccessoriesDao {

    @Query("SELECT * FROM Accessories")
    List<Accessories> getAllAccessoriesList();

    @Insert
    void insertAccessories(Accessories...accessories);

    @Update
    void updateAccessories(Accessories...accessories);

    @Delete
    void deleteAccessories(Accessories...accessories);
    
    @Query("SELECT * FROM AccessoriesItems where accessoriesID=:accID")
    List<AccessoriesItems> getALlAccessoriesItemsList(int accID);

    @Insert
    void insertAccessoriesItems (AccessoriesItems accessoriesItems);

    @Update
    void updateAccessoriesItems (AccessoriesItems accessoriesItems);

    @Delete
    void deleteAccessoriesItems(AccessoriesItems accessoriesItems);

}
