package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Accessories {
    @PrimaryKey(autoGenerate = true)
    public int aid;

    @ColumnInfo(name = "AccessoriesName")
    public String accessoriesName;

    
}
