plugins {
    kotlin("jvm") version "1.6.20"
    id("application")
    id("java")
    id("idea")

    // This is used to create a GraalVM native image
    id("org.graalvm.buildtools.native") version "0.9.11"

    // This creates a fat JAR
    id("com.github.johnrengelman.shadow") version "7.1.2"

    // // This will handle versioning - hopefully
    //id("pl.allegro.tech.build.axion-release") version "1.14.2"

   // id("dev.quiescence.plugins.versioning") version "0.1"
	
}

group = "com.ido"
description = "HelloWorld"

application.mainClass.set("com.ido.HelloWorld")
// tasks.jar {
//     manifest {
//         attributes["Main-Class"] = "com.ido.HelloWorld" 
//     }
// }
tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}
repositories {
    mavenCentral()
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("helloworld")
            mainClass.set("com.ido.HelloWorld")
            fallback.set(false)
            sharedLibrary.set(false)
            useFatJar.set(true)
            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(17))
                vendor.set(JvmVendorSpec.matching("GraalVM Community"))
            })
        }
    }
}
