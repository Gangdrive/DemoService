package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemsTo {
    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "itemNameTo")
    public String itemNameTo;

    @ColumnInfo(name = "toID")
    public int toId;

    @ColumnInfo(name = "completedTo")
    public boolean completedTo;
}
