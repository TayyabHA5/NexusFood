import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    private var hasLoadedInitialData = false

    init {
        loadUserNameOnce()
    }

    private fun loadUserNameOnce() {
        if (!hasLoadedInitialData) {
            hasLoadedInitialData = true
            val userId = auth.currentUser?.uid
            userId?.let {
                db.collection("users").document(it)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            _userName.value = document.getString("firstName")
                        } else {
                            _userName.value = "User data not found"
                        }
                    }
                    .addOnFailureListener {
                        _userName.value = "Error loading name"
                    }
            } ?: run {
                _userName.value = "Not logged in"
            }
        }
    }
}