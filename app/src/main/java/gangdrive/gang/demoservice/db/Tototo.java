package gangdrive.gang.demoservice.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tototo {
    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "ToName")
    public String toName;

}
