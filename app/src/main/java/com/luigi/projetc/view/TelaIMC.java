package com.luigi.projetc.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luigi.projetc.R;
import com.luigi.projetc.database.RightFitDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TelaIMC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TelaIMC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TelaIMC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TelaIMC.
     */
    // TODO: Rename and change types and number of parameters
    public static TelaIMC newInstance(String param1, String param2) {
        TelaIMC fragment = new TelaIMC();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RightFitDatabase.getDatabase(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new Date());

        RightFitDatabase.getDatabase(getContext()).alimentoDao().getAllAlimentos().observe(this, alimentoEntities -> {
            Log.e("Alimentos",alimentoEntities.toString());
        });

        RightFitDatabase.getDatabase(getContext()).dietaDao().getDietasPorUsuarioEData("3").observe(this, dietas -> {
            Log.e("Dietas", dietas.toString());
        });

        RightFitDatabase.getDatabase(getContext()).dietaDao().getCaloriasIngeridasPorData("3", date).observe(this, dieta -> {
            Log.e("Carboidratos", dieta == null ? "Nada" : dieta.toString());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_imc, container, false);
    }
}