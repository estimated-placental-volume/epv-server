package net.epv.epvserver.jdbi;

import net.epv.epvserver.core.UserProfile;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * DAO interface for UserProfile
 */
public interface UserProfileDao {

    @SqlUpdate("create table user_profile (" +
            "id     char(36) not null," +
            "dob    double   not null," +
            "height double   not null," +
            "primary key (id) )")
    void createTable();

    @SqlUpdate("insert into user_profile (id, dob, height) values (:id, :dob, :height)")
    int insert(@BindBean UserProfile userProfile);

    @SqlUpdate("update user_profile set dob = :dob, height = :height where id = :id")
    int update(@BindBean UserProfile s);
}
