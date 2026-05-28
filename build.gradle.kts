import dev.detekt.gradle.Detekt

plugins {
    id("com.android.application") version "9.2.1" apply false
    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21"
//    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
    id("dev.detekt") version ("2.0.0-alpha.3")

}

detekt {
    toolVersion = "2.0.0-alpha.3"
    config.setFrom(files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true

    source.setFrom(
        fileTree(rootDir) {
//            include("**/*.kt")
            exclude("**/*_impl.kt")
            exclude("**/*_Impl.kt")
            exclude("**/build/**")
            exclude("**/generated/**")
        }
    )

}

tasks.withType<Detekt>().configureEach {
    reports {
        checkstyle.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        markdown.required.set(true)
    }
}


tasks.register<JavaExec>("detektFormat") {
    description = "Reformats whole code base using detekt"
    group = "formatting"

    mainClass.set("dev.detekt.cli.Main")
    classpath = configurations.detekt.get()

    // Добавляем флаг --auto-correct
    val input = projectDir
    val config = "$projectDir/config/detekt/detekt.yml"
    val params = listOf(
        "-i", input.toString(),
        "-c", config,
        "--auto-correct",  // ← ключевой флаг для автоисправления
        "--report", "html:build/reports/detekt/format.html"
    )

    args(params)

    // Не запускаем для всех подпроектов, а только для текущего
    dependsOn(configurations.detekt)
}

// Задача для проверки всех модулей
tasks.register("detektAll") {
    description = "Runs detekt for all modules"
    group = "verification"
    dependsOn(subprojects.map { "${it.path}:detekt" })
}
