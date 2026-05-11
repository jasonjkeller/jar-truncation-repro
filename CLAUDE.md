# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a minimal Java reproduction project (`jar-truncation-repro`) that demonstrates JAR truncation issues with the New Relic Java agent. It uses Apache HttpClient5 with `@Trace` annotations to trigger class loading instrumented by New Relic.

## Build System

- **Gradle 9.0.0** with Kotlin DSL (`build.gradle.kts`)
- **Java** (no Kotlin source code)
- Group: `com.nr`, single module

## Commands

- Build: `./gradlew build`
- Run tests: `./gradlew test`
- Compile only: `./gradlew compileJava`
- Clean: `./gradlew clean`

## Dependencies

- `com.newrelic.agent.java:newrelic-api:9.2.0` — New Relic agent API for `@Trace` annotations
- `org.apache.httpcomponents.client5:httpclient5:5.3.1` — Apache HttpClient5 (the JAR being loaded/tested for truncation)
- JUnit 5 for testing

## Structure

Single source file at `src/main/java/com/nr/Main.java`. The `Main.loadJars()` method uses `Class.forName` to trigger class loading of `org.apache.hc.client5.http.utils.Base64`, simulating the scenario where the New Relic agent instruments JAR loading.