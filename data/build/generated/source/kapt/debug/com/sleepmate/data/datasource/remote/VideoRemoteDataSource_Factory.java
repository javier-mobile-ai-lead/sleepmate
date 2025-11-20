package com.sleepmate.data.datasource.remote;

import com.google.firebase.firestore.FirebaseFirestore;
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
public final class VideoRemoteDataSource_Factory implements Factory<VideoRemoteDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private VideoRemoteDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public VideoRemoteDataSource get() {
    return newInstance(firestoreProvider.get());
  }

  public static VideoRemoteDataSource_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new VideoRemoteDataSource_Factory(firestoreProvider);
  }

  public static VideoRemoteDataSource newInstance(FirebaseFirestore firestore) {
    return new VideoRemoteDataSource(firestore);
  }
}
