val graalDebug: String? by project

plugins {
    id("java")
    id("org.graalvm.buildtools.native") version "0.9.4"
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
    implementation("info.picocli:picocli:4.7.0")
    annotationProcessor("info.picocli:picocli-codegen:4.7.0")
    implementation("com.mattmalec:Pterodactyl4J:2.BETA_140")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("org.slf4j:slf4j-jdk14:2.0.6")
}

nativeBuild {
    imageName.set("pteroctl")
    mainClass.set("ru.meproject.pteroctl.Application")
    debug.set(true)
    verbose.set(true)
    fallback.set(true)
    configurationFileDirectories.from(file("build/classes/java/main/META-INF/native-image/picocli-generated"))
    useFatJar.set(false)

    buildArgs.add("-H:+AddAllCharsets")

    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(17))
    })
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}