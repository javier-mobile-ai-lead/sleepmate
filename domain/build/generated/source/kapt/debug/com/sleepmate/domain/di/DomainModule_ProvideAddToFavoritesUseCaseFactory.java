package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.VideoRepository;
import com.sleepmate.domain.usecase.AddVideoFavoritesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DomainModule_ProvideAddToFavoritesUseCaseFactory implements Factory<AddVideoFavoritesUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private DomainModule_ProvideAddToFavoritesUseCaseFactory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddVideoFavoritesUseCase get() {
    return provideAddToFavoritesUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideAddToFavoritesUseCaseFactory create(
      Provider<VideoRepository> repositoryProvider) {
    return new DomainModule_ProvideAddToFavoritesUseCaseFactory(repositoryProvider);
  }

  public static AddVideoFavoritesUseCase provideAddToFavoritesUseCase(VideoRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideAddToFavoritesUseCase(repository));
  }
}
