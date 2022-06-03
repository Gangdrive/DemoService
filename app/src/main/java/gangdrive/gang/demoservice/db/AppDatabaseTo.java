package gangdrive.gang.demoservice.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tototo.class,Items.class} , version = 1)
public abstract class AppDatabaseTo extends RoomDatabase {

  public abstract ToDao toDao();
    public static AppDatabaseTo INSTANCE;

    public static AppDatabaseTo getDbInstance(Context context){

        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseTo.class,"AppDBTO")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
