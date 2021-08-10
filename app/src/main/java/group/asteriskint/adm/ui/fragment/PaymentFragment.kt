package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import group.asteriskint.adm.R
import group.asteriskint.adm.viewmodel.UserViewModel

class PaymentFragment : Fragment() {

    private val userViewModel : UserViewModel by activityViewModels()

    companion object {
        fun newInstance() = PaymentFragment()
    }

    private lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        userViewModel.user.observe(viewLifecycleOwner, { user ->
            if(user != null) {
                //do nothing
            }else{
                navController.navigate(R.id.loginFragment)
            }
        })
    }

}