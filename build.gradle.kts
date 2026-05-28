import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("com.android.application") version "9.2.1" apply false
    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
}

// Применяем плагин detekt для всех подпроектов
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    configure<DetektExtension> {
        toolVersion = "1.23.8"
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = true

        source.setFrom(
            fileTree(rootDir) {
                exclude("**/*_impl.kt")
                exclude("**/*_Impl.kt")
                exclude("**/build/**")
                exclude("**/generated/**")
            }
        )
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        reports {
            html.required.set(true)
            xml.required.set(false)
            txt.required.set(false)
        }
    }
}

// Задача для проверки всех модулей
tasks.register("detektAll") {
    description = "Runs detekt for all modules"
    group = "verification"
    dependsOn(subprojects.map { "${it.path}:detekt" })
}
