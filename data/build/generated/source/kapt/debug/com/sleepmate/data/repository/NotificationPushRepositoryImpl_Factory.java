package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.NotificationPushPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class NotificationPushRepositoryImpl_Factory implements Factory<NotificationPushRepositoryImpl> {
  private final Provider<NotificationPushPreferences> notificationPushPreferencesProvider;

  private NotificationPushRepositoryImpl_Factory(
      Provider<NotificationPushPreferences> notificationPushPreferencesProvider) {
    this.notificationPushPreferencesProvider = notificationPushPreferencesProvider;
  }

  @Override
  public NotificationPushRepositoryImpl get() {
    return newInstance(notificationPushPreferencesProvider.get());
  }

  public static NotificationPushRepositoryImpl_Factory create(
      Provider<NotificationPushPreferences> notificationPushPreferencesProvider) {
    return new NotificationPushRepositoryImpl_Factory(notificationPushPreferencesProvider);
  }

  public static NotificationPushRepositoryImpl newInstance(
      NotificationPushPreferences notificationPushPreferences) {
    return new NotificationPushRepositoryImpl(notificationPushPreferences);
  }
}
