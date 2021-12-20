import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    `java-library`
    `maven-publish`
    application
}

group = "com.github.iammohdzaki"
version = "0.1"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.iammohdzaki"
            artifactId = "passgen"
            version = "0.1"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //(zxcvbn) Java,Password Strength Estimator
    implementation("com.nulab-inc:zxcvbn:1.5.2")

    //Unit Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("com.google.truth:truth:1.1.3")

    //CLI Input
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("AppKt")
}

val jar by tasks.getting(Jar::class){
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest{
        attributes["Main-Class"] = "AppKt"
    }
    from(configurations.runtimeClasspath.get().map{
        if(it.isDirectory) it
        else zipTree(it)
    }){
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}