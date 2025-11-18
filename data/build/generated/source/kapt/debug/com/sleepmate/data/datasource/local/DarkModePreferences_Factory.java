package com.sleepmate.data.datasource.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DarkModePreferences_Factory implements Factory<DarkModePreferences> {
  private final Provider<Context> contextProvider;

  private DarkModePreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DarkModePreferences get() {
    return newInstance(contextProvider.get());
  }

  public static DarkModePreferences_Factory create(Provider<Context> contextProvider) {
    return new DarkModePreferences_Factory(contextProvider);
  }

  public static DarkModePreferences newInstance(Context context) {
    return new DarkModePreferences(context);
  }
}
