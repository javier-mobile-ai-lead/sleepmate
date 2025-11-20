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
public final class GetVideoRecommendationsUseCase_Factory implements Factory<GetVideoRecommendationsUseCase> {
  private final Provider<VideoRepository> repositoryProvider;

  private GetVideoRecommendationsUseCase_Factory(Provider<VideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetVideoRecommendationsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetVideoRecommendationsUseCase_Factory create(
      Provider<VideoRepository> repositoryProvider) {
    return new GetVideoRecommendationsUseCase_Factory(repositoryProvider);
  }

  public static GetVideoRecommendationsUseCase newInstance(VideoRepository repository) {
    return new GetVideoRecommendationsUseCase(repository);
  }
}
