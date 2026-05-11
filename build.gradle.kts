plugins {
    id("java")
    id("application")
}

group = "com.nr"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.nr.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.newrelic.agent.java:newrelic-api:9.2.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.nr.Main")
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}