
---

````markdown
# 📚 BookApp

A simple Android book catalogue app built in Kotlin. Browse books, view details, and enjoy a sleek UI.

---

## 🔎 Features

- Display a list of books with cover images, titles, authors, and ratings  
- View book details: full summary, author info, large cover image  
- Smooth navigation with back-stack support

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer  
- Android SDK Platform 31 (or highest supported version)  
- Kotlin 1.7+

### Setup

1. Clone the repo:

   ```bash
   git clone https://github.com/PareshDroid/BookApp.git
   cd BookApp
````

2. In Android Studio:
   File → **Open** → select the `BookApp` directory

3. Sync Gradle and run the app on emulator or a connected device.

---

## 📁 Project Structure

```
app/
├─ src/
│  ├─ main/
│     ├─ java/com/yourdomain/bookapp/  # Activities, Adapters, Models, etc.
│     ├─ res/
│        ├─ layout/                    # XML layouts
│        ├─ drawable/                  # Images, icons
│        ├─ values/                    # Strings, colors, styles
├─ build.gradle                       # Module-level Gradle config
└─ AndroidManifest.xml
```

**Key Packages**

* `model/` — data classes like `Book`
* `adapter/` — RecyclerView adapters for listing books
* `res/layout` — UI layouts: list screen, details screen, etc.

---

## 🧩 Usage

* Main screen shows a scrollable list of books.
* Tap any book to open the detail screen with full information.
* Press device's back button to return to the list.

---

## ✅ Testing

No unit tests are included currently.

---

## 🛠️ Contributing

Contributions, bug reports, and feature requests are welcome!

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/NewFeature`)
3. Commit your changes with clear messages (`git commit -m 'Add feature'`)
4. Push to your branch (`git push origin feature/NewFeature`)
5. Create a Pull Request

---

## ⚡ Contact

Created by **Paresh**


