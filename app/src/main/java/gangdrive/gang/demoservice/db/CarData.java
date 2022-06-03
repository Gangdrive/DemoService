package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CarData {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "CarDataName")
    public String carDataName;

    
}
