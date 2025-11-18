package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.DarkModeRepository;
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
public final class DarkModeUseCase_Factory implements Factory<DarkModeUseCase> {
  private final Provider<DarkModeRepository> darkModeRepositoryProvider;

  private DarkModeUseCase_Factory(Provider<DarkModeRepository> darkModeRepositoryProvider) {
    this.darkModeRepositoryProvider = darkModeRepositoryProvider;
  }

  @Override
  public DarkModeUseCase get() {
    return newInstance(darkModeRepositoryProvider.get());
  }

  public static DarkModeUseCase_Factory create(
      Provider<DarkModeRepository> darkModeRepositoryProvider) {
    return new DarkModeUseCase_Factory(darkModeRepositoryProvider);
  }

  public static DarkModeUseCase newInstance(DarkModeRepository darkModeRepository) {
    return new DarkModeUseCase(darkModeRepository);
  }
}
