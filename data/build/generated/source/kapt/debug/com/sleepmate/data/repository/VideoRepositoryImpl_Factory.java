package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.VideoFavoritesDataStore;
import com.sleepmate.data.datasource.remote.VideoRemoteDataSource;
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
public final class VideoRepositoryImpl_Factory implements Factory<VideoRepositoryImpl> {
  private final Provider<VideoRemoteDataSource> remoteDataSourceProvider;

  private final Provider<VideoFavoritesDataStore> favoritesDataStoreProvider;

  private VideoRepositoryImpl_Factory(Provider<VideoRemoteDataSource> remoteDataSourceProvider,
      Provider<VideoFavoritesDataStore> favoritesDataStoreProvider) {
    this.remoteDataSourceProvider = remoteDataSourceProvider;
    this.favoritesDataStoreProvider = favoritesDataStoreProvider;
  }

  @Override
  public VideoRepositoryImpl get() {
    return newInstance(remoteDataSourceProvider.get(), favoritesDataStoreProvider.get());
  }

  public static VideoRepositoryImpl_Factory create(
      Provider<VideoRemoteDataSource> remoteDataSourceProvider,
      Provider<VideoFavoritesDataStore> favoritesDataStoreProvider) {
    return new VideoRepositoryImpl_Factory(remoteDataSourceProvider, favoritesDataStoreProvider);
  }

  public static VideoRepositoryImpl newInstance(VideoRemoteDataSource remoteDataSource,
      VideoFavoritesDataStore favoritesDataStore) {
    return new VideoRepositoryImpl(remoteDataSource, favoritesDataStore);
  }
}
