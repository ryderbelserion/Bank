plugins {
    `maven-publish`

    `java-library`
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")

    group = "com.ryderbelserion.bank"
    description = "An alternative to vault."
    version = "1.0.0"

    repositories {
        maven("https://jitpack.io")

        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }

        withJavadocJar()
        withSourcesJar()
    }

    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(21)
        }

        javadoc {
            options.encoding = Charsets.UTF_8.name()
        }

        processResources {
            filteringCharset = Charsets.UTF_8.name()
        }
    }
}