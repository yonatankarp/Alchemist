/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package alchemist

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * A simple 'hello world' plugin.
 */
class AlchemistPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'alchemist.greeting'")
            }
        }
    }
}
