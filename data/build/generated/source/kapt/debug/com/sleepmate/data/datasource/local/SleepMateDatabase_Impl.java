package com.sleepmate.data.datasource.local;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao;
import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao_Impl;
import com.sleepmate.data.datasource.local.dao.SleepHistoryDao;
import com.sleepmate.data.datasource.local.dao.SleepHistoryDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class SleepMateDatabase_Impl extends SleepMateDatabase {
  private volatile SleepHistoryDao _sleepHistoryDao;

  private volatile DailyHealthMetricsDao _dailyHealthMetricsDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(2, "7fe2d1fc45a3959abba03ee026eff043", "0a309ac081dc4ff41a144256144ea477") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `sleep_history` (`id` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `durationMinutes` INTEGER NOT NULL, `source` TEXT, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `daily_health_metrics` (`date` TEXT NOT NULL, `sleepDurationMinutes` INTEGER NOT NULL, `steps` INTEGER NOT NULL, `caloriesBurned` INTEGER, `avgHeartRate` INTEGER, `restingHeartRate` INTEGER, `avgOxygenSaturation` REAL, `hrvRmssd` REAL, `weight` REAL, `height` REAL, `sourceApp` TEXT, `lastSyncTime` INTEGER NOT NULL, PRIMARY KEY(`date`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7fe2d1fc45a3959abba03ee026eff043')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `sleep_history`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `daily_health_metrics`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsSleepHistory = new HashMap<String, TableInfo.Column>(5);
        _columnsSleepHistory.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepHistory.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepHistory.put("endTime", new TableInfo.Column("endTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepHistory.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepHistory.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysSleepHistory = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesSleepHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSleepHistory = new TableInfo("sleep_history", _columnsSleepHistory, _foreignKeysSleepHistory, _indicesSleepHistory);
        final TableInfo _existingSleepHistory = TableInfo.read(connection, "sleep_history");
        if (!_infoSleepHistory.equals(_existingSleepHistory)) {
          return new RoomOpenDelegate.ValidationResult(false, "sleep_history(com.sleepmate.data.datasource.local.entity.SleepHistoryEntity).\n"
                  + " Expected:\n" + _infoSleepHistory + "\n"
                  + " Found:\n" + _existingSleepHistory);
        }
        final Map<String, TableInfo.Column> _columnsDailyHealthMetrics = new HashMap<String, TableInfo.Column>(12);
        _columnsDailyHealthMetrics.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("sleepDurationMinutes", new TableInfo.Column("sleepDurationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("caloriesBurned", new TableInfo.Column("caloriesBurned", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("avgHeartRate", new TableInfo.Column("avgHeartRate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("restingHeartRate", new TableInfo.Column("restingHeartRate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("avgOxygenSaturation", new TableInfo.Column("avgOxygenSaturation", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("hrvRmssd", new TableInfo.Column("hrvRmssd", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("weight", new TableInfo.Column("weight", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("height", new TableInfo.Column("height", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("sourceApp", new TableInfo.Column("sourceApp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyHealthMetrics.put("lastSyncTime", new TableInfo.Column("lastSyncTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysDailyHealthMetrics = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesDailyHealthMetrics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDailyHealthMetrics = new TableInfo("daily_health_metrics", _columnsDailyHealthMetrics, _foreignKeysDailyHealthMetrics, _indicesDailyHealthMetrics);
        final TableInfo _existingDailyHealthMetrics = TableInfo.read(connection, "daily_health_metrics");
        if (!_infoDailyHealthMetrics.equals(_existingDailyHealthMetrics)) {
          return new RoomOpenDelegate.ValidationResult(false, "daily_health_metrics(com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity).\n"
                  + " Expected:\n" + _infoDailyHealthMetrics + "\n"
                  + " Found:\n" + _existingDailyHealthMetrics);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "sleep_history", "daily_health_metrics");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "sleep_history", "daily_health_metrics");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SleepHistoryDao.class, SleepHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DailyHealthMetricsDao.class, DailyHealthMetricsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SleepHistoryDao sleepHistoryDao() {
    if (_sleepHistoryDao != null) {
      return _sleepHistoryDao;
    } else {
      synchronized(this) {
        if(_sleepHistoryDao == null) {
          _sleepHistoryDao = new SleepHistoryDao_Impl(this);
        }
        return _sleepHistoryDao;
      }
    }
  }

  @Override
  public DailyHealthMetricsDao dailyHealthMetricsDao() {
    if (_dailyHealthMetricsDao != null) {
      return _dailyHealthMetricsDao;
    } else {
      synchronized(this) {
        if(_dailyHealthMetricsDao == null) {
          _dailyHealthMetricsDao = new DailyHealthMetricsDao_Impl(this);
        }
        return _dailyHealthMetricsDao;
      }
    }
  }
}
