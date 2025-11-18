package com.sleepmate.domain.di;

import com.sleepmate.domain.repository.SleepHabitRepository;
import com.sleepmate.domain.usecase.GetSleepHabitsUseCase;
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
public final class DomainModule_ProvideGetSleepHabitsUseCaseFactory implements Factory<GetSleepHabitsUseCase> {
  private final Provider<SleepHabitRepository> repositoryProvider;

  private DomainModule_ProvideGetSleepHabitsUseCaseFactory(
      Provider<SleepHabitRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetSleepHabitsUseCase get() {
    return provideGetSleepHabitsUseCase(repositoryProvider.get());
  }

  public static DomainModule_ProvideGetSleepHabitsUseCaseFactory create(
      Provider<SleepHabitRepository> repositoryProvider) {
    return new DomainModule_ProvideGetSleepHabitsUseCaseFactory(repositoryProvider);
  }

  public static GetSleepHabitsUseCase provideGetSleepHabitsUseCase(
      SleepHabitRepository repository) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideGetSleepHabitsUseCase(repository));
  }
}
