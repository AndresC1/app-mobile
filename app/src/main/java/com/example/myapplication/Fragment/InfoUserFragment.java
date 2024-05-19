package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.MyApp;
import com.example.myapplication.R;
import com.example.myapplication.Repository.NoteDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoUserFragment newInstance(String param1, String param2) {
        InfoUserFragment fragment = new InfoUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_info_user, container, false);
        TextView id = view.findViewById(R.id.showUserID);
        TextView email = view.findViewById(R.id.showUserEmail);
        TextView countNotes = view.findViewById(R.id.showUserCountNotes);

        NoteDB noteDB = new NoteDB(getContext());
        MyApp app = (MyApp) getActivity().getApplicationContext();

        id.setText(String.valueOf(app.getUser().getId()));
        email.setText(app.getUser().getEmail());
        countNotes.setText(String.valueOf(noteDB.getCountNotesByUser(app.getUser().getId())));
        return view;
    }
}