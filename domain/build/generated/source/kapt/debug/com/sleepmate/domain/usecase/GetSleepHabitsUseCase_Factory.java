package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.SleepHabitRepository;
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
public final class GetSleepHabitsUseCase_Factory implements Factory<GetSleepHabitsUseCase> {
  private final Provider<SleepHabitRepository> repositoryProvider;

  private GetSleepHabitsUseCase_Factory(Provider<SleepHabitRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetSleepHabitsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetSleepHabitsUseCase_Factory create(
      Provider<SleepHabitRepository> repositoryProvider) {
    return new GetSleepHabitsUseCase_Factory(repositoryProvider);
  }

  public static GetSleepHabitsUseCase newInstance(SleepHabitRepository repository) {
    return new GetSleepHabitsUseCase(repository);
  }
}
