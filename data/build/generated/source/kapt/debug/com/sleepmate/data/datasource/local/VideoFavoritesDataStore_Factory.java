package com.sleepmate.data.datasource.local;

import android.content.Context;
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
public final class VideoFavoritesDataStore_Factory implements Factory<VideoFavoritesDataStore> {
  private final Provider<Context> contextProvider;

  private VideoFavoritesDataStore_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public VideoFavoritesDataStore get() {
    return newInstance(contextProvider.get());
  }

  public static VideoFavoritesDataStore_Factory create(Provider<Context> contextProvider) {
    return new VideoFavoritesDataStore_Factory(contextProvider);
  }

  public static VideoFavoritesDataStore newInstance(Context context) {
    return new VideoFavoritesDataStore(context);
  }
}
