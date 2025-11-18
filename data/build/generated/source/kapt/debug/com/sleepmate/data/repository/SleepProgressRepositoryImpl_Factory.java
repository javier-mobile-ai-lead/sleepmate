package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.SleepProgressDataStore;
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
public final class SleepProgressRepositoryImpl_Factory implements Factory<SleepProgressRepositoryImpl> {
  private final Provider<SleepProgressDataStore> sleepProgressDataStoreProvider;

  private SleepProgressRepositoryImpl_Factory(
      Provider<SleepProgressDataStore> sleepProgressDataStoreProvider) {
    this.sleepProgressDataStoreProvider = sleepProgressDataStoreProvider;
  }

  @Override
  public SleepProgressRepositoryImpl get() {
    return newInstance(sleepProgressDataStoreProvider.get());
  }

  public static SleepProgressRepositoryImpl_Factory create(
      Provider<SleepProgressDataStore> sleepProgressDataStoreProvider) {
    return new SleepProgressRepositoryImpl_Factory(sleepProgressDataStoreProvider);
  }

  public static SleepProgressRepositoryImpl newInstance(
      SleepProgressDataStore sleepProgressDataStore) {
    return new SleepProgressRepositoryImpl(sleepProgressDataStore);
  }
}
