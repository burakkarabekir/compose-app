// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        val composeCompilerReportsEnabled = project.findProperty("composeCompilerReports") == "true"
        val composeCompilerMetricsEnabled = project.findProperty("composeCompilerMetrics") == "true"
        val reportsDestination = "${project.layout.projectDirectory}/compose_compiler"
        compilerOptions.apply {
            if (composeCompilerReportsEnabled) {
                freeCompilerArgs.add("-P")
                freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$reportsDestination/compose_compiler_reports")

                logger.lifecycle("Compose compiler reports are enabled for ${project.name}.")
            } else {
                logger.lifecycle("Compose compiler reports are disabled for ${project.name}.")
            }
            if (composeCompilerMetricsEnabled) {
                freeCompilerArgs.add("-P")
                freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$reportsDestination/compose_compiler_metrics")
                logger.lifecycle("Compose compiler metrics are enabled for ${project.name}.")
            } else {
                logger.lifecycle("Compose compiler metrics are disabled for ${project.name}.")
            }
        }
    }
}