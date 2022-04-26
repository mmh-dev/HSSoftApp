# Demo app, which  works with GraphQL server, written according to Clean Architecture an MVVM pattern.

An application that shows a list of countries and detailed information about the selected country in
separate window. The data is obtained from GraphQL server, but stored on local database and can
be accessed offline

## Used libraries and features

### Android Jetpack

* [ViewBinding](https://developer.android.com/topic/libraries/view-binding) View binding is a
  feature that makes it easier for you to write code that interacts with views.

* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) An interface
  that automatically responds to lifecycle events.

* [Kotlin flows](https://developer.android.com/kotlin/flow) In coroutines, a flow is a type that can
  emit multiple values sequentially, as opposed to suspend functions that return only a single
  value. For example, you can use a flow to receive live updates from a database.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Data related to
  the user interface that is not destroyed when the application is rotated. Easily schedule
  asynchronous tasks for optimal execution.

* [Room](https://developer.android.com/training/data-storage/room) The Room persistence library
  provides an abstraction layer over SQLite to allow fluent database access while harnessing the
  full power of SQLite

### DI

* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Dagger Hilt is a
  dependency injection library for Android that reduces the execution time of manual dependency
  injection into your project. Performing manual dependency injection requires that you create each
  class and its dependencies manually, and use containers to reuse and manage dependencies.

### HTTP

* [Retrofit2](https://github.com/square/retrofit) Type-safe HTTP client for Android and Java.

### Coroutines

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) A coroutine is a concurrency 
  design pattern that you can use on Android to simplify code that executes asynchronously.

## Author
This project is maintained by:
* [Murod Khodzhaev](https://github.com/mmh-dev)