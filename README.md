# User Directory App

An Android Studio application to Display a list of users from an API, with offline support.

The app loads user data from Room Database and updates UI using Flow from ViewModel.
On launch it fetches fresh API data with Retrofit and replaces Room cache using an offline-first pattern.

---

## Table of Contents

1. [Overview](#-Concepts)  
2. [Getting Started](#-getting-started)   
3. [Screenshots](#-screenshots)  
4. [License](#-license)  

---

### Overview

| Tool                     | Purpose             |
| ------------------------ | ------------------- |
| **Kotlin**               | Main language       |
| **Retrofit**             | Network calls       |
| **Room**                 | Local caching       |
| **Coroutines + Flow**    | Async + reactive UI |
| **ViewModel**            | UI state holder     |
| **RecyclerView**         | Display list        |
| **Navigation Component** | Screen navigation   |


---

#### Getting Started

 Prerequisites:

- Clone repo
- Open Android Studio 
- Run emulator (or real device)
- Ensure internet is ON on first launch
- In Airplain Mode, close app then reopen app to fetch cached data 

---

##### Screenshots

- ![img_1.png](C:\Users\dmald\AndroidStudioProjects\TODOmini\img_1.png)
- 
---

###### Core Features 

** 1. Fetch Users from API (GET only) **

    Use Retrofit to GET users from the public API
    API endpoint: https://jsonplaceholder.typicode.com/users
    Returns 10 users with id, name, username, email, phone, website

** 2. Store Users in Local Database (Room) **

    Create a Room Database with User entity: Defines a UserEntity, UserDao, and AppDatabase.
    Save fetched users to the local database
    Use @Insert(onConflict = OnConflictStrategy.REPLACE) to update existing users

** 3. Display from Room Database (Single Source of Truth) **

    UI always reads from the Room Database (using Flow)
    Never display API data directly to UI
    RecycleView displays: id, name, email, and phone number
    Repository handles API <-> Room logic

** 4. Offline-First Pattern **

    When the app opens:
        Step 1: Display data from Room immediately (even if old/empty)
        Step 2: Try to fetch fresh data from API
        Step 3: If successful, update Room Database
        Step 4: UI auto-updates via Flow
        Step 5: If API fails, user still sees cached data

** 5. Search Functionality (Local Search) **

    Search bar (at the top) filters users by name or email
    Search happens in Room Database (using a custom SQL Query() in the DAO)
    No API call needed for search
    Uses Flow, debounce(), and flatMapLatest() to update results

 
