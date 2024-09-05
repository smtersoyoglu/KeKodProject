# Kekod Project

This Android application demonstrates dynamic UI interaction using `SwitchCompat` components and navigation between multiple fragments. The project follows the MVVM (Model-View-ViewModel) architecture and is built using XML layouts, ViewBinding, LiveData, and Glide for image loading.

## Features

- **Dynamic Switches**: Six switches (`Ego`, `Happiness`, `Optimism`, `Kindness`, `Giving`, and `Respect`) with unique behaviors:
  - The `Ego` switch controls the state of the other switches.
  - When `Ego` is on, the other switches are disabled. When `Ego` is off, the other switches can be toggled on or off.
  - Only up to four switches can be active at the same time.
- **Bottom Navigation**: A `BottomNavigationView` dynamically updates based on the state of the switches. The first item is always the "Home" item, and up to four additional items can be displayed.
- **GIF Display**: Each fragment displays a GIF using the Glide library.

## Project Structure

The application consists of several fragments, each representing different states or themes:

1. **DashboardFragment**: Contains the switches and manages their interactions.
2. **HappinessFragment**: Displays a GIF related to happiness.
3. **OptimismFragment**: Displays a GIF related to optimism.
4. **KindnessFragment**: Displays a GIF related to kindness.
5. **GivingFragment**: Displays multiple GIFs related to giving, with a button to toggle visibility.
6. **RespectFragment**: Displays a GIF related to respect.

### ViewModel

`DashboardViewModel` manages the states of all switches and controls the dynamic updates to the `BottomNavigationView`.

## DataBinding

Using DataBinding, ViewModels are linked to XML layout files. In this way, UI components are synchronised with the data.

### Navigation

The application uses the Jetpack Navigation Component to handle fragment transactions. The `nav_graph.xml` file defines the navigation graph.

## Technologies Used

- **XML Layouts**: Used for designing the user interface.
- **ViewBinding**: Efficient view lookups and null safety.
- **DataBinding**: Simplifies the code by binding components and data with XML
- **LiveData**: Observes and updates UI components.
- **Glide**: Efficient image loading, including GIFs.
- **MVVM Architecture**: Clear separation of concerns for better maintainability.
- **Material Components**: For a modern and consistent UI design.



### Screenshoots

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/7dc00d79-2b6b-4a15-9e49-91a8d0062cc4" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/384cc2a1-5808-4758-8d4a-d3363bd67f12" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/716308ed-982b-4518-ad14-1a1a34f0d703" width="290"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/739b80d5-475d-4690-ae81-fdf0f586a669" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/4989cb9a-ceaf-4e09-a336-91fa8339309e" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/73dbe0e9-4eff-413f-8953-177fed820dc3" width="290"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/bb36c6c3-c977-46e4-a151-a24db2e508c9" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/a1fcb76d-a6e1-42bc-b046-0ae8d34427cf" width="290"></td>
    <td><img src="https://github.com/user-attachments/assets/0d7949b6-4aea-48ec-ac23-e4e48258ecc7" width="290"></td>
  </tr>
</table>


### Project Video

https://github.com/user-attachments/assets/0a3a1a20-d3f3-4424-bf18-174940f88edd


