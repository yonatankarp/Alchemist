plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

// Configure the extension using a DSL block
//configure<AlchemistPluginExtension> {
//    specs.set(
//        listOf(
//            SpecConfig(
//                name = "Cash Book Api V2",
//                specPath = "test1",
//                outputDir = "test1",
//                generatorType = "test1",
//                packageName = "test1",
//                modelPackageName = "test1",
//                config = mapOf("test1" to "test1")
//            )
//        )
//    )
//}


dependencies {
    implementation("org.openapitools:openapi-generator-gradle-plugin:6.6.0")

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

gradlePlugin {
    @Suppress("unused_variable")
    val alchemist by plugins.creating {
        id = "com.yonatankarp.alchemist"
        implementationClass = "alchemist.AlchemistPlugin"
    }
}

//// Add a source set for the functional test suite
//val functionalTestSourceSet = sourceSets.create("functionalTest") {
//}
//
//configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
//
//// Add a task to run the functional tests
//val functionalTest by tasks.registering(Test::class) {
//    testClassesDirs = functionalTestSourceSet.output.classesDirs
//    classpath = functionalTestSourceSet.runtimeClasspath
//    useJUnitPlatform()
//}
//
//gradlePlugin.testSourceSets.add(functionalTestSourceSet)
//
//tasks.named<Task>("check") {
//    // Run the functional tests as part of `check`
//    dependsOn(functionalTest)
//}

tasks.named<Test>("test") {
    // Use JUnit Jupiter for unit tests.
    useJUnitPlatform()
}

