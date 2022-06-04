package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Washing {
    @PrimaryKey(autoGenerate = true)
    public int wid;

    @ColumnInfo(name = "WashingPrice")
    public String  washingPrice;

}
