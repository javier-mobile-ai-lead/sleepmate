package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/sleepmate/data/repository/GPTRepositoryImpl;", "Lcom/sleepmate/domain/repository/GPTRepository;", "remoteConfig", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "onboardingPreferences", "Lcom/sleepmate/data/datasource/local/OnboardingPreferences;", "<init>", "(Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;Lcom/sleepmate/data/datasource/local/OnboardingPreferences;)V", "gptApiService", "Lcom/sleepmate/data/api/GPTApiService;", "getGptApiService", "()Lcom/sleepmate/data/api/GPTApiService;", "gptApiService$delegate", "Lkotlin/Lazy;", "getGPTApiKey", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSleepHealthQuery", "userMessage", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public final class GPTRepositoryImpl implements com.sleepmate.domain.repository.GPTRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.remoteconfig.FirebaseRemoteConfig remoteConfig = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.OnboardingPreferences onboardingPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GPT_API_KEY_CONFIG = "gpt_api_key";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GPT_BASE_URL = "https://api.openai.com/";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SYSTEM_PROMPT = "\nEres SleepMate, un asistente especializado en salud del sue\u00f1o y descanso. \n\nINSTRUCCIONES IMPORTANTES:\n- Solo responde preguntas relacionadas con sue\u00f1o, descanso, higiene del sue\u00f1o, insomnio, rutinas nocturnas y temas similares\n- Si la pregunta no est\u00e1 relacionada con el sue\u00f1o, responde cort\u00e9smente que solo puedes ayudar con temas de sue\u00f1o y descanso\n- NO proporciones diagn\u00f3sticos m\u00e9dicos espec\u00edficos\n- NO recomiendes medicamentos espec\u00edficos\n- Si detectas un problema serio de salud, recomienda consultar con un profesional m\u00e9dico\n- Mant\u00e9n tus respuestas en espa\u00f1ol\n- S\u00e9 emp\u00e1tico, comprensivo y \u00fatil\n- Limita tus respuestas a m\u00e1ximo 150 palabras\n- Proporciona consejos pr\u00e1cticos y basados en evidencia cient\u00edfica\n\nEjemplos de lo que S\u00cd puedes responder:\n- T\u00e9cnicas de relajaci\u00f3n para dormir mejor\n- Rutinas de higiene del sue\u00f1o\n- Consejos sobre el ambiente del dormitorio\n- H\u00e1bitos que afectan el sue\u00f1o\n- T\u00e9cnicas de respiraci\u00f3n para relajarse\n\nEjemplos de lo que NO debes responder:\n- Preguntas sobre otros temas de salud no relacionados con el sue\u00f1o\n- Diagn\u00f3sticos m\u00e9dicos espec\u00edficos\n- Recomendaciones de medicamentos\n- Temas que no est\u00e1n relacionados con el descanso\n";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy gptApiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.repository.GPTRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public GPTRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.remoteconfig.FirebaseRemoteConfig remoteConfig, @org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.OnboardingPreferences onboardingPreferences) {
        super();
    }
    
    private final com.sleepmate.data.api.GPTApiService getGptApiService() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getGPTApiKey(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendSleepHealthQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String userMessage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/sleepmate/data/repository/GPTRepositoryImpl$Companion;", "", "<init>", "()V", "GPT_API_KEY_CONFIG", "", "GPT_BASE_URL", "SYSTEM_PROMPT", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}