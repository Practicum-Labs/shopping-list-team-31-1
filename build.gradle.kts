import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    id("com.android.application") version "8.13.2" apply false
    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
}

// Функция для общих настроек
fun Detekt.setupCommonDetektSettings() {
    parallel = true
    autoCorrect = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = true

    setSource(files(project.projectDir))
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**", "**/generated/**")

    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(false)
    }
}

// Задача для проверки
tasks.register<Detekt>("detektAll") {
    description = "Runs over whole code base"
    group = "verification"
    setupCommonDetektSettings()
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
}

// Задача для форматирования
tasks.register<Detekt>("detektFormat") {
    description = "Reformats whole code base"
    group = "formatting"
    setupCommonDetektSettings()
    autoCorrect = true
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
}