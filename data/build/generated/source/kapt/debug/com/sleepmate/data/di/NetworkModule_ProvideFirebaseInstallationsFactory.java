package com.sleepmate.data.di;

import com.google.firebase.installations.FirebaseInstallations;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NetworkModule_ProvideFirebaseInstallationsFactory implements Factory<FirebaseInstallations> {
  @Override
  public FirebaseInstallations get() {
    return provideFirebaseInstallations();
  }

  public static NetworkModule_ProvideFirebaseInstallationsFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseInstallations provideFirebaseInstallations() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideFirebaseInstallations());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideFirebaseInstallationsFactory INSTANCE = new NetworkModule_ProvideFirebaseInstallationsFactory();
  }
}
