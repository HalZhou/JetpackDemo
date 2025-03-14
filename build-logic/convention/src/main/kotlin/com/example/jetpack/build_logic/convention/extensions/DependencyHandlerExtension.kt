package com.example.jetpack.build_logic.convention.extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation : Any) : Dependency? {
    return add("implementation",dependencyNotation)
}

fun DependencyHandler.api(dependencyNotation : Any) : Dependency? {
    return add("api",dependencyNotation)
}

fun DependencyHandler.ksp(dependencyNotation : Any) : Dependency? {
    return add("ksp",dependencyNotation)
}

fun DependencyHandler.testImplementation(dependencyNotation : Any) : Dependency? {
    return add("testImplementation",dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation : Any) : Dependency? {
    return add("androidTestImplementation",dependencyNotation)
}

fun DependencyHandler.debugImplementation(dependencyNotation : Any) : Dependency? {
    return add("debugImplementation",dependencyNotation)
}

fun DependencyHandler.testRuntimeOnly(dependencyNotation : Any) : Dependency? {
    return add("testRuntimeOnly",dependencyNotation)
}