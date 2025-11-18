package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.VideoRepository;
import com.sleepmate.domain.usecase.RemoveFavoritesUseCase;
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
public final class DomainModule_ProvideRemoveFromFavoritesUseCaseFactory implements Factory<RemoveFavoritesUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private DomainModule_ProvideRemoveFromFavoritesUseCaseFactory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RemoveFavoritesUseCase get() {
    return provideRemoveFromFavoritesUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideRemoveFromFavoritesUseCaseFactory create(
      Provider<VideoRepository> repositoryProvider) {
    return new DomainModule_ProvideRemoveFromFavoritesUseCaseFactory(repositoryProvider);
  }

  public static RemoveFavoritesUseCase provideRemoveFromFavoritesUseCase(
      VideoRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideRemoveFromFavoritesUseCase(repository));
  }
}
