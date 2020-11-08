import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    antlr
}
group = "net.prettyrandom.kotlin_thrift"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.8")

    implementation("org.antlr:antlr4:4.8")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.named("compileKotlin") {
    dependsOn(tasks.named("generateGrammarSource"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor", "-no-listener", "-long-messages")
}