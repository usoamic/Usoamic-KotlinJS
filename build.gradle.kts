import org.jetbrains.kotlin.gradle.frontend.KotlinFrontendExtension

buildscript {
    val kotlinVersion = "1.3.50"

    repositories {
        jcenter()
        mavenCentral()
        maven ("https://dl.bintray.com/kotlin/kotlin-eap")
    }

    dependencies {
        classpath("org.jetbrains.kotlin", "kotlin-gradle-plugin", kotlinVersion)
        classpath("org.jetbrains.kotlin", "kotlin-frontend-plugin", "0.0.45")
    }
}

allprojects {
    group = "io.usoamic"
    version = "1.0.7"
}

plugins {
    id("kotlin2js") version "1.3.50"
    id("kotlin-dce-js") version "1.3.50"
    id("org.jetbrains.kotlin.frontend") version "1.3.50"
}

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

dependencies {
    compile(kotlin("stdlib-js"))
    compile(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

configure<KotlinFrontendExtension> {
    downloadNodeJsVersion = "latest"

    npm {
        dependency("web3", "^1.2.1")
        dependency("bignumber.js", "^9.0.0")
        dependency("ethereumjs-tx", "^1.3.7")
        dependency("buffer", "^5.2.1")
        dependency("ethereumjs-wallet", "^0.6.3")
        dependency("bip39", "^3.0.2")
    }
}

tasks {
    compileKotlin2Js {
        kotlinOptions {
            metaInfo = true
            sourceMap = false
            moduleKind = "commonjs"
            main = "call"
            suppressWarnings = false
        }
    }
}