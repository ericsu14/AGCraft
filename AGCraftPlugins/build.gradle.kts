/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    eclipse
    id("io.papermc.paperweight.userdev") version "1.3.3"
    `maven-publish`
}

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
	paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    implementation("org.yaml:snakeyaml:1.26")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("org.mockito:mockito-core:2.21.0")
    implementation("org.powermock:powermock-module-junit4:2.0.2")
    implementation("org.powermock:powermock-api-mockito2:2.0.2")
    testImplementation("org.powermock:powermock-module-testng:2.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
}

tasks {
  // Configure reobfJar to run when invoking the build task
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release.set(17)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }
  processResources {
    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
  }
}

sourceSets {
	main {
		java {
			setSrcDirs(listOf("src"))
		}
	}
	test{
		java {
			setSrcDirs(listOf("test"))
		}
	}
}

tasks.test {
    useJUnit()

    maxHeapSize = "1G"
}

group = "AGCraftPlugins"
version = "1.8.0"
description = "AGCraftPlugins"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
