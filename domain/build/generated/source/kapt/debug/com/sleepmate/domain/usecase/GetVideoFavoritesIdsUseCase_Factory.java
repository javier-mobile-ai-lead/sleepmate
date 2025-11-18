package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.VideoRepository;
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
public final class GetVideoFavoritesIdsUseCase_Factory implements Factory<GetVideoFavoritesIdsUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private GetVideoFavoritesIdsUseCase_Factory(Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoFavoritesIdsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetVideoFavoritesIdsUseCase_Factory create(
      Provider<VideoRepository> repositoryProvider) {
    return new GetVideoFavoritesIdsUseCase_Factory(repositoryProvider);
  }

  public static GetVideoFavoritesIdsUseCase newInstance(VideoRepository repository) {
    return new GetVideoFavoritesIdsUseCase(repository);
  }
}
