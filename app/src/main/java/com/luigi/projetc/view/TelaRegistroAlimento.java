package com.luigi.projetc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luigi.projetc.R;
import com.luigi.projetc.controller.RegistroAlimentoController;
import com.luigi.projetc.database.RightFitDatabase;
import com.luigi.projetc.database.entities.AlimentoEntity;
import com.luigi.projetc.database.enums.PeriodoEnum;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelaRegistroAlimento extends Fragment {

    private Button bt_entrar, bt_entrar2, bt_entrar3;
    private TextView textViewData;
    private RegistroAlimentoController registroAlimentoController;
    private ImageView imageViewDiaAnterior;
    private ImageView imageViewDiaPosterior;
    private TextView textViewCaloriasIngeridas;
    private TextView textViewRestantes;
    private EditText editTextMeta;
    private DietaAdapter dietaAdapterManha;
    private DietaAdapter dietaAdapterAlmoco;
    private DietaAdapter dietaAdapterJanta;
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    Handler uiThread = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_registro_alimento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view2, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view2, savedInstanceState);
        initComponents();
        setOnClickButtons();
        setObservables();
    }

    private void setObservables() {
        registroAlimentoController.dataAtualObservable().observe(getViewLifecycleOwner(), data -> {
            textViewData.setText(data);
        });
        imageViewDiaAnterior.setOnClickListener(view -> {
            registroAlimentoController.diaAnterior();
            getDadosCaloria();
            getAlimentosPorPeriodo();
        });
        imageViewDiaPosterior.setOnClickListener(view -> {
            registroAlimentoController.diaPosterior();
            getDadosCaloria();
            getAlimentosPorPeriodo();
        });

        editTextMeta.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                saveMeta(v.getText().toString());
                getDadosCaloria();

                // Fechando teclado
                MainActivity activity = (MainActivity) getActivity();
                activity.closeKeyboard();

                Snackbar.make(getView(), "Meta Atualizada!", Snackbar.LENGTH_SHORT).show();

                return true;
            }
            return false;
        });
    }

    @Override
    public void onResume() {
        //Buscando dados da tela
        getDadosCaloria();
        getAlimentosPorPeriodo();
        super.onResume();
    }

    private void saveMeta(String meta) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        Runnable backgroundRunnable = () -> {
            registroAlimentoController.saveMeta(Integer.parseInt(meta), userId);
        };
        mExecutor.execute(backgroundRunnable);
    }

    private void getDadosCaloria(){
        Runnable backgroundRunnable = () -> {
            Integer restantes = registroAlimentoController.getRestantes();
            Integer meta = registroAlimentoController.getMeta();
            Integer calorias = registroAlimentoController.getCalorias();

            uiThread.post(() -> {
                textViewCaloriasIngeridas.setText(calorias.toString());
                editTextMeta.setText(String.valueOf(meta));
                textViewRestantes.setText(restantes.toString());
            });
        };
        mExecutor.execute(backgroundRunnable);
    }

    private void getAlimentosPorPeriodo(){
        Runnable runnable = () -> {
            List<AlimentoEntity> cafeDaManha = registroAlimentoController.alimentosPorPeriodo(PeriodoEnum.CAFE_DA_MANHA);
            List<AlimentoEntity> janta = registroAlimentoController.alimentosPorPeriodo(PeriodoEnum.JANTA);
            List<AlimentoEntity> almoco = registroAlimentoController.alimentosPorPeriodo(PeriodoEnum.ALMOCO);
            uiThread.post(() -> {
                dietaAdapterManha.setAlimentos(cafeDaManha);
                dietaAdapterJanta.setAlimentos(janta);
                dietaAdapterAlmoco.setAlimentos(almoco);
            });
        };
        mExecutor.execute(runnable);
    }

    private void setOnClickButtons() {
        bt_entrar.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.CAFE_DA_MANHA.name());
            intent.putExtra("data", registroAlimentoController.dataAtualObservable().getValue());
            startActivity(intent);
        });
        bt_entrar2.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.ALMOCO.name());
            intent.putExtra("data", registroAlimentoController.dataAtualObservable().getValue());
            startActivity(intent);
        });
        bt_entrar3.setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), TelaSelecionarAlimento.class);
            intent.putExtra("periodo", PeriodoEnum.JANTA.name());
            intent.putExtra("data", registroAlimentoController.dataAtualObservable().getValue());
            startActivity(intent);
        });

        dietaAdapterManha.setOnClickItem(id -> {
            Intent intent = new Intent(getView().getContext(), TelaAdicionarAlimento.class);
            intent.putExtra("alimento_id", id);
            intent.putExtra("update", true);
            intent.putExtra("periodo", PeriodoEnum.CAFE_DA_MANHA.name());
            startActivity(intent);
        });

        dietaAdapterAlmoco.setOnClickItem(id -> {
            Intent intent = new Intent(getView().getContext(), TelaAdicionarAlimento.class);
            intent.putExtra("alimento_id", id);
            intent.putExtra("update", true);
            intent.putExtra("periodo", PeriodoEnum.ALMOCO.name());
            startActivity(intent);
        });

        dietaAdapterJanta.setOnClickItem(id -> {
            Intent intent = new Intent(getView().getContext(), TelaAdicionarAlimento.class);
            intent.putExtra("alimento_id", id);
            intent.putExtra("update", true);
            intent.putExtra("periodo", PeriodoEnum.JANTA.name());
            startActivity(intent);
        });
    }

    private void initComponents() {
        registroAlimentoController = new RegistroAlimentoController(
                RightFitDatabase.getDatabase(getView().getContext()).dietaDao(),
                RightFitDatabase.getDatabase(getView().getContext()).metaDao()
        );
        bt_entrar = getView().findViewById(R.id.bt_entrar);
        bt_entrar2 = getView().findViewById(R.id.bt_entrar2);
        bt_entrar3 = getView().findViewById(R.id.bt_entrar3);
        textViewData = getView().findViewById(R.id.textView_data);
        imageViewDiaAnterior = getView().findViewById(R.id.image_view_dia_anterior);
        imageViewDiaPosterior = getView().findViewById(R.id.image_view_dia_posterior);
        textViewCaloriasIngeridas = getView().findViewById(R.id.text_view_calorias_ingeridas);
        textViewRestantes = getView().findViewById(R.id.editText_calorias_restantes);
        editTextMeta = getView().findViewById(R.id.editText_meta);
        RecyclerView recyclerViewManha = getView().findViewById(R.id.rv_1);
        RecyclerView recyclerViewAlmoco = getView().findViewById(R.id.rv_2);
        RecyclerView recyclerViewJanta = getView().findViewById(R.id.rv_3);
        recyclerViewManha.setLayoutManager( new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewAlmoco.setLayoutManager( new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewJanta.setLayoutManager( new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false));
        dietaAdapterManha = new DietaAdapter();
        dietaAdapterAlmoco = new DietaAdapter();
        dietaAdapterJanta = new DietaAdapter();
        recyclerViewManha.setAdapter(dietaAdapterManha);
        recyclerViewAlmoco.setAdapter(dietaAdapterAlmoco);
        recyclerViewJanta.setAdapter(dietaAdapterJanta);
    }
}
