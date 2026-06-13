plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "ru.washer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("io.javalin:javalin:6.7.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("gg.jte:jte:3.1.9")
    implementation("io.javalin:javalin-rendering:6.7.0")
    implementation("org.postgresql:postgresql:42.7.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.github.cdimascio:dotenv-java:3.2.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("ru.washer.Washer")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    manifest {
        attributes["Main-Class"] = "ru.washer.Washer" // Твой главный класс
    }
    mergeServiceFiles()
}

tasks.named("build") {
    dependsOn("shadowJar")
}