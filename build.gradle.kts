plugins {
    id("java")
    id("org.graalvm.buildtools.native") version "0.9.11"
}

group = "ru.meproject"
version = "1.0-SNAPSHOT"
val javaTarget = 17

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaTarget))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.mattmalec.com/repository/releases")
}

dependencies {
    implementation("org.spongepowered:configurate-gson:4.1.2")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("info.picocli:picocli:4.6.3")
    annotationProcessor("info.picocli:picocli-codegen:4.6.3")
    implementation("com.mattmalec:Pterodactyl4J:2.BETA_134")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
        named("main") {
            imageName.set("pteroctl")
            mainClass.set("ru.meproject.pterocli.Application")
            debug.set(true) // Determines if debug info should be generated, defaults to false
            verbose.set(true) // Add verbose output, defaults to false
            fallback.set(true) // Sets the fallback mode of native-image, defaults to false
            // buildArgs.add("-H:Extra")
            // jvmArgs.add("flag") // Passes 'flag' directly to the JVM running the native image builder
            configurationFileDirectories.from(file("build/classes/java/main/META-INF/native-image/picocli-generated"))
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}