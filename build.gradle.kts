plugins {
    id("java")
    id("com.diffplug.spotless") version "8.4.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        // Google Java Format でコード整形
        googleJavaFormat("1.28.0").aosp()
        // import を自動削除
        removeUnusedImports()
        // 末尾空白を削除
        trimTrailingWhitespace()
        // ファイル末尾に改行を追加
        endWithNewline()
        // ライセンスヘッダー（任意）
        // licenseHeader("/* (C) 2025 Example Corp */")
    }
    // Kotlin DSL ビルドファイルも整形対象
    kotlinGradle {
        ktlint("1.5.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

// ビルド前に Spotless チェックを実行（CI向け）
tasks.named("check") { dependsOn("spotlessCheck") }
