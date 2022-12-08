plugins {
    kotlin("jvm") version "1.7.22"
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }

    register("newDay") {
        // Check required arguments
        if (!project.hasProperty("day")) {
            throw InvalidUserDataException("Day is required")
        }

        val day: String = project.properties["day"].toString()
        val directory = "src/day$day"
        mkdir(directory)
        File("src/day$day", "Day$day.txt").writeText("")
        File("src/day$day", "Day${day}_test.txt").writeText("")
        val template: String = File("day.template").readText()
        File("src/day$day", "Day$day.kt")
            .writeText(template.replace("{day}", day, true))
    }
}
