package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.VideoRepository;
import com.sleepmate.domain.usecase.GetVideoFavoritesIdsUseCase;
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
public final class DomainModule_ProvideGetFavoritesIdsUseCaseFactory implements Factory<GetVideoFavoritesIdsUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private DomainModule_ProvideGetFavoritesIdsUseCaseFactory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoFavoritesIdsUseCase get() {
    return provideGetFavoritesIdsUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideGetFavoritesIdsUseCaseFactory create(
      Provider<VideoRepository> repositoryProvider) {
    return new DomainModule_ProvideGetFavoritesIdsUseCaseFactory(repositoryProvider);
  }

  public static GetVideoFavoritesIdsUseCase provideGetFavoritesIdsUseCase(
      VideoRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideGetFavoritesIdsUseCase(repository));
  }
}
