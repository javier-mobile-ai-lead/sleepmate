package com.sleepmate.data.di;

import com.sleepmate.data.datasource.local.SleepMateDatabase;
import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DataContextModule_ProvideDailyHealthMetricsDaoFactory implements Factory<DailyHealthMetricsDao> {
  private final Provider<SleepMateDatabase> databaseProvider;

  private DataContextModule_ProvideDailyHealthMetricsDaoFactory(
      Provider<SleepMateDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DailyHealthMetricsDao get() {
    return provideDailyHealthMetricsDao(databaseProvider.get());
  }

  public static DataContextModule_ProvideDailyHealthMetricsDaoFactory create(
      Provider<SleepMateDatabase> databaseProvider) {
    return new DataContextModule_ProvideDailyHealthMetricsDaoFactory(databaseProvider);
  }

  public static DailyHealthMetricsDao provideDailyHealthMetricsDao(SleepMateDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataContextModule.INSTANCE.provideDailyHealthMetricsDao(database));
  }
}
