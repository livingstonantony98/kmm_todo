# KMMTodos

KMMTodos is a cross-platform Todo application built using Kotlin Multiplatform. It targets both Android and iOS, sharing business logic and UI code where possible to maximize code reuse and maintainability.

## Project Structure

- **/composeApp**  
  Shared code for Compose Multiplatform applications.
  - `commonMain`: Code common to all platforms (business logic, models, etc.).
  - `androidMain`, `iosMain`: Platform-specific implementations (e.g., Android or iOS APIs).

- **/iosApp**  
  Contains the iOS application entry point and SwiftUI code. This is required even if most UI is shared via Compose Multiplatform.

## Class/File Tree Structure

```
composeApp/
  src/
    androidMain/
      kotlin/com/example/kmmtodos/
        MainActivity.kt
        MyApplication.kt
        Platform.android.kt
        di/Modules.android.kt
    commonMain/
      kotlin/com/example/kmmtodos/
        App.kt
        Greeting.kt
        Platform.kt
        data/
          HttpClientExt.kt
          HttpClientFactory.kt
          model/
            Todo.kt
            TodoDTO.kt
        domain/
          Result.kt
        networking/
          CensoredText.kt
          KtorRemoteBookDataSource.kt
          RemoteBookDataSource.kt
          createHttpClient.kt
    iosMain/
      kotlin/com/example/kmmtodos/
        MainViewController.kt
        Platform.ios.kt
        di/Modules.ios.kt
    commonTest/
      kotlin/com/example/kmmtodos/
        ComposeAppCommonTest.kt
```

## Features

- Add, edit, and delete todo items.
- Shared business logic across Android and iOS.
- Compose Multiplatform UI for a consistent look and feel.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio or IntelliJ IDEA.
3. For Android: Run the `composeApp` target.
4. For iOS: Open `iosApp/iosApp.xcodeproj` in Xcode and run on a simulator or device.

## Learn More

- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)