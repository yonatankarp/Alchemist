package alchemist

import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Nested

abstract class AlchemistExtension {
    @get:Nested
    abstract val specs: ListProperty<SpecConfig>
}

data class SpecConfig (
    val name: String,
    val specPath: String,
    val outputDir: String,
    val generatorType: String,
    val packageName: String,
    val modelPackageName: String,
    val config: Map<String, String> = emptyMap(),
    val templatesDir: String? = null,
)
