package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.VideoRepository;
import com.sleepmate.domain.usecase.GetVideoRecommendationsUseCase;
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
public final class DomainModule_ProvideGetVideosRecommendationsUseCaseFactory implements Factory<GetVideoRecommendationsUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private DomainModule_ProvideGetVideosRecommendationsUseCaseFactory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoRecommendationsUseCase get() {
    return provideGetVideosRecommendationsUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideGetVideosRecommendationsUseCaseFactory create(
      Provider<VideoRepository> repositoryProvider) {
    return new DomainModule_ProvideGetVideosRecommendationsUseCaseFactory(repositoryProvider);
  }

  public static GetVideoRecommendationsUseCase provideGetVideosRecommendationsUseCase(
      VideoRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideGetVideosRecommendationsUseCase(repository));
  }
}
