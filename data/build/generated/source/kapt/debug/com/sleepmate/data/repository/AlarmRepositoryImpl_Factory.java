package com.sleepmate.data.repository;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class AlarmRepositoryImpl_Factory implements Factory<AlarmRepositoryImpl> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private AlarmRepositoryImpl_Factory(Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public AlarmRepositoryImpl get() {
    return newInstance(dataStoreProvider.get());
  }

  public static AlarmRepositoryImpl_Factory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new AlarmRepositoryImpl_Factory(dataStoreProvider);
  }

  public static AlarmRepositoryImpl newInstance(DataStore<Preferences> dataStore) {
    return new AlarmRepositoryImpl(dataStore);
  }
}
