package net.epv.epvserver.jdbi;

import net.epv.epvserver.core.UserProfile;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * DAO interface for UserProfile
 */
public interface UserProfileDao {

    @SqlUpdate("create table user_profile (id char(36) primary key, dob double, height double)")
    void createUserProfileTable();

    @SqlUpdate("insert into user_profile (id, dob, height) values (:id, :dob, :height)")
    int insert(@BindBean UserProfile userProfile);

}
