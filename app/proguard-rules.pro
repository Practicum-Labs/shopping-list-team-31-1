-optimizationpasses 5
-allowaccessmodification
-dontpreverify

-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
-keepattributes SourceFile,LineNumberTable
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.foundation.** { *; }
-keep class androidx.compose.material.** { *; }
-keep class androidx.compose.animation.** { *; }
-keep class androidx.compose.material3.** { *; }

-keepclassmembers class ** {
    @androidx.compose.runtime.Composable *;
}
-keep class androidx.compose.runtime.Composer { *; }
-keep class androidx.compose.runtime.ComposerKt { *; }
-keep class androidx.compose.runtime.internal.ComposableLambdaImpl { *; }
-keep class androidx.compose.runtime.internal.ComposableLambdaKt { *; }

# Сохраняем NodeKind и узлы (решает ошибку NodeKindKt)
-keep class androidx.compose.ui.node.** { *; }
-keepclassmembers class androidx.compose.ui.node.** { *; }

# Сохраняем модификаторы
-keep class androidx.compose.ui.Modifier { *; }
-keepclassmembers class androidx.compose.ui.Modifier.** { *; }

# Предупреждения для Preview (можно игнорировать)
-dontwarn androidx.compose.ui.tooling.**

-keep class ru.practicum.android.projectmonth.shoppinglist.domain.models.** { *; }
-keepclassmembers class ru.practicum.android.projectmonth.shoppinglist.domain.models.** { *; }

-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Сохраняем все модели, используемые в @Body, @Query, @Path, @Field
-keep class ru.practicum.android.projectmonth.shoppinglist.data.** { *; }
-keepclassmembers class ru.practicum.android.projectmonth.shoppinglist.data.** {
    *;
}

# Сохраняем все DTO, Request, Response
-keep class ru.practicum.android.projectmonth.shoppinglist.data.network.dto.** { *; }
-keepclassmembers class ru.practicum.android.projectmonth.shoppinglist.data.network.dto.** {
    *;
}
-keep class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName *;
}
-keepclassmembers class * {
    @com.google.gson.annotations.Expose *;
}
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# ============================================
# OkHttp (ПОЛНЫЕ ПРАВИЛА)
# ============================================
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.logging.** { *; }

-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers class * {
    @androidx.room.* <fields>;
}
-keepclassmembers class * {
    @androidx.room.ColumnInfo *;
}

-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
-keepclassmembers class * extends android.app.Fragment {
    public void *(android.view.View);
}

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}