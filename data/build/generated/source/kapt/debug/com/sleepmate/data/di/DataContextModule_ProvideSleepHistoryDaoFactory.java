package com.sleepmate.data.di;

import com.sleepmate.data.datasource.local.SleepMateDatabase;
import com.sleepmate.data.datasource.local.dao.SleepHistoryDao;
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
public final class DataContextModule_ProvideSleepHistoryDaoFactory implements Factory<SleepHistoryDao> {
  private final Provider<SleepMateDatabase> databaseProvider;

  private DataContextModule_ProvideSleepHistoryDaoFactory(
      Provider<SleepMateDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SleepHistoryDao get() {
    return provideSleepHistoryDao(databaseProvider.get());
  }

  public static DataContextModule_ProvideSleepHistoryDaoFactory create(
      Provider<SleepMateDatabase> databaseProvider) {
    return new DataContextModule_ProvideSleepHistoryDaoFactory(databaseProvider);
  }

  public static SleepHistoryDao provideSleepHistoryDao(SleepMateDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataContextModule.INSTANCE.provideSleepHistoryDao(database));
  }
}
