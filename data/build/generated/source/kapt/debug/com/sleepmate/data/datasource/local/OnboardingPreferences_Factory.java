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
public final class OnboardingPreferences_Factory implements Factory<OnboardingPreferences> {
  private final Provider<Context> contextProvider;

  private OnboardingPreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OnboardingPreferences get() {
    return newInstance(contextProvider.get());
  }

  public static OnboardingPreferences_Factory create(Provider<Context> contextProvider) {
    return new OnboardingPreferences_Factory(contextProvider);
  }

  public static OnboardingPreferences newInstance(Context context) {
    return new OnboardingPreferences(context);
  }
}
