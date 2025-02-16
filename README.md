## 📱 Movies App – Built for Learning & Best Practices 🎬
This application was developed exclusively for testing purposes to demonstrate modern Android development best practices. It serves as a reference for clean, maintainable, and scalable Android applications, utilizing industry-standard tools, architecture patterns, and libraries.

## 🛠️ Key Objectives
* Showcase clean, efficient, and testable Android code.
* Implement modern architecture principles like MVVM, MVI and Clean Architecture.
* Apply Kotlin Coroutines, Flow, and Jetpack Components for robust asynchronous operations.
* Demonstrate integration with The Movie Database (TMDB) API to fetch and display movie data.

## 🔍 Features Demonstrated
* **Movie Discovery:** Fetch and display a list of movies using the TMDB API.
* **Network Operations:** Efficient data fetching with Ktor and OkHttp.
* **Dependency Injection:** Leverage Hilt for modular and testable code.
* **State Management:** Use ViewModel with StateFlow, Channel and Flow for UI state handling.
* **Modularization:** The project is structured using a modular architecture to improve code
  organization and maintainability.

## Key Features

* **Movies list:**
    * Retrieve a list of movies sorted by predefined parameters.
    * Display movies with essential information like title, poster, budget, revenue, and rating.
* **Filter:** Quickly filter movies based on selected genre.
* **Responsive UI:** Enjoy a consistent and visually appealing experience thanks to Jetpack Compose.

## Architecture

* **MVVM/MVI:** The project follows the MVVM (Model-View-ViewModel) architecture pattern with
  elements of MVI (Model-View-Intent) to separate concerns and improve testability.
* **Clean Architecture:** The project follows the principles of Clean Architecture to ensure
  separation of concerns and maintainability.
* **Modularization:** The project is structured using a modular architecture to improve code
  organization and maintainability.
    * Some modules have a contract and implementation modules. The main reason is the build time.
      When you change something in the implementation module, the modules that depend on contract
      module will not be recompiled.

## Technologies Used

* **Kotlin:** The primary programming language for building the app.
* **Jetpack Compose:** A modern UI toolkit for building native Android UIs declaratively.
* **Coroutines:** A powerful and flexible concurrency framework for simplifying code that executes
  asynchronously.
* **Ktor:** A powerful and flexible HTTP client for making network requests to the TMDB API.
* **Hilt:** A dependency injection library for Android that reduces boilerplate and improves code
  maintainability.
* **Coil:** An image loading library for Android that is fast, efficient, and easy to use.
* **Hilt-Navigation-Compose:** A library that integrates Hilt with Jetpack Compose Navigation for
  seamless dependency injection in navigation components.
* **Material 3:** The latest version of Google's Material Design system, providing a modern and
  consistent look and feel.
* **TMDB API:** The Movie Database API, used to fetch movie data.

## Build requirements

1. **Android Studio Ladybug Feature Drop | 2024.2.2**
2. **Java version 17.0.7**

## Setup and Installation

1. **Clone the Repository:** Use the following command to clone the project repository to your local
   machine:

   ```bash
   git clone git@github.com:wfranik/MoviesApp.git

2. **Open in Android Studio:** Open the cloned project in Android Studio.
3. **TMDB API Key:**
    * Create an account at [https://www.themoviedb.org/](https://www.themoviedb.org/) and obtain an
      API key.
    * Create a `local.properties` file in the root of your project.
    * Add your TMDB API key to the `local.properties` file:

      ```properties
      API_KEY_VALUE="YOUR_API_KEY" 


4. **Build and Run:** Build and run the project on an emulator or a physical device.

## Known Issues

1. **Unit Tests:** For now there is only one unit test implemented -
   LoadMoviesWithDetailsUseCaseTest.
2. **No Movie Details:** The movie details screen is not yet implemented. Clicking on a movie will
   display a snackbar with the movie title instead.

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details. 
        
