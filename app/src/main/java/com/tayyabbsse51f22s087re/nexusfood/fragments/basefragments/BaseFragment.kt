import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tayyabbsse51f22s087re.nexusfood.LoadingUtils

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: error("Binding accessed outside of view lifecycle")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return _binding!!.root
    }
    protected fun showLoader(cancelable : Boolean  = false ){
        context?.let {
            LoadingUtils.show(it, cancelable)
        }
    }
    protected fun hideLoader(){
        LoadingUtils.hide()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        hideLoader()
    }
}