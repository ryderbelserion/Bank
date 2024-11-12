plugins {
    alias(libs.plugins.shadow)
}

project.version = "1.7.3"

repositories {
    maven("https://repo.papermc.io/repository/maven-public")
}

dependencies {
    api("com.github.MilkBowl", "VaultAPI", "1.7") {
        exclude("org.bukkit", "*")
    }

    compileOnly(libs.paper)
}

tasks {
    shadowJar {
        archiveBaseName.set("Vault")
        archiveClassifier.set("")
    }

    processResources {
        inputs.properties("apiVersion" to libs.versions.minecraft.get())

        filesMatching("paper-plugin.yml") {
            expand(inputs.properties)
        }
    }
}