plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "my.washer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("my.washer.Washer")
}

javafx {
    version = "21"                          // Версия JavaFX
    modules = listOf("javafx.controls", "javafx.fxml")  // Нужные модули
}