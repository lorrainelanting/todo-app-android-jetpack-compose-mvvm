# Memobly
Memobly is a memo/note-taking/todo app.

*Note*: This is an ongoing demo project.

## Architecture Pattern
- Clean MVVM (Model-View-ViewModel)

## Libraries:
- [Android's Jetpack Compose](https://developer.android.com/develop/ui/compose/documentation)
- [Kotlin's Coroutines](https://kotlinlang.org/docs/coroutines-basics.html)
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection
- Android's Room Database to save data locally.

## About the Clean MVVM (Model-View-ViewModel)
It aims to separate the user interface logic from the business logic of the application, making it easier to maintain, test, and evolve.

The project is divided into four layers:
- Core
- Data
- Domain
- Presentation

### Core
Where the app and dependency injection located.

### Data
It contains the API interfaces, databases, and repository implementation. 
It acts as an abstraction layer, shielding the data from direct interaction with the rest of the application.

### Domain
Model (entity of data), Repository interfaces, and Use Cases are included in this layer.

### Presentation
Responsible for displaying the user interface and interacting with the user. It is typically implemented using UI elements like controls, layouts, and widgets.
The view observes the ViewModel and updates its appearance accordingly.