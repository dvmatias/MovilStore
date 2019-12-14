package com.matias.features.login.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matias.features.R

class SignInFragment : Fragment() {
    
    private var listener: OnFragmentInteractionListener? = null
    
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sign_in, container, false)
    
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
    
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
    
    companion object {
        @JvmStatic
        fun newInstance(): SignInFragment = SignInFragment()
    }
    
}
