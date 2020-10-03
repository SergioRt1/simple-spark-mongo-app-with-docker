import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.4.10"
}
group = "escuelaing.aygo.sergio"
version = "1.0-SNAPSHOT"
application {
    mainClassName = "escuelaing.aygo.sergio.SparkWebServer"
}
noArg {
    annotation("escuelaing.aygo.sergio.annotations.NoArgsConstructor")
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("com.sparkjava:spark-kotlin:1.0.0-alpha")
    implementation("org.mongodb:mongo-java-driver:3.12.7")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    testImplementation(kotlin("test-junit5"))
}
tasks {
    withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
    withType<Jar> {
        doFirst {
            from(Callable { configurations["compileClasspath"].map { if (it.isDirectory) it else zipTree(it) } })
        }
        exclude("META-INF/*.RSA", "META-INF/*.SF","META-INF/*.DSA")
        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
        }
    }
}