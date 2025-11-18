package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.DarkModePreferences;
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
public final class DarkModeRepositoryImpl_Factory implements Factory<DarkModeRepositoryImpl> {
  private final Provider<DarkModePreferences> darkModePreferencesProvider;

  private DarkModeRepositoryImpl_Factory(
      Provider<DarkModePreferences> darkModePreferencesProvider) {
    this.darkModePreferencesProvider = darkModePreferencesProvider;
  }

  @Override
  public DarkModeRepositoryImpl get() {
    return newInstance(darkModePreferencesProvider.get());
  }

  public static DarkModeRepositoryImpl_Factory create(
      Provider<DarkModePreferences> darkModePreferencesProvider) {
    return new DarkModeRepositoryImpl_Factory(darkModePreferencesProvider);
  }

  public static DarkModeRepositoryImpl newInstance(DarkModePreferences darkModePreferences) {
    return new DarkModeRepositoryImpl(darkModePreferences);
  }
}
