package com.sleepmate.domain.usecase.health;

import com.sleepmate.domain.repository.HealthDataRepository;
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
public final class GetGrantedHealthPermissionsUseCase_Factory implements Factory<GetGrantedHealthPermissionsUseCase> {
  private final Provider<HealthDataRepository> repositoryProvider;

  private GetGrantedHealthPermissionsUseCase_Factory(
      Provider<HealthDataRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetGrantedHealthPermissionsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetGrantedHealthPermissionsUseCase_Factory create(
      Provider<HealthDataRepository> repositoryProvider) {
    return new GetGrantedHealthPermissionsUseCase_Factory(repositoryProvider);
  }

  public static GetGrantedHealthPermissionsUseCase newInstance(HealthDataRepository repository) {
    return new GetGrantedHealthPermissionsUseCase(repository);
  }
}
