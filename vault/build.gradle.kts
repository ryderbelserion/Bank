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
    processResources {
        inputs.properties("apiVersion" to libs.versions.minecraft.get())

        filesMatching("paper-plugin.yml") {
            expand(inputs.properties)
        }
    }
}