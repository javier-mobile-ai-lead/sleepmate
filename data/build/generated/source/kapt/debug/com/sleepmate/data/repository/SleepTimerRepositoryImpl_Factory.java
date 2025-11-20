package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.SleepModeDataStore;
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
public final class SleepTimerRepositoryImpl_Factory implements Factory<SleepTimerRepositoryImpl> {
  private final Provider<SleepModeDataStore> sleepModeDataStoreProvider;

  private SleepTimerRepositoryImpl_Factory(
      Provider<SleepModeDataStore> sleepModeDataStoreProvider) {
    this.sleepModeDataStoreProvider = sleepModeDataStoreProvider;
  }

  @Override
  public SleepTimerRepositoryImpl get() {
    return newInstance(sleepModeDataStoreProvider.get());
  }

  public static SleepTimerRepositoryImpl_Factory create(
      Provider<SleepModeDataStore> sleepModeDataStoreProvider) {
    return new SleepTimerRepositoryImpl_Factory(sleepModeDataStoreProvider);
  }

  public static SleepTimerRepositoryImpl newInstance(SleepModeDataStore sleepModeDataStore) {
    return new SleepTimerRepositoryImpl(sleepModeDataStore);
  }
}
