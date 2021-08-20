package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import group.asteriskint.adm.R
import group.asteriskint.adm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle


    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)
        login.setOnClickListener {
            val username = login_email.text.toString()
            val password = login_password.text.toString()
            login(username,password)
        }
        create_account.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }
    private fun login(username: String, password:String) {
        userViewModel.login(username, password).observe(viewLifecycleOwner, { result ->
            if(result) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                findNavController().popBackStack()
            }else {
                showErrorMessage()
            }
        })
    }
    private fun showErrorMessage() {
        //DISPLAY A error message
    }

}