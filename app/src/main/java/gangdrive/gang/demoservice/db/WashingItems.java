package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WashingItems {
    @PrimaryKey (autoGenerate = true)
    public int wid;

    @ColumnInfo(name = "washingItemName")
    public String washingItemName;

    @ColumnInfo(name = "washingID")
    public int washingID;

    @ColumnInfo(name = "completedWashing")
    public boolean completedWashing;

}
