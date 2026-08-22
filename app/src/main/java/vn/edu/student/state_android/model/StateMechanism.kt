package vn.edu.student.state_android.model

enum class StateMechanism(val label: String) {
    REMEMBER("remember"),
    REMEMBER_SAVEABLE("rememberSaveable"),
    VIEW_MODEL("ViewModel"),
    SAVED_STATE_HANDLE("SavedStateHandle"),
    DATA_STORE("DataStore")
}