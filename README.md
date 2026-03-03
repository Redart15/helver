[![GitHub release](https://img.shields.io/github/v/release/Redart15/helver?color=%233fa33f&cacheSeconds=1)](https://github.com/Redart15/helver/releases)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/w/Redart15/helver?color=%233fa33f&cacheSeconds=1)](https://github.com/Redart15/helver/graphs/code-frequency)
[![GitHub last commit](https://img.shields.io/github/last-commit/Redart15/helver?color=%233fa33f&cacheSeconds=1)](https://github.com/Redart15/helver/commits)
[![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/Redart15/helver/total?color=%233fa33f&cacheSeconds=1)](https://tooomm.github.io/github-release-stats/?username=Redart15&repository=helver)
# Helver Mod

Helper libabry containing function for common use cases in Redart15 mods.

## Prerequites
- JDK for Java 17 ([Eclipse Temurin](https://adoptium.net/temurin/releases/) recommended)

# How to include Helver in a project
Add this in your `build.gradle`:
```java
repositories {
   maven { url = "https://jitpack.io" }
}

dependencies {
    modImplementation "com.github.Redart15:helver:1.0.0"
}
```
