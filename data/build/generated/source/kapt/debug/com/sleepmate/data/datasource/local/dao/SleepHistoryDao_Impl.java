package com.sleepmate.data.datasource.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.sleepmate.data.datasource.local.Converters;
import com.sleepmate.data.datasource.local.entity.SleepHistoryEntity;
import java.lang.Class;
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
public final class SleepHistoryDao_Impl implements SleepHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<SleepHistoryEntity> __insertAdapterOfSleepHistoryEntity;

  private final Converters __converters = new Converters();

  public SleepHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfSleepHistoryEntity = new EntityInsertAdapter<SleepHistoryEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `sleep_history` (`id`,`startTime`,`endTime`,`durationMinutes`,`source`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final SleepHistoryEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        final Long _tmp = __converters.dateToTimestamp(entity.getStartTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        final Long _tmp_1 = __converters.dateToTimestamp(entity.getEndTime());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp_1);
        }
        statement.bindLong(4, entity.getDurationMinutes());
        if (entity.getSource() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getSource());
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<SleepHistoryEntity> history,
      final Continuation<? super Unit> arg1) {
    if (history == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfSleepHistoryEntity.insert(_connection, history);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<SleepHistoryEntity>> getAllSleepHistory() {
    final String _sql = "SELECT * FROM sleep_history ORDER BY startTime DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"sleep_history"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
        final int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfSource = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "source");
        final List<SleepHistoryEntity> _result = new ArrayList<SleepHistoryEntity>();
        while (_stmt.step()) {
          final SleepHistoryEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final Instant _tmpStartTime;
          final Long _tmp;
          if (_stmt.isNull(_columnIndexOfStartTime)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getLong(_columnIndexOfStartTime);
          }
          _tmpStartTime = __converters.fromTimestamp(_tmp);
          final Instant _tmpEndTime;
          final Long _tmp_1;
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfEndTime);
          }
          _tmpEndTime = __converters.fromTimestamp(_tmp_1);
          final long _tmpDurationMinutes;
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes);
          final String _tmpSource;
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null;
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource);
          }
          _item = new SleepHistoryEntity(_tmpId,_tmpStartTime,_tmpEndTime,_tmpDurationMinutes,_tmpSource);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getRecentHistory(final Instant since,
      final Continuation<? super List<SleepHistoryEntity>> arg1) {
    final String _sql = "SELECT * FROM sleep_history WHERE startTime >= ? ORDER BY startTime DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        final Long _tmp = __converters.dateToTimestamp(since);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
        final int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfSource = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "source");
        final List<SleepHistoryEntity> _result = new ArrayList<SleepHistoryEntity>();
        while (_stmt.step()) {
          final SleepHistoryEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final Instant _tmpStartTime;
          final Long _tmp_1;
          if (_stmt.isNull(_columnIndexOfStartTime)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfStartTime);
          }
          _tmpStartTime = __converters.fromTimestamp(_tmp_1);
          final Instant _tmpEndTime;
          final Long _tmp_2;
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfEndTime);
          }
          _tmpEndTime = __converters.fromTimestamp(_tmp_2);
          final long _tmpDurationMinutes;
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes);
          final String _tmpSource;
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null;
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource);
          }
          _item = new SleepHistoryEntity(_tmpId,_tmpStartTime,_tmpEndTime,_tmpDurationMinutes,_tmpSource);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> arg0) {
    final String _sql = "DELETE FROM sleep_history";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
