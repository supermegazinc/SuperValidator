# ✅ SuperValidator

**SuperValidator** is a comprehensive validation engine for Android. It decouples validation logic from your UI, allowing you to define business rules once and observe validation states in real-time across your Backend, Reactive layers, and Jetpack Compose UI.

Built on top of Kotlin Coroutines and Flow, it ensures that your data is always valid before it ever reaches your database.

---

## ✨ Features

*   **🧩 Decoupled Logic**: Define validators as pure logic objects that can be reused anywhere.
*   **🔄 Reactive Validation**: Use `ValidatedInputReactive` to get real-time feedback as the user types.
*   **🎨 Compose Integration**: Seamless `.compose()` extension to handle `isError` states and property delegation in your TextFields.
*   **🛠 Dynamic Rules**: Update or add validators at runtime (e.g., changing password requirements dynamically).
*   **🏗 Type Safe**: Specialized support for `String`, `Int`, and custom types.

---

## 📦 Installation

Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.supermegazinc:SuperValidator:LATEST_VERSION")
}
```

---

## 🚀 How it Works

### 1. Define Your Validators
Start by defining your rules. SuperValidator comes with common validators like `EmptyField`, but you can easily create custom ones.

```kotlin
object MyValidators {
    val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
    val age: IntValidators = setOf(GreaterValidator(than = 18)).asInputValidators()
}

class GreaterValidator(private val than: Int) : IntValidator {
    override fun validate(input: Int): Boolean = input > than
}
```

### 2. Create a Reactive State
In your ViewModel or Data layer, wrap your inputs in a `Reactive` container. This tracks both the current value and whether it currently passes the validators.

```kotlin
data class UserForm(
    val name: ValidatedStringReactive = MyValidators.name.reactive(""),
    val age: ValidatedIntReactive = MyValidators.age.reactive(0)
)

// Check validity or render final data
val data = form.name.renderIfValid() ?: "Default Name"
```

### 3. Power Your Compose UI
The `.compose()` extension transforms your reactive state into a Compose-friendly object with delegation support.

```kotlin
@Composable
fun RegistrationScreen(form: UserForm) {
    // 1. Observe the global validity of the whole form
    val isFormValid by listOf(form.name, form.age)
        .isValidFlow()
        .collectAsStateWithLifecycle(false)

    // 2. Convert to Compose state
    val nameCompose = form.name.compose()
    var name by nameCompose.inputState

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            isError = nameCompose.failedValidator != null,
            label = { Text("Enter Name") }
        )

        Button(enabled = isFormValid, onClick = { /* Submit */ }) {
            Text("Register")
        }
    }
}
```

---

## 📂 Examples

The repository includes three common use-case examples:

| Module | Purpose |
| :--- | :--- |
| **`backend-form`** | Pure Kotlin validation logic without UI or Flow dependencies. |
| **`reactive-form`** | Using `ValidatedInputReactive` to modify values and rules at runtime. |
| **`compose-form`** | Full implementation with Jetpack Compose, showing error states and button enabling. |

---

## 🤝 Contributing

1. **Fork** the repository.
2. Create your **Feature Branch** (`git checkout -b feature/NewValidator`).
3. **Commit** your changes.
4. **Push** to the branch and open a **Pull Request**.

---

## 📄 License

Distributed under the **MIT License**.

Developed with ❤️ by **Supermegazinc**