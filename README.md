# Electronic Store

**Author:** Vishrutha Gopa

This project implements an electronic store application following the Model-View-Controller (MVC) paradigm using JavaFX. The application allows users to interact with an electronic store, add products to the cart, complete sales, and view sales statistics.

## Project Structure

- `ElectronicStoreApp.java`: The main class extending `Application` to launch the JavaFX application.
- `ElectronicStore.java`: The model class representing the electronic store.
- `ElectronicStoreView.java`: The view class responsible for the graphical user interface.
- Other model classes: Additional classes representing products, sales, and other entities.

## Testing Instructions

1. Run the `ElectronicStoreApp` class to start the application.
2. Interact with the graphical user interface to perform various actions, including adding products to the cart, completing sales, and viewing sales statistics.

## Features

- **Add to Cart**: Users can add products to the shopping cart from the store stock.
- **Remove from Cart**: Users can remove products from the shopping cart.
- **Complete Sale**: Users can complete a sale, updating sales statistics and resetting the cart.
- **View Sales Statistics**: The application displays the number of sales, total revenue, and average revenue per sale.
- **Most Popular Items**: The most popular items list view shows the top 3 sold products.

## Implementation Details

- The project follows the MVC design pattern to maintain a clear separation between the model, view, and controller components.
- Event handling is implemented in the `ElectronicStoreApp` class to respond to user interactions and update the model and view accordingly.
- The GUI is designed with JavaFX components, including buttons, text fields, and list views.