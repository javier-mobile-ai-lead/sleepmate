package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.NotificationPushRepository;
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
public final class GetEnableNotificationPushUseCase_Factory implements Factory<GetEnableNotificationPushUseCase> {
  private final Provider<NotificationPushRepository> notificationPushRepositoryProvider;

  private GetEnableNotificationPushUseCase_Factory(
      Provider<NotificationPushRepository> notificationPushRepositoryProvider) {
    this.notificationPushRepositoryProvider = notificationPushRepositoryProvider;
  }

  @Override
  public GetEnableNotificationPushUseCase get() {
    return newInstance(notificationPushRepositoryProvider.get());
  }

  public static GetEnableNotificationPushUseCase_Factory create(
      Provider<NotificationPushRepository> notificationPushRepositoryProvider) {
    return new GetEnableNotificationPushUseCase_Factory(notificationPushRepositoryProvider);
  }

  public static GetEnableNotificationPushUseCase newInstance(
      NotificationPushRepository notificationPushRepository) {
    return new GetEnableNotificationPushUseCase(notificationPushRepository);
  }
}
