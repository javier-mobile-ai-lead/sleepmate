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
public final class GetVideoRecommendationRefreshUseCase_Factory implements Factory<GetVideoRecommendationRefreshUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private GetVideoRecommendationRefreshUseCase_Factory(
      Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoRecommendationRefreshUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetVideoRecommendationRefreshUseCase_Factory create(
      Provider<VideoRepository> repositoryProvider) {
    return new GetVideoRecommendationRefreshUseCase_Factory(repositoryProvider);
  }

  public static GetVideoRecommendationRefreshUseCase newInstance(VideoRepository repository) {
    return new GetVideoRecommendationRefreshUseCase(repository);
  }
}
