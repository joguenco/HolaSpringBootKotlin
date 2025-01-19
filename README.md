# HolaSpringBootKotlin
Example of Spring Boot with Kotlin in Gradle

## Dependencies
- Java 21
- Gradle 8.11.1
- SQLite

## Hot Reload
In one terminal run:
```
gradle compileKotlin --continuous --parallel --build-cache --configuration-cache
```
In second terminal run:
```
gradle bootRun
```

## Code Formatter
In the plugin{} blocks in your Gradle file:
```
plugins {
    id("com.ncorti.ktfmt.gradle") version "<latest_version>"
    }
```
To enable different styles you can simply:
```
ktfmt {
    // Google style - 2 space indentation & automatically adds/removes trailing commas
    googleStyle()
    
    // KotlinLang style - 4 space indentation - From kotlinlang.org/docs/coding-conventions.html
    kotlinLangStyle()
}
```
and run:
```
gradle ktfmtFormat
```
## Test
Run all test
```
gradle test
```
Run specific test
```
gradle test --tests "dev.joguenco.hola.HolaTests.test ping"
```
## Kover
Is a set of solutions for collecting test coverage of Kotlin code compiled for JVM and Android platforms.
In the plugin{} blocks in your Gradle file:
```
plugins {
	id("org.jetbrains.kotlinx.kover") version "<latest_version>"
    }
```
and run:
```
gradle koverHtmlReport
```