val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val prometheusVersion: String by project
val exposedVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
}

group = "com.example"
version = "0.0.2"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-freemarker")
    implementation("io.ktor:ktor-server-content-negotiation")
//    implementation("io.ktor:ktor-serialization-gson")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-html-builder")
    implementation("io.ktor:ktor-serialization-jackson")
    implementation("io.ktor:ktor-server-locations")
    implementation("io.ktor:ktor-server-metrics-micrometer")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-host-common")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-websockets")

    implementation("io.ktor:ktor-server-data-conversion")
    implementation("io.ktor:ktor-server-status-pages")

    implementation("io.ktor:ktor-server-conditional-headers")
    implementation("io.ktor:ktor-server-auto-head-response")
    implementation("io.ktor:ktor-server-double-receive")
    implementation("io.ktor:ktor-server-default-headers")
    implementation("io.ktor:ktor-server-call-logging")
    implementation("io.ktor:ktor-server-compression")
    implementation("io.ktor:ktor-server-caching-headers")
    implementation("io.ktor:ktor-server-cors")

    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.508")
    implementation("io.micrometer:micrometer-registry-prometheus:$prometheusVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-tests")

    //region webjars
    implementation("io.ktor:ktor-server-webjars")
    implementation("org.webjars:jquery:3.6.2")
    implementation("org.webjars:vue:2.1.3")
    implementation("org.webjars:ionicons:2.0.1")
    implementation("org.webjars.npm:google-polyline:1.0.0")
    //endregion

    implementation("org.freemarker:freemarker:2.3.31")

    // database
    implementation("io.ktor:ktor-server-resources")
    implementation("io.ktor:ktor-server-sessions")
    implementation("io.ktor:ktor-server-partial-content")
    implementation("com.h2database:h2:2.1.214")
    implementation("com.mchange:c3p0:0.9.5.5")
    implementation("org.ehcache:ehcache:3.9.7")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
}