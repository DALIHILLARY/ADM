package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import group.asteriskint.adm.R
import group.asteriskint.adm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.payment_fragment.*

class PaymentFragment : Fragment() {

    private val userViewModel : UserViewModel by activityViewModels()
    private val viewModel : PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navController = findNavController()
//
//        val currentBackStackEntry = navController.currentBackStackEntry!!
//        val savedStateHandle = currentBackStackEntry.savedStateHandle
//        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
//            .observe(currentBackStackEntry, { success ->
//                if(!success) {
//                    val startDestination = navController.graph.startDestination
//                    val navOptions = NavOptions.Builder()
//                        .setPopUpTo(startDestination, true)
//                        .build()
//                    navController.navigate(startDestination,null,navOptions)
//                }
//            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
//        userViewModel.user.observe(viewLifecycleOwner, { user ->
//            if(user != null) {
//                //do nothing
//            }else{
//                navController.navigate(R.id.loginFragment)
//            }
//        })
        card_payment.setOnClickListener {
            viewModel.makePayment()
        }
        viewModel.paymentStatus.observe(viewLifecycleOwner, { result ->
            if(result.isSuccess) {
                navController.navigate(R.id.afterPaymentFragment)
            }
            if(result.isFailure) {
                navController.navigate(R.id.paymentFailFragment)
            }
        })
    }

}