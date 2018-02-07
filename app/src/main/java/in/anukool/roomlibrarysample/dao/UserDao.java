package in.anukool.roomlibrarysample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

import in.anukool.roomlibrarysample.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Anukool Srivastav on 07/02/18.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("delete from user where uid = :userId")
    int deleteUsersById(String userId);

    @Query("delete from user where first_name like :badName OR last_name like :badName")
    int deleteUsersByName(String badName);

    @Query("delete FROM user")
    void deleteAll();

}
