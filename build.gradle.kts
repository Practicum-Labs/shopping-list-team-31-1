plugins {
    id("com.android.application") version "8.13.2" apply false
    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
}

// Задача для форматирования через detekt плагин
tasks.register("detektFormat") {
    description = "Reformats whole code base using detekt"
    group = "formatting"

    // Запускаем detekt задачу для всех подпроектов с автоисправлением
    dependsOn(subprojects.map { "${it.path}:detekt" })

    doLast {
        println("Detekt formatting completed. Check the modified files.")
    }
}

// Задача для проверки всех модулей
tasks.register("detektAll") {
    description = "Runs detekt for all modules"
    group = "verification"
    dependsOn(subprojects.map { "${it.path}:detekt" })
}