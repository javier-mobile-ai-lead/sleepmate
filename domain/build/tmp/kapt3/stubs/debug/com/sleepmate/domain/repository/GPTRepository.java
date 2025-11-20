package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/GPTRepository;", "", "sendSleepHealthQuery", "", "userMessage", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGPTApiKey", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface GPTRepository {
    
    /**
     * Envía un mensaje de consulta sobre salud del sueño a GPT
     * @param userMessage mensaje del usuario
     * @return respuesta de la IA
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendSleepHealthQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String userMessage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Obtiene el API Key de GPT desde Firebase Remote Config
     * @return API Key si está disponible
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGPTApiKey(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
}