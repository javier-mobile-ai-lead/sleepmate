package com.sleepmate.data.datasource.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SleepModeDataStore_Factory implements Factory<SleepModeDataStore> {
  private final Provider<Context> contextProvider;

  private SleepModeDataStore_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SleepModeDataStore get() {
    return newInstance(contextProvider.get());
  }

  public static SleepModeDataStore_Factory create(Provider<Context> contextProvider) {
    return new SleepModeDataStore_Factory(contextProvider);
  }

  public static SleepModeDataStore newInstance(Context context) {
    return new SleepModeDataStore(context);
  }
}
