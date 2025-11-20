package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.VideoRepository;
import com.sleepmate.domain.usecase.GetVideoRecommendationRefreshUseCase;
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
public final class DomainModule_ProvideGetVideosFavoritesUseCaseFactory implements Factory<GetVideoRecommendationRefreshUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private DomainModule_ProvideGetVideosFavoritesUseCaseFactory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoRecommendationRefreshUseCase get() {
    return provideGetVideosFavoritesUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideGetVideosFavoritesUseCaseFactory create(
      Provider<VideoRepository> repositoryProvider) {
    return new DomainModule_ProvideGetVideosFavoritesUseCaseFactory(repositoryProvider);
  }

  public static GetVideoRecommendationRefreshUseCase provideGetVideosFavoritesUseCase(
      VideoRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideGetVideosFavoritesUseCase(repository));
  }
}
