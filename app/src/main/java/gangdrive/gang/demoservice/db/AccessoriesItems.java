package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccessoriesItems {
    @PrimaryKey (autoGenerate = true)
    public int aid;

    @ColumnInfo(name = "accessoriesItemName")
    public String accessoriesItemName;

    @ColumnInfo(name = "accessoriesID")
    public int accessoriesID;

    @ColumnInfo(name = "completedAccessories")
    public boolean completedAccessories;

}
