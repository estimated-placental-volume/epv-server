package net.epv.epvserver.jdbi;

import net.epv.epvserver.core.Outcome;
import net.epv.epvserver.core.UserProfile;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * DAO interface for UserProfile
 */
public interface UserProfileDao {

    @SqlUpdate("create table if not exists user_profile (" +
            "id         char(36) not null," +
            "dob        double   not null," +
            "height     double   not null," +
            "weight     double   not null," +
            "latitude   double   not null," +
            "longitude  double   not null," +
            "outcome_id char(36)," +
            "outcome_date double," +
            "outcome_gender int,"  +
            "outcome_apgar1 int,"  +
            "outcome_apgar5 int,"  +
            "outcome_cond_0 bool,"  +
            "outcome_cond_1 bool,"  +
            "outcome_cond_2 bool,"  +
            "outcome_cond_3 bool,"  +
            "outcome_cond_4 bool,"  +
            "primary key (id) )")
    void createTable();

    /**
     * Insert a new profile with an associated outcome
     */
    @SqlUpdate("" +
            "insert into user_profile (" +
            "  id, dob, height, weight, latitude, longitude, " +
            "  outcome_id, outcome_date, outcome_gender, outcome_apgar1, outcome_apgar5, " +
            "  outcome_cond_0, outcome_cond_1, outcome_cond_2, outcome_cond_3, outcome_cond_4 " +
            ") " +
            "values (" +
            "  :p.id, :p.dob, :p.height, :p.weight, :p.latitude, :p.longitude," +
            "  :o.id, :o.date, :o.gender, :o.apgar1, :o.apgar5, " +
            "  :c0, :c1, :c2, :c3, :c4" +
            ")")
    int insert(@BindBean("p") UserProfile profile,
               @BindBean("o") Outcome outcome,
               @Bind("c0") Boolean cond0,
               @Bind("c1") Boolean cond1,
               @Bind("c2") Boolean cond2,
               @Bind("c3") Boolean cond3,
               @Bind("c4") Boolean cond4);

    /**
     * Insert a new profile without an associated outcome
     */
    @SqlUpdate("" +
            "insert into user_profile (" +
            "  id, dob, height, weight, latitude, longitude" +
            ") " +
            "values (" +
            "  :p.id, :p.dob, :p.height, :p.weight, :p.latitude, :p.longitude" +
            ")")
    int insert(@BindBean("p") UserProfile profile);

    @SqlUpdate("" +
            "update user_profile set " +
            "  dob = :p.dob, " +
            "  height = :p.height, " +
            "  weight = :p.weight, " +
            "  latitude = :p.latitude, " +
            "  longitude = :p.longitude, " +
            "  outcome_id = :o.id, " +
            "  outcome_date = :o.date, " +
            "  outcome_gender = :o.gender, " +
            "  outcome_apgar1 = :o.apgar1, " +
            "  outcome_apgar5 = :o.apgar5, " +
            "  outcome_cond_0 = :c0, " +
            "  outcome_cond_1 = :c1, " +
            "  outcome_cond_2 = :c2, " +
            "  outcome_cond_3 = :c3, " +
            "  outcome_cond_4 = :c4  " +
            "where id = :p.id")
    int update(@BindBean("p") UserProfile profile,
               @BindBean("o") Outcome outcome,
               @Bind("c0") Boolean cond0,
               @Bind("c1") Boolean cond1,
               @Bind("c2") Boolean cond2,
               @Bind("c3") Boolean cond3,
               @Bind("c4") Boolean cond4);

    @SqlUpdate("" +
            "update user_profile set " +
            "  dob = :p.dob, " +
            "  height = :p.height, " +
            "  weight = :p.weight, " +
            "  latitude = :p.latitude, " +
            "  longitude = :p.longitude, " +
            "  outcome_id = NULL, " +
            "  outcome_date = NULL, " +
            "  outcome_gender = NULL, " +
            "  outcome_apgar1 = NULL, " +
            "  outcome_apgar5 = NULL, " +
            "  outcome_cond_0 = NULL, " +
            "  outcome_cond_1 = NULL, " +
            "  outcome_cond_2 = NULL, " +
            "  outcome_cond_3 = NULL, " +
            "  outcome_cond_4 = NULL  " +
            "where id = :p.id")
    int update(@BindBean("p") UserProfile profile);
}
