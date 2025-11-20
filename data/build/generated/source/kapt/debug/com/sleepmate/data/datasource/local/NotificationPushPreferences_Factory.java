package com.sleepmate.data.datasource.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NotificationPushPreferences_Factory implements Factory<NotificationPushPreferences> {
  private final Provider<Context> contextProvider;

  private NotificationPushPreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NotificationPushPreferences get() {
    return newInstance(contextProvider.get());
  }

  public static NotificationPushPreferences_Factory create(Provider<Context> contextProvider) {
    return new NotificationPushPreferences_Factory(contextProvider);
  }

  public static NotificationPushPreferences newInstance(Context context) {
    return new NotificationPushPreferences(context);
  }
}
