package com.sleepmate.data.di;

import android.content.Context;
import com.sleepmate.data.datasource.local.SleepMateDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataContextModule_ProvideDatabaseFactory implements Factory<SleepMateDatabase> {
  private final Provider<Context> contextProvider;

  private DataContextModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SleepMateDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static DataContextModule_ProvideDatabaseFactory create(Provider<Context> contextProvider) {
    return new DataContextModule_ProvideDatabaseFactory(contextProvider);
  }

  public static SleepMateDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DataContextModule.INSTANCE.provideDatabase(context));
  }
}
