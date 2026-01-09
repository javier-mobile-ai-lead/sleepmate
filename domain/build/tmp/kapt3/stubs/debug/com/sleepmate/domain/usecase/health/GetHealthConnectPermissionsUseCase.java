package com.sleepmate.domain.usecase.health;

/**
 * Use case to retrieve the set of required permissions for Health Connect.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/sleepmate/domain/usecase/health/GetHealthConnectPermissionsUseCase;", "", "healthDataRepository", "Lcom/sleepmate/domain/repository/HealthDataRepository;", "<init>", "(Lcom/sleepmate/domain/repository/HealthDataRepository;)V", "invoke", "", "", "domain_debug"})
public final class GetHealthConnectPermissionsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.HealthDataRepository healthDataRepository = null;
    
    @javax.inject.Inject()
    public GetHealthConnectPermissionsUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.HealthDataRepository healthDataRepository) {
        super();
    }
    
    /**
     * Returns the required Health Connect permissions as defined in the repository.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> invoke() {
        return null;
    }
}