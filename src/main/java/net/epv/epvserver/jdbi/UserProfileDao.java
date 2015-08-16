package net.epv.epvserver.jdbi;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * DAO interface for UserProfile
 */
public interface UserProfileDao {

    @SqlUpdate("create table user_profile (id binary(16) primary key)")
    void createUserProfileTable();
}
