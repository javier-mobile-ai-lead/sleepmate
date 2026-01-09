package com.sleepmate.data.di;

import android.content.Context;
import androidx.health.connect.client.HealthConnectClient;
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
public final class DataContextModule_ProvideHealthConnectClientFactory implements Factory<HealthConnectClient> {
  private final Provider<Context> contextProvider;

  private DataContextModule_ProvideHealthConnectClientFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HealthConnectClient get() {
    return provideHealthConnectClient(contextProvider.get());
  }

  public static DataContextModule_ProvideHealthConnectClientFactory create(
      Provider<Context> contextProvider) {
    return new DataContextModule_ProvideHealthConnectClientFactory(contextProvider);
  }

  public static HealthConnectClient provideHealthConnectClient(Context context) {
    return Preconditions.checkNotNullFromProvides(DataContextModule.INSTANCE.provideHealthConnectClient(context));
  }
}
