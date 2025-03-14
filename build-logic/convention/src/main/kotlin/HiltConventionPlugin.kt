import com.example.jetpack.build_logic.convention.extensions.implementation
import com.example.jetpack.build_logic.convention.extensions.ksp
import com.example.jetpack.build_logic.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")
            apply(plugin = "com.google.dagger.hilt.android")

            dependencies {
                ksp(libs.findLibrary("hilt-compiler").get())
                implementation(libs.findLibrary("hilt-android").get())
            }
        }
    }
}