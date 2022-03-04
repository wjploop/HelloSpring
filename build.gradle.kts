import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    kotlin("jvm") version "1.6.10"
//    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.6.10"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.jpa") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
}

group = "com.wjp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    // 包含日志
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.data:spring-data-rest-hal-explorer")
    implementation("org.springframework.session:spring-session-core")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    implementation("junit:junit:4.13.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("org.springdoc:springdoc-openapi-ui:+")

    // mysql driver
    runtimeOnly("mysql:mysql-connector-java")

    developmentOnly("commons-net:commons-net:3.8.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    manifest {
        println("manifest")
    }
}

buildscript {
    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.jcraft:jsch:0.1.55")
    }
}

configurations {

}

task("publishJar") {
    val bootJar = tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar")
    dependsOn.add(bootJar)

    val jsch = com.jcraft.jsch.JSch()
    jsch.getSession("ubuntu", "wjploop.xyz").apply {
        setConfig("StrictHostKeyChecking", "no")
        setPassword("qwerA1234")
        connect()
        openChannel("sftp").apply {
            connect()
            this as com.jcraft.jsch.ChannelSftp
            put(
                "${projectDir}/build/libs/HelloSpring-0.0.1-SNAPSHOT.jar", "/home/ubuntu/jar"
            )
            disconnect()
        }
        println("updated jar")

        openChannel("exec").apply {
            val channel  = this
            this as com.jcraft.jsch.ChannelExec
            setCommand("cd ~/jar  && ps -A u | grep 'sudo nohup java -jar' | grep -m1 '' | sudo kill `awk '{print \$2}'` | sudo nohup java -jar HelloSpring-0.0.1-SNAPSHOT.jar &")
            channel.connect()
            inputStream.bufferedReader().readLines().let {
                println(it)
            }

            channel.outputStream.writer(Charsets.UTF_8).write("echo hello > hello.txt")
            channel.disconnect()
            println("restart java app")

        }
        disconnect()
    }
}
