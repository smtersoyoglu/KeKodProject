# Kekod Project

This Android application demonstrates dynamic UI interaction using `SwitchCompat` components and navigation between multiple fragments. The project follows the MVVM (Model-View-ViewModel) architecture and is built using XML layouts, `ViewBinding`, `LiveData`, and `Glide` for image loading.

## Features

### Dynamic Switches
- **Ego Switch**: Controls the state of the other switches:
  - When `Ego` is **ON**, the other switches are disabled.
  - When `Ego` is **OFF**, the other switches can be toggled on or off.
  - Only up to four switches can be active at the same time.

### Animation Updates
- **Rive Animation**: A dynamic Rive animation is displayed based on the state of the `Ego` switch.
- **Lottie Animations**:
  - **Sad Animation**: Plays when the `Ego` switch is **ON**.
  - **Smile Animation**: Plays when the `Ego` switch is **OFF**.

### Splash Screen
- A splash screen with a countdown timer has been added, enhancing the user experience upon app launch.
- The splash screen includes an animation for visual appeal.

### Bottom Navigation
- A `BottomNavigationView` dynamically updates based on the state of the switches:
  - The first item is always the "Home" item.
  - Up to four additional items are displayed based on active switches.

### GIF Display
- Each fragment displays a relevant GIF using the `Glide` library.

## Project Structure

- **DashboardFragment**: Contains the switches and manages their interactions.
- **HappinessFragment**: Displays a GIF related to happiness.
- **OptimismFragment**: Displays a GIF related to optimism.
- **KindnessFragment**: Displays a GIF related to kindness.
- **GivingFragment**: Displays multiple GIFs related to giving, with a button to toggle visibility.
- **RespectFragment**: Displays a GIF related to respect.

## ViewModel
- `DashboardViewModel` manages the states of all switches and controls the dynamic updates to the `BottomNavigationView`.

## DataBinding
- Using `DataBinding`, `ViewModels` are linked to XML layout files, allowing UI components to synchronize with the data.

## Navigation
- The application uses the `Jetpack Navigation Component` to handle fragment transactions, with `nav_graph.xml` defining the navigation graph.

## Technologies Used
- **XML Layouts**: For designing the user interface.
- **ViewBinding**: For efficient view lookups and null safety.
- **DataBinding**: Simplifies the code by binding components and data with XML.
- **LiveData**: Observes and updates UI components.
- **Glide**: Efficient image loading, including GIFs.
- **Rive**: For smooth and scalable vector animations.
- **Lottie**: For lightweight, interactive animations.
- **MVVM Architecture**: Ensures a clear separation of concerns for better maintainability.
- **Material Components**: Provides a modern and consistent UI design.

## Recent Updates
- **Added** a splash screen with a countdown timer.
- **Updated** switch design and transitions with Rive animations.
- **Lottie Animations**: Sad (`Ego ON`) and Smile (`Ego OFF`).
- **Updated** Lottie animations for switch transitions.
- **Fixed** the state management of Rive and Lottie animations in `onResume` to maintain correct states after navigation.

### Project Video

https://github.com/user-attachments/assets/38bc123e-f625-4eb3-8375-a86c67e14cc6


### Screenshoots

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/b5b19103-0927-4b9c-9365-dd25633f4c2d" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/dac35512-cee4-49f5-bc7a-e160d746f08e" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/92a1a68b-232d-45ca-ab29-d612f3cee965" width="290"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/eb888e95-809e-46d4-abea-f2b8e7af5c7d" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/739b80d5-475d-4690-ae81-fdf0f586a669" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/4989cb9a-ceaf-4e09-a336-91fa8339309e" width="290"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/73dbe0e9-4eff-413f-8953-177fed820dc3" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/bb36c6c3-c977-46e4-a151-a24db2e508c9" width="290"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/fb29b441-3b22-4c09-9ce6-d4243009fc06" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/0d7949b6-4aea-48ec-ac23-e4e48258ecc7" width="290"></td>
  </tr>
</table>






