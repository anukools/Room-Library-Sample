package in.anukool.roomlibrarysample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import in.anukool.roomlibrarysample.database.AppDatabase;
import in.anukool.roomlibrarysample.databinding.ActivityMainBinding;
import in.anukool.roomlibrarysample.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Clearing old records from database
        AppDatabase.getAppDataBase(this).userDao().deleteAll();

        // Adding User to database
        activityMainBinding.addUser
                .setOnClickListener(view -> {
                    User user = new User();
                    user.setFirstName(activityMainBinding.textInputEditText1.getText().toString());
                    user.setLastName(activityMainBinding.textInputEditText2.getText().toString());
                    user.setAge(Integer.parseInt(activityMainBinding.textInputEditText3.getText().toString()));
                    DatabaseHandler.populateAsync(AppDatabase.getAppDataBase(MainActivity.this), user);

                    activityMainBinding.textInputEditText1.getText().clear();
                    activityMainBinding.textInputEditText2.getText().clear();
                    activityMainBinding.textInputEditText3.getText().clear();

                    Toast.makeText(MainActivity.this, "USer Added",Toast.LENGTH_SHORT).show();

                });


        // show all user from database
        activityMainBinding.showUsers
                .setOnClickListener(view -> {
                    List<User> userList = AppDatabase.getAppDataBase(MainActivity.this).userDao().getAll();
                    for (int i = 0; i < userList.size(); i++) {
                        View tableRow = LayoutInflater.from(MainActivity.this).inflate(R.layout.table_item, null, false);
                        TextView uID = tableRow.findViewById(R.id.uid);
                        TextView firstName = tableRow.findViewById(R.id.firstName);
                        TextView lastName = tableRow.findViewById(R.id.lastName);
                        TextView age = tableRow.findViewById(R.id.age);

                        uID.setText(String.valueOf(userList.get(i).getUid()));
                        firstName.setText(userList.get(i).getFirstName());
                        lastName.setText(userList.get(i).getLastName());
                        age.setText(String.valueOf(userList.get(i).getAge()));
                        activityMainBinding.tableLayout.addView(tableRow);
                    }
                });



    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
