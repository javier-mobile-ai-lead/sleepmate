package com.sleepmate.data.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u00c6\u0003J3\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/sleepmate/data/model/GPTResponse;", "", "id", "", "choices", "", "Lcom/sleepmate/data/model/GPTChoice;", "error", "Lcom/sleepmate/data/model/GPTError;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/sleepmate/data/model/GPTError;)V", "getId", "()Ljava/lang/String;", "getChoices", "()Ljava/util/List;", "getError", "()Lcom/sleepmate/data/model/GPTError;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "data_debug"})
public final class GPTResponse {
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "choices")
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<com.sleepmate.data.model.GPTChoice> choices = null;
    @com.google.gson.annotations.SerializedName(value = "error")
    @org.jetbrains.annotations.Nullable()
    private final com.sleepmate.data.model.GPTError error = null;
    
    public GPTResponse(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.util.List<com.sleepmate.data.model.GPTChoice> choices, @org.jetbrains.annotations.Nullable()
    com.sleepmate.data.model.GPTError error) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.sleepmate.data.model.GPTChoice> getChoices() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.sleepmate.data.model.GPTError getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.sleepmate.data.model.GPTChoice> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.sleepmate.data.model.GPTError component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.data.model.GPTResponse copy(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.util.List<com.sleepmate.data.model.GPTChoice> choices, @org.jetbrains.annotations.Nullable()
    com.sleepmate.data.model.GPTError error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}