package com.sleepmate.data.datasource.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.sleepmate.data.datasource.local.Converters;
import com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class DailyHealthMetricsDao_Impl implements DailyHealthMetricsDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<DailyHealthMetricsEntity> __insertAdapterOfDailyHealthMetricsEntity;

  private final Converters __converters = new Converters();

  public DailyHealthMetricsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfDailyHealthMetricsEntity = new EntityInsertAdapter<DailyHealthMetricsEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_health_metrics` (`date`,`sleepDurationMinutes`,`steps`,`caloriesBurned`,`avgHeartRate`,`restingHeartRate`,`avgOxygenSaturation`,`hrvRmssd`,`weight`,`height`,`sourceApp`,`lastSyncTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final DailyHealthMetricsEntity entity) {
        if (entity.getDate() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getDate());
        }
        statement.bindLong(2, entity.getSleepDurationMinutes());
        statement.bindLong(3, entity.getSteps());
        if (entity.getCaloriesBurned() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getCaloriesBurned());
        }
        if (entity.getAvgHeartRate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getAvgHeartRate());
        }
        if (entity.getRestingHeartRate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getRestingHeartRate());
        }
        if (entity.getAvgOxygenSaturation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getAvgOxygenSaturation());
        }
        if (entity.getHrvRmssd() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getHrvRmssd());
        }
        if (entity.getWeight() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getWeight());
        }
        if (entity.getHeight() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getHeight());
        }
        if (entity.getSourceApp() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getSourceApp());
        }
        final Long _tmp = __converters.dateToTimestamp(entity.getLastSyncTime());
        if (_tmp == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp);
        }
      }
    };
  }

  @Override
  public Object insert(final DailyHealthMetricsEntity metrics,
      final Continuation<? super Unit> $completion) {
    if (metrics == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfDailyHealthMetricsEntity.insert(_connection, metrics);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getMetricsForDate(final String date,
      final Continuation<? super DailyHealthMetricsEntity> $completion) {
    final String _sql = "SELECT * FROM daily_health_metrics WHERE date = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (date == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, date);
        }
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfSleepDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sleepDurationMinutes");
        final int _columnIndexOfSteps = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "steps");
        final int _columnIndexOfCaloriesBurned = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "caloriesBurned");
        final int _columnIndexOfAvgHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgHeartRate");
        final int _columnIndexOfRestingHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "restingHeartRate");
        final int _columnIndexOfAvgOxygenSaturation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgOxygenSaturation");
        final int _columnIndexOfHrvRmssd = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "hrvRmssd");
        final int _columnIndexOfWeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "weight");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfSourceApp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceApp");
        final int _columnIndexOfLastSyncTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastSyncTime");
        final DailyHealthMetricsEntity _result;
        if (_stmt.step()) {
          final String _tmpDate;
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate);
          }
          final long _tmpSleepDurationMinutes;
          _tmpSleepDurationMinutes = _stmt.getLong(_columnIndexOfSleepDurationMinutes);
          final long _tmpSteps;
          _tmpSteps = _stmt.getLong(_columnIndexOfSteps);
          final Long _tmpCaloriesBurned;
          if (_stmt.isNull(_columnIndexOfCaloriesBurned)) {
            _tmpCaloriesBurned = null;
          } else {
            _tmpCaloriesBurned = _stmt.getLong(_columnIndexOfCaloriesBurned);
          }
          final Long _tmpAvgHeartRate;
          if (_stmt.isNull(_columnIndexOfAvgHeartRate)) {
            _tmpAvgHeartRate = null;
          } else {
            _tmpAvgHeartRate = _stmt.getLong(_columnIndexOfAvgHeartRate);
          }
          final Long _tmpRestingHeartRate;
          if (_stmt.isNull(_columnIndexOfRestingHeartRate)) {
            _tmpRestingHeartRate = null;
          } else {
            _tmpRestingHeartRate = _stmt.getLong(_columnIndexOfRestingHeartRate);
          }
          final Double _tmpAvgOxygenSaturation;
          if (_stmt.isNull(_columnIndexOfAvgOxygenSaturation)) {
            _tmpAvgOxygenSaturation = null;
          } else {
            _tmpAvgOxygenSaturation = _stmt.getDouble(_columnIndexOfAvgOxygenSaturation);
          }
          final Double _tmpHrvRmssd;
          if (_stmt.isNull(_columnIndexOfHrvRmssd)) {
            _tmpHrvRmssd = null;
          } else {
            _tmpHrvRmssd = _stmt.getDouble(_columnIndexOfHrvRmssd);
          }
          final Double _tmpWeight;
          if (_stmt.isNull(_columnIndexOfWeight)) {
            _tmpWeight = null;
          } else {
            _tmpWeight = _stmt.getDouble(_columnIndexOfWeight);
          }
          final Double _tmpHeight;
          if (_stmt.isNull(_columnIndexOfHeight)) {
            _tmpHeight = null;
          } else {
            _tmpHeight = _stmt.getDouble(_columnIndexOfHeight);
          }
          final String _tmpSourceApp;
          if (_stmt.isNull(_columnIndexOfSourceApp)) {
            _tmpSourceApp = null;
          } else {
            _tmpSourceApp = _stmt.getText(_columnIndexOfSourceApp);
          }
          final Instant _tmpLastSyncTime;
          final Long _tmp;
          if (_stmt.isNull(_columnIndexOfLastSyncTime)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getLong(_columnIndexOfLastSyncTime);
          }
          _tmpLastSyncTime = __converters.fromTimestamp(_tmp);
          _result = new DailyHealthMetricsEntity(_tmpDate,_tmpSleepDurationMinutes,_tmpSteps,_tmpCaloriesBurned,_tmpAvgHeartRate,_tmpRestingHeartRate,_tmpAvgOxygenSaturation,_tmpHrvRmssd,_tmpWeight,_tmpHeight,_tmpSourceApp,_tmpLastSyncTime);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyHealthMetricsEntity>> getRecentMetrics(final int limit) {
    final String _sql = "SELECT * FROM daily_health_metrics ORDER BY date DESC LIMIT ?";
    return FlowUtil.createFlow(__db, false, new String[] {"daily_health_metrics"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, limit);
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfSleepDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sleepDurationMinutes");
        final int _columnIndexOfSteps = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "steps");
        final int _columnIndexOfCaloriesBurned = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "caloriesBurned");
        final int _columnIndexOfAvgHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgHeartRate");
        final int _columnIndexOfRestingHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "restingHeartRate");
        final int _columnIndexOfAvgOxygenSaturation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgOxygenSaturation");
        final int _columnIndexOfHrvRmssd = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "hrvRmssd");
        final int _columnIndexOfWeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "weight");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfSourceApp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceApp");
        final int _columnIndexOfLastSyncTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastSyncTime");
        final List<DailyHealthMetricsEntity> _result = new ArrayList<DailyHealthMetricsEntity>();
        while (_stmt.step()) {
          final DailyHealthMetricsEntity _item;
          final String _tmpDate;
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate);
          }
          final long _tmpSleepDurationMinutes;
          _tmpSleepDurationMinutes = _stmt.getLong(_columnIndexOfSleepDurationMinutes);
          final long _tmpSteps;
          _tmpSteps = _stmt.getLong(_columnIndexOfSteps);
          final Long _tmpCaloriesBurned;
          if (_stmt.isNull(_columnIndexOfCaloriesBurned)) {
            _tmpCaloriesBurned = null;
          } else {
            _tmpCaloriesBurned = _stmt.getLong(_columnIndexOfCaloriesBurned);
          }
          final Long _tmpAvgHeartRate;
          if (_stmt.isNull(_columnIndexOfAvgHeartRate)) {
            _tmpAvgHeartRate = null;
          } else {
            _tmpAvgHeartRate = _stmt.getLong(_columnIndexOfAvgHeartRate);
          }
          final Long _tmpRestingHeartRate;
          if (_stmt.isNull(_columnIndexOfRestingHeartRate)) {
            _tmpRestingHeartRate = null;
          } else {
            _tmpRestingHeartRate = _stmt.getLong(_columnIndexOfRestingHeartRate);
          }
          final Double _tmpAvgOxygenSaturation;
          if (_stmt.isNull(_columnIndexOfAvgOxygenSaturation)) {
            _tmpAvgOxygenSaturation = null;
          } else {
            _tmpAvgOxygenSaturation = _stmt.getDouble(_columnIndexOfAvgOxygenSaturation);
          }
          final Double _tmpHrvRmssd;
          if (_stmt.isNull(_columnIndexOfHrvRmssd)) {
            _tmpHrvRmssd = null;
          } else {
            _tmpHrvRmssd = _stmt.getDouble(_columnIndexOfHrvRmssd);
          }
          final Double _tmpWeight;
          if (_stmt.isNull(_columnIndexOfWeight)) {
            _tmpWeight = null;
          } else {
            _tmpWeight = _stmt.getDouble(_columnIndexOfWeight);
          }
          final Double _tmpHeight;
          if (_stmt.isNull(_columnIndexOfHeight)) {
            _tmpHeight = null;
          } else {
            _tmpHeight = _stmt.getDouble(_columnIndexOfHeight);
          }
          final String _tmpSourceApp;
          if (_stmt.isNull(_columnIndexOfSourceApp)) {
            _tmpSourceApp = null;
          } else {
            _tmpSourceApp = _stmt.getText(_columnIndexOfSourceApp);
          }
          final Instant _tmpLastSyncTime;
          final Long _tmp;
          if (_stmt.isNull(_columnIndexOfLastSyncTime)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getLong(_columnIndexOfLastSyncTime);
          }
          _tmpLastSyncTime = __converters.fromTimestamp(_tmp);
          _item = new DailyHealthMetricsEntity(_tmpDate,_tmpSleepDurationMinutes,_tmpSteps,_tmpCaloriesBurned,_tmpAvgHeartRate,_tmpRestingHeartRate,_tmpAvgOxygenSaturation,_tmpHrvRmssd,_tmpWeight,_tmpHeight,_tmpSourceApp,_tmpLastSyncTime);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getMetricsInRange(final String startDate, final String endDate,
      final Continuation<? super List<DailyHealthMetricsEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_health_metrics WHERE date BETWEEN ? AND ? ORDER BY date ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (startDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, startDate);
        }
        _argIndex = 2;
        if (endDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, endDate);
        }
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfSleepDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sleepDurationMinutes");
        final int _columnIndexOfSteps = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "steps");
        final int _columnIndexOfCaloriesBurned = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "caloriesBurned");
        final int _columnIndexOfAvgHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgHeartRate");
        final int _columnIndexOfRestingHeartRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "restingHeartRate");
        final int _columnIndexOfAvgOxygenSaturation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "avgOxygenSaturation");
        final int _columnIndexOfHrvRmssd = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "hrvRmssd");
        final int _columnIndexOfWeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "weight");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfSourceApp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceApp");
        final int _columnIndexOfLastSyncTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastSyncTime");
        final List<DailyHealthMetricsEntity> _result = new ArrayList<DailyHealthMetricsEntity>();
        while (_stmt.step()) {
          final DailyHealthMetricsEntity _item;
          final String _tmpDate;
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate);
          }
          final long _tmpSleepDurationMinutes;
          _tmpSleepDurationMinutes = _stmt.getLong(_columnIndexOfSleepDurationMinutes);
          final long _tmpSteps;
          _tmpSteps = _stmt.getLong(_columnIndexOfSteps);
          final Long _tmpCaloriesBurned;
          if (_stmt.isNull(_columnIndexOfCaloriesBurned)) {
            _tmpCaloriesBurned = null;
          } else {
            _tmpCaloriesBurned = _stmt.getLong(_columnIndexOfCaloriesBurned);
          }
          final Long _tmpAvgHeartRate;
          if (_stmt.isNull(_columnIndexOfAvgHeartRate)) {
            _tmpAvgHeartRate = null;
          } else {
            _tmpAvgHeartRate = _stmt.getLong(_columnIndexOfAvgHeartRate);
          }
          final Long _tmpRestingHeartRate;
          if (_stmt.isNull(_columnIndexOfRestingHeartRate)) {
            _tmpRestingHeartRate = null;
          } else {
            _tmpRestingHeartRate = _stmt.getLong(_columnIndexOfRestingHeartRate);
          }
          final Double _tmpAvgOxygenSaturation;
          if (_stmt.isNull(_columnIndexOfAvgOxygenSaturation)) {
            _tmpAvgOxygenSaturation = null;
          } else {
            _tmpAvgOxygenSaturation = _stmt.getDouble(_columnIndexOfAvgOxygenSaturation);
          }
          final Double _tmpHrvRmssd;
          if (_stmt.isNull(_columnIndexOfHrvRmssd)) {
            _tmpHrvRmssd = null;
          } else {
            _tmpHrvRmssd = _stmt.getDouble(_columnIndexOfHrvRmssd);
          }
          final Double _tmpWeight;
          if (_stmt.isNull(_columnIndexOfWeight)) {
            _tmpWeight = null;
          } else {
            _tmpWeight = _stmt.getDouble(_columnIndexOfWeight);
          }
          final Double _tmpHeight;
          if (_stmt.isNull(_columnIndexOfHeight)) {
            _tmpHeight = null;
          } else {
            _tmpHeight = _stmt.getDouble(_columnIndexOfHeight);
          }
          final String _tmpSourceApp;
          if (_stmt.isNull(_columnIndexOfSourceApp)) {
            _tmpSourceApp = null;
          } else {
            _tmpSourceApp = _stmt.getText(_columnIndexOfSourceApp);
          }
          final Instant _tmpLastSyncTime;
          final Long _tmp;
          if (_stmt.isNull(_columnIndexOfLastSyncTime)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getLong(_columnIndexOfLastSyncTime);
          }
          _tmpLastSyncTime = __converters.fromTimestamp(_tmp);
          _item = new DailyHealthMetricsEntity(_tmpDate,_tmpSleepDurationMinutes,_tmpSteps,_tmpCaloriesBurned,_tmpAvgHeartRate,_tmpRestingHeartRate,_tmpAvgOxygenSaturation,_tmpHrvRmssd,_tmpWeight,_tmpHeight,_tmpSourceApp,_tmpLastSyncTime);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
