package net.epv.epvserver.jdbi;

import net.epv.epvserver.core.DataPoint;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * JDBI DAO for data_point table
 */
public interface DataPointDao {

    @SqlUpdate("create table if not exists data_point (" +
            "id         char(36) not null," +
            "profile_id char(36) not null," +
            "timestamp  double   not null," +
            "width      double   not null," +
            "height     double   not null," +
            "thickness  double   not null," +
            "epv        double   not null," +
            "percentile double   not null," +
            "weeks      int      not null," +
            "days       int      not null," +
            "primary key (id)," +
            "constraint fk_profile_data foreign key (profile_id) references user_profile(id) )")
    void createTable();


    @SqlUpdate("insert into data_point " +
            "(id, profile_id, timestamp, width, height, thickness, epv, percentile, weeks, days) " +
            "values (:id, :profileId, :timestamp, :width, :height, :thickness, :epv, :percentile, :weeks, :days)")
    int insert(@BindBean DataPoint dataPoint);
}
