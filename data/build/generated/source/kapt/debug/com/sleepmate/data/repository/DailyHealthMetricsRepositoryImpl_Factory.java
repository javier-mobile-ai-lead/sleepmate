package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao;
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
public final class DailyHealthMetricsRepositoryImpl_Factory implements Factory<DailyHealthMetricsRepositoryImpl> {
  private final Provider<DailyHealthMetricsDao> daoProvider;

  private DailyHealthMetricsRepositoryImpl_Factory(Provider<DailyHealthMetricsDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DailyHealthMetricsRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static DailyHealthMetricsRepositoryImpl_Factory create(
      Provider<DailyHealthMetricsDao> daoProvider) {
    return new DailyHealthMetricsRepositoryImpl_Factory(daoProvider);
  }

  public static DailyHealthMetricsRepositoryImpl newInstance(DailyHealthMetricsDao dao) {
    return new DailyHealthMetricsRepositoryImpl(dao);
  }
}
