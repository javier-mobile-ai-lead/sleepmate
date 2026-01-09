package com.sleepmate.data.health;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class HealthDataRepositoryImpl_Factory implements Factory<HealthDataRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private HealthDataRepositoryImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HealthDataRepositoryImpl get() {
    return newInstance(contextProvider.get());
  }

  public static HealthDataRepositoryImpl_Factory create(Provider<Context> contextProvider) {
    return new HealthDataRepositoryImpl_Factory(contextProvider);
  }

  public static HealthDataRepositoryImpl newInstance(Context context) {
    return new HealthDataRepositoryImpl(context);
  }
}
