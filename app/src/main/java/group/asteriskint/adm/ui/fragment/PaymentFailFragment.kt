package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import group.asteriskint.adm.R
import kotlinx.android.synthetic.main.payment_fail_fragment.*

class PaymentFailFragment : Fragment() {

    private val viewModel: PaymentFailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_fail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        retry_payment.setOnClickListener {
            navController.popBackStack()
        }
    }

}