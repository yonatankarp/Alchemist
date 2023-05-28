package alchemist

import java.util.Locale
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.create
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask


@Suppress("unused")
class AlchemistPlugin : Plugin<Project> {
    /**
     * Applies the plugin to the project.
     */
    override fun apply(project: Project) {
        /**
         * A list of tasks that generate code for each spec, the general
         * generateOpenApiSpecs task depends on all of them.
         */
        val generatedTasks = mutableListOf<GenerateTask>()

        /**
         * The extension that configures the plugin.
         */
        val extension = project
            .extensions
            .create<AlchemistExtension>("alchemist")

        project.afterEvaluate {
            extension
                .specs.get()
                .forEach { generatedTasks.add(tasks.generateTaskForSpec(it)) }
        }

        project.task("generateOpenApiSpecs") {
            group = "alchemist"
            description = "Generate the code for all specs"
            dependsOn(generatedTasks)
        }
    }

    /**
     * Creates a task for generating code for a specific spec.
     */
    private fun TaskContainer.generateTaskForSpec(spec: SpecConfig) =
        create("generate${spec.name.toUpperCamelCase()}", GenerateTask::class) {
            group = "alchemist"
            description = "Generate the code for ${spec.name}"

            generatorName.set(spec.generatorType)
            inputSpec.set(spec.specPath)
            outputDir.set(spec.outputDir)
            apiPackage.set(spec.packageName)
            modelPackage.set(spec.modelPackageName)
            configOptions.set(spec.config)
            spec.templatesDir?.let { templateDir.set(it) }
        }
}

/**
 * Extension function to convert a string to upper camel case.
 */
private fun String.toUpperCamelCase() =
    split(" ", "_", "-")
        .joinToString("") {
            it.lowercase(Locale.getDefault())
                .replaceFirstChar { character ->
                    if (character.isLowerCase()) character.titlecase(Locale.getDefault())
                    else character.toString()
                }
        }
