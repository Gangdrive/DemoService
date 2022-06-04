package gangdrive.gang.demoservice.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CarData.class,Items.class, Accessories.class,AccessoriesItems.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public abstract CarDataDao carDataDao();
  public abstract AccessoriesDao accessoriesDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){

        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
