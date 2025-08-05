# 🪙 MercadoBitcoinApp - CoinMarketCap Viewer App

CoinTrack is an Android app that allows users to browse cryptocurrency market data, including exchange details, volume, and rankings, powered by the [CoinAPI](https://www.coinapi.io/) public API.

---

## 📱 Features

- 🔍 View list of crypto exchanges with key stats
- 📊 Explore detailed exchange info (rank, volume, website)
- 🌐 Real-time data fetched from CoinAPI
- 🔄 Retry on network errors
- 🧪 MVVM architecture with proper separation of concerns

---

## 🛠️ Tech Stack

| Layer        | Technology                          |
|--------------|--------------------------------------|
| Language     | Kotlin                               |
| Architecture | MVVM + Repository Pattern            |
| Networking   | Retrofit, OkHttp                     |
| UI           | Jetpack Compose                      |
| Async        | Kotlin Coroutines, Flow              |
| Storage      | EncryptedSharedPreferences (optional for API key) |
| DI (optional)| Hilt / Koin                          |

---

## 📦 Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/cointrack.git

## ✅ Testing

The `exchanges_presentation` module includes both **unit** and **UI tests** to ensure reliability and correctness of the presentation layer.

### 🔍 Unit Tests

- Located in:  
  `exchanges_presentation/src/test/`
- Covers:
  - ViewModel logic
  - State management
  - Use case interaction

### 🧪 UI Tests

- Located in:  
  `exchanges_presentation/src/androidTest/`
- Covers:
  - Jetpack Compose screens
  - Interaction flows
  - Visibility of loading/error/success states

### 📦 Run All Tests

From the project root:

```bash
./gradlew testDebugUnitTest       # Run unit tests
./gradlew connectedAndroidTest    # Run UI tests
