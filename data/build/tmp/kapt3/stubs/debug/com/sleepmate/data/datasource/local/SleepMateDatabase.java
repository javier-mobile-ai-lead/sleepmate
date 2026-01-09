package com.sleepmate.data.datasource.local;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/sleepmate/data/datasource/local/SleepMateDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "sleepHistoryDao", "Lcom/sleepmate/data/datasource/local/dao/SleepHistoryDao;", "dailyHealthMetricsDao", "Lcom/sleepmate/data/datasource/local/dao/DailyHealthMetricsDao;", "data_debug"})
@androidx.room.Database(entities = {com.sleepmate.data.datasource.local.entity.SleepHistoryEntity.class, com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.sleepmate.data.datasource.local.Converters.class})
public abstract class SleepMateDatabase extends androidx.room.RoomDatabase {
    
    public SleepMateDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.data.datasource.local.dao.SleepHistoryDao sleepHistoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao dailyHealthMetricsDao();
}