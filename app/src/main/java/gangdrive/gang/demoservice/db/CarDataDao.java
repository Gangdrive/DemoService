package gangdrive.gang.demoservice.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDataDao {

    @Query("SELECT * FROM CarData")
    List<CarData> getAllCarDataList();

    @Insert
    void insertCarData(CarData...carData);

    @Update
    void updateCarData(CarData...carData);

    @Delete
    void deleteCarData(CarData...carData);
    
    @Query("SELECT * FROM Items where carDataID=:qwertyID")
    List<Items> getALlItemsList(int qwertyID);

    @Insert
    void insertItems (Items items);

    @Update
    void updateItems (Items items);

    @Delete
    void deleteItems(Items items);

}
