# Alchemist

Alchemist is a Gradle plugin written in Kotlin DSL that simplifies the process
of generating multiple OpenAPI specifications. It provides a convenient
configuration interface to define the specifications and their associated
settings, allowing for easy customization and generation.

## Features

- Generate multiple OpenAPI specs effortlessly.
- Configure each specification with individual settings.
- Customize output directories, package names, and generator types for each spec.
- Easy integration with Gradle build scripts written in Kotlin DSL.

## Installation

To use Alchemist in your Gradle project, follow these steps:

1. Add the following to your project's `settings.gradle.kts` file:

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
```

2. Add the following to your project's `build.gradle.kts` file:

```kotlin
plugins {
    id("com.example.alchemist") version "1.0.0" // Replace with the actual plugin ID and version
}
```

3. Configure Alchemist in the same file (`build.gradle.kts`) to specify your
   desired OpenAPI specifications:

```kotlin
alchemist {
    specs {
        create("CashBookApiV2") {
            specPath = file("path/to/spec.yaml")
            outputDir = file("generated/api")
            generatorType = "openapi-generator"
            packageName = "com.example.api"
            modelPackageName = "com.example.model"
            config {
                put("language", "kotlin")
                put("apiPackage", "com.example.api")
                put("modelPackage", "com.example.model")
            }
        }
    }
}
```

Ensure you replace the values with your specific configuration details. You can
define multiple `create` blocks to generate multiple specifications with
different settings.

## Configuration

The Alchemist plugin configuration uses the `alchemist` extension block, which
provides the `specs` block to define the OpenAPI specifications. Inside the
`specs` block, you can use the `create` function to create individual
specification configurations.

The `create` function takes a name for the specification and allows you to
configure the following properties:

- `specPath`: The path to the specification file.
- `outputDir`: The output directory for the generated specification files.
- `generatorType`: The type of generator to use for the specification.
- `packageName`: The package name for the generated code.
- `modelPackageName`: The package name for the generated model code.
- `config`: Additional configuration options for the specification (provided as
  a map).

## Usage

After configuring Alchemist, you can execute the generateSpecs task to generate
the OpenAPI specifications:

```shell
./gradlew generateOpenApiSpecs
```

The task will process each specification defined in the `specs` configuration
and generate the corresponding output files.

## License

This library is licensed under the [MIT License](LICENSE).

## Contributing

Contributions to Alchemist are welcome! If you find any issues or have ideas for
improvements, please open an issue or submit a pull request on this GitHub
repository.

## Maintainers

- Yonatan Karp-Rudin - [yonatankarp](https://github.com/yonatankarp) 
