# Architecture

The CWS Design System ships inside a **production-grade sample app** that demonstrates
**feature-based modularization + Clean Architecture** with **Hilt** DI, a mocked data layer, and
Navigation Compose.

Each feature is a self-contained Gradle module that owns its UI and ViewModels and depends only on
the shared *core* modules — never on another feature. Within a feature, layering follows
`presentation → domain ← data`.

## Layered architecture

What lives in each layer. Dependencies point **downward** toward `:domain` — the pure-Kotlin core
that knows nothing about Android, UI, or data sources. (See [Login flow](#login-flow-request-lifecycle)
below for how a call actually moves through these layers.)

```mermaid
flowchart TB
    PRES["<br/><br/>🎨 &nbsp; <b>Presentation Layer</b> &nbsp; · &nbsp; :app · :feature:*<br/><br/><small>Screens (Compose) &nbsp;·&nbsp; ViewModels (StateFlow&lt;UiState&gt;)</small><br/><small>Navigation &nbsp;·&nbsp; Design System (:core)</small><br/><br/>"]
    DOM["<br/><br/>🧠 &nbsp; <b>Domain Layer</b> &nbsp; · &nbsp; :domain &nbsp;<i>(pure Kotlin)</i><br/><br/><small>Use Cases &nbsp;·&nbsp; Models &nbsp;·&nbsp; Repository Interfaces</small><br/><small>Validators &nbsp;·&nbsp; AppError / Result&lt;T&gt;</small><br/><br/>"]
    DATA["<br/><br/>💾 &nbsp; <b>Data Layer</b> &nbsp; · &nbsp; :data<br/><br/><small>Repository Impls &nbsp;·&nbsp; Mock APIs &nbsp;·&nbsp; DTOs / mappers</small><br/><small>Bundled JSON (destinations · users)</small><br/><br/>"]

    PRES -- "depends on" --> DOM
    DATA -- "implements" --> DOM

    classDef presentation fill:#6FC3AD,stroke:#4FA98E,color:#FFFFFF;
    classDef domain fill:#6E6E6E,stroke:#555555,color:#FFFFFF;
    classDef data fill:#F98E7E,stroke:#E07565,color:#FFFFFF;

    class PRES presentation;
    class DOM domain;
    class DATA data;
```

!!! note "Background & services"
    WorkManager and a core Service aren't part of the app yet. When added, they'd sit alongside the
    Data layer (scheduling work, then calling Use Cases) — a natural extension point for later.

## Login flow (request lifecycle)

How a single `login()` call travels **outward** to the data sources and how data flows **back** to
the UI — the runtime counterpart to the static layers above.

```mermaid
flowchart LR
    subgraph PRES["Presentation Layer"]
        direction TB
        LV["<br/>LoginView /<br/>LoginScreen<br/><small>Presentation</small><br/>"]
        VM["<br/>ViewModel<br/>&nbsp;<br/><small>Presentation Logic</small><br/>"]
    end

    subgraph DOM["Domain Layer"]
        UC["<br/>Login<br/>Use Case<br/><small>Business Logic &amp; Data</small><br/>"]
    end

    subgraph DATA["Data Layer"]
        REPO["<br/>Repository<br/><small>Decides which</small><br/><small>data source to use</small><br/>"]
    end

    subgraph SVC["Service Layer"]
        direction TB
        DB["<br/>&nbsp;<br/>Database<br/>&nbsp;<br/>"]
        NET["<br/>&nbsp;<br/>Network<br/>&nbsp;<br/>"]
    end

    %% forward: the login() request travels outward
    LV -. "login()" .-> VM
    VM -. "login()" .-> UC
    UC -. "login()" .-> REPO
    REPO -. "login()" .-> DB
    REPO -. "login()" .-> NET

    %% return: data flows back inward
    DB -. "Data" .-> REPO
    NET -. "Data" .-> REPO
    REPO -. "Data" .-> UC
    UC -. "Data" .-> VM

    %% UI reacts to state
    VM == "Show error ·<br/>Show loading indicator" ==> LV

    classDef view fill:#6FC3AD,stroke:#4FA98E,color:#FFFFFF;
    classDef vm fill:#E8453C,stroke:#C5392F,color:#FFFFFF;
    classDef usecase fill:#6E6E6E,stroke:#555,color:#FFFFFF;
    classDef repo fill:#F98E7E,stroke:#E07565,color:#FFFFFF;
    classDef service fill:#18C39A,stroke:#13A082,color:#FFFFFF;

    class LV view;
    class VM vm;
    class UC usecase;
    class REPO repo;
    class DB,NET service;
```

- **`login()` forward** — UI → ViewModel → Use Case → Repository → data source. Each layer only
  knows the *next inward* abstraction, never the concrete implementation.
- **`Data` back** — the source returns data the same way in reverse; the Repository decides whether
  it came from cache (Database) or the wire (Network).
- **UI reacts** — the ViewModel maps the result into `LoginUiState` (loading / error / success);
  the View just renders state.

!!! note "In this codebase"
    Screens are **Compose** (`LoginScreen`), not Fragments, and the "Network" source is a
    **mock API over bundled JSON**. The flow and layer boundaries are otherwise identical.

## Module dependency graph

How the Gradle modules wire together at build time (a complement to the runtime layers above).

```mermaid
flowchart LR
    app[":app — Composition root"]
    auth[":feature:auth — Login & signup"]
    home[":feature:home — Destinations"]
    core[":core — Compose design system"]
    domain[":domain — Models, use cases, repo interfaces"]
    data[":data — Repo impls, Hilt modules, mock APIs"]
    mock[":data resources — Mock JSON"]
    lint[":lint-rules — Custom a11y checks"]
    buildlogic[":buildlogic — Gradle convention plugins"]
    docs["docs-site — Component documentation"]

    app -->|"hosts navigation graphs"| auth
    app -->|"hosts navigation graphs"| home
    app -->|"installs data bindings"| data
    app -->|"applies theme & components"| core
    app -->|"uses domain models"| domain

    auth -->|"uses UI components"| core
    auth -->|"calls use cases"| domain
    home -->|"uses UI components"| core
    home -->|"calls use cases"| domain

    data -->|"implements interfaces"| domain
    data -->|"reads bundled JSON"| mock

    core -->|"publishes lint checks"| lint
    docs -->|"documents components & theming"| core

    buildlogic -.->|"configures"| app
    buildlogic -.->|"configures"| auth
    buildlogic -.->|"configures"| home
    buildlogic -.->|"configures"| core
    buildlogic -.->|"configures"| data
    buildlogic -.->|"configures"| domain
    buildlogic -.->|"configures"| lint
```

## Module types

| Type | Modules | Responsibility |
|---|---|---|
| **App** | `:app` | Composition root — navigation host, theme switching, Hilt setup |
| **Feature** | `:feature:auth`, `:feature:home` | Vertical slices: screens + `@HiltViewModel`s + nav graph |
| **Core / shared** | `:core`, `:domain`, `:data` | Reusable layers shared across features |
| **Tooling** | `:lint-rules`, `:buildlogic` | Custom lint checks · Gradle convention plugins |

- **`:core`** — the design system (Compose components, theme, tokens).
- **`:domain`** — pure Kotlin: models, repository interfaces, use cases, validators (no Android).
- **`:data`** — repository implementations, mock JSON-backed APIs, Hilt modules.

## Dependency rules

```
:app ──▶ :feature:auth ─┐
     ──▶ :feature:home ─┼──▶ :core    (design system)
     ──▶ :data          └──▶ :domain  (models + use cases)
            └──────────────▶ :domain
```

- A feature depends on `:core` + `:domain` only — **never** on `:data` or on another feature.
- Only `:app` depends on `:data` (so Hilt can register its modules); features receive repository
  *interfaces* by injection.
- `:domain` is pure Kotlin and **annotation-free** — Hilt lives only in the Android layers.
- Common build config lives in `:buildlogic` convention plugins, so each module's build file is
  ~10 lines (`cws.android.feature`, `cws.android.data`, `cws.kotlin.library`, …).

## Principles

- **Clean Architecture** — `presentation → domain ← data`. `:domain` is pure Kotlin and
  annotation-free; Hilt lives only in the Android layers.
- **MVVM + UDF** — ViewModels expose immutable `StateFlow<UiState>`; screens are stateless and
  driven by state + callbacks; one-shot events flow through a `Channel`.
- **DI with Hilt** — interfaces are bound in `:data`; ViewModels are `@HiltViewModel`.
- **Mocked network** — `:data` deserializes bundled JSON "responses" (`mock/destinations.json`,
  `mock/users.json`) with `kotlinx.serialization`, with simulated latency for real loading states.
- **Result type** — layers exchange `kotlin.Result<T>`; a typed `AppError` rides inside an
  `AppException` (see `appErrorOrNull()` / `appFailure()`).

---

*See the [README](https://github.com/ersandip94/android-design-system#architecture) for the full
write-up, or [Get started →](getting-started.md) using the design system.*
