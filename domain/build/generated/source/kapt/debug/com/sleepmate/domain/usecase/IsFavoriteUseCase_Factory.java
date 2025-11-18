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
public final class IsFavoriteUseCase_Factory implements Factory<IsFavoriteUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private IsFavoriteUseCase_Factory(Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public IsFavoriteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static IsFavoriteUseCase_Factory create(Provider<VideoRepository> repositoryProvider) {
    return new IsFavoriteUseCase_Factory(repositoryProvider);
  }

  public static IsFavoriteUseCase newInstance(VideoRepository repository) {
    return new IsFavoriteUseCase(repository);
  }
}
