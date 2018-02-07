package in.anukool.roomlibrarysample;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import in.anukool.roomlibrarysample.database.AppDatabase;
import in.anukool.roomlibrarysample.entity.User;

/**
 * Created by Anukool Srivastav on 07/02/18.
 */

public class DatabaseHandler {

    public static void populateAsync(@NonNull final AppDatabase db, User user){
        PopulateDbAsync task = new PopulateDbAsync(db, user);
        task.execute();
    }

    /* Aysnc Task to insert data to table */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final User newUser;

        PopulateDbAsync(AppDatabase db, User user) {
            mDb = db;
            newUser = user;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.userDao().insertUser(newUser);
            return null;
        }
    }



}
