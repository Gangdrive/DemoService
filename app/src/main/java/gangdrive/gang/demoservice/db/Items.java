package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Items {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "carDataID")
    public int carId;

    @ColumnInfo(name = "completed")
    public boolean completed;

}
