package in.anukool.roomlibrarysample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import in.anukool.roomlibrarysample.dao.UserDao;
import in.anukool.roomlibrarysample.entity.User;

/**
 * Created by Anukool Srivastav on 07/02/18.
 */
@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getAppDataBase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       AppDatabase.class, "user_database")
            .allowMainThreadQueries()
            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
