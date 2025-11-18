package com.sleepmate.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.FirebaseInstallations;
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
public final class AIUsageRepositoryImpl_Factory implements Factory<AIUsageRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseInstallations> firebaseInstallationsProvider;

  private AIUsageRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseInstallations> firebaseInstallationsProvider) {
    this.firestoreProvider = firestoreProvider;
    this.firebaseInstallationsProvider = firebaseInstallationsProvider;
  }

  @Override
  public AIUsageRepositoryImpl get() {
    return newInstance(firestoreProvider.get(), firebaseInstallationsProvider.get());
  }

  public static AIUsageRepositoryImpl_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseInstallations> firebaseInstallationsProvider) {
    return new AIUsageRepositoryImpl_Factory(firestoreProvider, firebaseInstallationsProvider);
  }

  public static AIUsageRepositoryImpl newInstance(FirebaseFirestore firestore,
      FirebaseInstallations firebaseInstallations) {
    return new AIUsageRepositoryImpl(firestore, firebaseInstallations);
  }
}
