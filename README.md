# objc2-foundation-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fobjc2-foundation-kotlin-blue.svg)](https://github.com/KotlinMania/objc2-foundation-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/objc2-foundation-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/objc2-foundation-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/objc2-foundation-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/objc2-foundation-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`objc2-foundation`](https://github.com/madsmtm/objc2).

**Original Project:** This port is based on [`objc2-foundation`](https://github.com/madsmtm/objc2). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `objc2-foundation`

> The text below is reproduced from [`https://github.com/madsmtm/objc2`](https://github.com/madsmtm/objc2). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs where appropriate so they continue to resolve from this repository.

# `objc2-foundation`

[![Latest version](https://badgen.net/crates/v/objc2-foundation)](https://crates.io/crates/objc2-foundation)
[![License](https://badgen.net/badge/license/MIT/blue)](../../LICENSE.md)
[![Documentation](https://docs.rs/objc2-foundation/badge.svg)](https://docs.rs/objc2-foundation/)
[![CI](https://github.com/madsmtm/objc2/actions/workflows/ci.yml/badge.svg)](https://github.com/madsmtm/objc2/actions/workflows/ci.yml)

Rust bindings to Apple's framework Foundation.

This README is kept intentionally small to consolidate the documentation, see
[the Rust docs](https://docs.rs/objc2-foundation/) for more details on this crate.

This crate is part of the [`objc2` project](https://github.com/madsmtm/objc2),
see that for related crates.

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:objc2-foundation-kotlin:0.1.0")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same license terms as the upstream [`objc2-foundation`](https://github.com/madsmtm/objc2). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the objc2-foundation authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`objc2-foundation`](https://github.com/madsmtm/objc2) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
