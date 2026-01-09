package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.dao.SleepHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SleepHistoryRepositoryImpl_Factory implements Factory<SleepHistoryRepositoryImpl> {
  private final Provider<SleepHistoryDao> sleepHistoryDaoProvider;

  private SleepHistoryRepositoryImpl_Factory(Provider<SleepHistoryDao> sleepHistoryDaoProvider) {
    this.sleepHistoryDaoProvider = sleepHistoryDaoProvider;
  }

  @Override
  public SleepHistoryRepositoryImpl get() {
    return newInstance(sleepHistoryDaoProvider.get());
  }

  public static SleepHistoryRepositoryImpl_Factory create(
      Provider<SleepHistoryDao> sleepHistoryDaoProvider) {
    return new SleepHistoryRepositoryImpl_Factory(sleepHistoryDaoProvider);
  }

  public static SleepHistoryRepositoryImpl newInstance(SleepHistoryDao sleepHistoryDao) {
    return new SleepHistoryRepositoryImpl(sleepHistoryDao);
  }
}
