package com.sleepmate.data.datasource.local;

import android.content.Context;
import com.google.gson.Gson;
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
public final class SleepHabitsDataStore_Factory implements Factory<SleepHabitsDataStore> {
  private final Provider<Context> contextProvider;

  private final Provider<Gson> gsonProvider;

  private SleepHabitsDataStore_Factory(Provider<Context> contextProvider,
      Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public SleepHabitsDataStore get() {
    return newInstance(contextProvider.get(), gsonProvider.get());
  }

  public static SleepHabitsDataStore_Factory create(Provider<Context> contextProvider,
      Provider<Gson> gsonProvider) {
    return new SleepHabitsDataStore_Factory(contextProvider, gsonProvider);
  }

  public static SleepHabitsDataStore newInstance(Context context, Gson gson) {
    return new SleepHabitsDataStore(context, gson);
  }
}
