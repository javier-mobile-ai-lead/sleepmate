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
public final class AddVideoFavoritesUseCase_Factory implements Factory<AddVideoFavoritesUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private AddVideoFavoritesUseCase_Factory(Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddVideoFavoritesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddVideoFavoritesUseCase_Factory create(
      Provider<VideoRepository> repositoryProvider) {
    return new AddVideoFavoritesUseCase_Factory(repositoryProvider);
  }

  public static AddVideoFavoritesUseCase newInstance(VideoRepository repository) {
    return new AddVideoFavoritesUseCase(repository);
  }
}
