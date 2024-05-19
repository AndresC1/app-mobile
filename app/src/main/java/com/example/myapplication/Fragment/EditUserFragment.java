package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MyApp;
import com.example.myapplication.R;
import com.example.myapplication.Repository.UserDB;
import com.example.myapplication.ViewModel.ValidateModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUserFragment newInstance(String param1, String param2) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        view.findViewById(R.id.btnUpdatePassword).setOnClickListener(v -> {
            TextView newPassword = view.findViewById(R.id.txtNewPassword);
            TextView confirmPassword = view.findViewById(R.id.txtConfirmPassword);
            MyApp app = (MyApp) getActivity().getApplication();
            // validate new password
            String validateChangePassword = app.getUser().validateChangePassword(newPassword.getText().toString(), confirmPassword.getText().toString());
            if(validateChangePassword != null){
                Toast.makeText(getContext(), validateChangePassword, Toast.LENGTH_SHORT).show();
            } else {
                UserDB userDB = new UserDB(getContext());
                int idUser = app.getUser().getId();
                userDB.changePassword(idUser, newPassword.getText().toString());
                Toast.makeText(getContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                // logout
                app.setUser(null);
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return view;
    }
}