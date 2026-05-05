import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider

@CacheableTask
abstract class GenerateProguardRulesTask : DefaultTask() {

    @get:Input
    abstract val className: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val file = outputFile.get().asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            -dontobfuscate
            -keep class dev.brahmkshatriya.echo.extension.${className.get()}
            """.trimIndent()
        )
    }
}

fun Project.generateProguardRules(meta: ExtensionMetadata): TaskProvider<GenerateProguardRulesTask> =
    tasks.register("generateProguardRules", GenerateProguardRulesTask::class.java) {
        className.set(meta.className)
        outputFile.set(layout.buildDirectory.file("generated/proguard/generated-rules.pro"))
    }
