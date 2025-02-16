# MoviesApp

A modern Android application for browsing movies, powered by the TMDB API.

## Description

MoviesApp is a sleek and intuitive Android application that allows users to explore a vast library
of movies. Leveraging the power of the TMDB (The Movie Database) API, this app helps you discover
popular movies. Built with modern Android development
practices and technologies, MoviesApp offers a smooth and engaging user experience.

## Key Features

* **Movies list:**
    * Retrieve a list of movies sorted by predefined parameters.
    * Display movies with essential information like title, poster, budget, revenue, and rating.
* **Filter:** Quickly filter movies based on selected genre.
* **Responsive UI:** Enjoy a consistent and visually appealing experience thanks to Jetpack Compose.

## Technologies Used

* **Kotlin:** The primary programming language for building the app.
* **Jetpack Compose:** A modern UI toolkit for building native Android UIs declaratively.
* **Ktor:** A powerful and flexible HTTP client for making network requests to the TMDB API.
* **Hilt:** A dependency injection library for Android that reduces boilerplate and improves code
  maintainability.
* **Coil:** An image loading library for Android that is fast, efficient, and easy to use.
* **Hilt-Navigation-Compose:** A library that integrates Hilt with Jetpack Compose Navigation for
  seamless dependency injection in navigation components.
* **Material 3:** The latest version of Google's Material Design system, providing a modern and
  consistent look and feel.
* **TMDB API:** The Movie Database API, used to fetch movie data.

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

## Usage

1. Launch the app.
2. Filter by selected movie genres (e.g., Action, Comedy, Drama).
3. Tap on a movie to view its details (TBD - for now it's snackbar with movie title).
4. Tap on Filters button (FAB) to open filters screen.

## Known Issues

1. **No Modularization:** I had an issues during the project setup with modularization which takes
   me a lot of time. I will try to fix it in the future.
2. **No Unit Tests:** The project does not contain any unit tests yet. This will be addressed in
   future updates.
3. **No Movie Details:** The movie details screen is not yet implemented. Clicking on a movie will
   display a snackbar with the movie title instead.

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details. 
        
