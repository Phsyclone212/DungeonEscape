## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## ChatGPT's advice

Great! Here’s a **structured project path** for building your **Dungeon Escape** game in Java. This will help you implement features step by step without feeling lost.

---

## **📜 Dungeon Escape - Project Path**
### **🟢 Phase 1: Core Structure (Setup & Classes)**
1. **Create the `Main` class**  
   - Handles game startup & loop.  
2. **Create the `Player` class**  
   - Attributes: `health`, `position`, `inventory`.  
   - Methods: `move()`, `takeDamage()`, `pickUpItem()`.  
3. **Create the `Room` class**  
   - Attributes: `description`, `items`, `hasMonster`.  
   - Methods: `getDescription()`, `generateRoom()`.  
4. **Create the `GameMap` class**  
   - Holds all rooms in a grid.  
   - Method: `getRoom(x, y)` to retrieve a location.

---

### **🟡 Phase 2: Movement & Interactions**
5. **Implement `move()` in `Player`**  
   - Allow moving North/South/East/West.  
   - Prevent movement outside the map.  
6. **Generate randomized rooms**  
   - Each room has a description & possible events.  
7. **Add basic user input**  
   - Let the player choose where to move.

---

### **🟠 Phase 3: Events & Game Mechanics**
8. **Create a `Monster` class**  
   - Random chance for a monster in a room.  
   - Basic combat: `attack()` and `takeDamage()`. 
9. **Create an `Item` class**  
   - Types: Health potions, weapons, etc.  
   - Allow picking up and using items.  
10. **Implement escape conditions**  
   - Winning condition: Find the exit room.  
   - Losing condition: Health reaches 0.

---

### **🔴 Phase 4: Polish & Expand (Optional)**
11. **Improve combat**  
   - Add weapons with different damage values.  
12. **Implement a turn-based system**  
   - Limit actions per turn (move, attack, heal).  
13. **Add a small storyline**  
   - Random events with choices.  
14. **Save/load game progress**  
   - Write player data to a file.

---

Would you like to start **Phase 1 now**, or do you want adjustments to the project scope? 😊