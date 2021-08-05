package com.nodoclic.cic.ui.home;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nodoclic.cic.HomeActivity;
import com.nodoclic.cic.R;
import com.nodoclic.cic.adapters.MenuAdapter;
import com.nodoclic.cic.clases.RsLogin;
import com.nodoclic.cic.clases.RsMenu;
import com.nodoclic.cic.databinding.FragmentHomeBinding;
import com.nodoclic.cic.servicios.Servicio;
import com.nodoclic.cic.util.CONST;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    //Intialize attributes
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private static final String TAG ="TAG_NFC";
    private Context context;
    protected MenuAdapter mAdapter_1;
    protected RecyclerView mRecyclerView_1;
    RecyclerView.LayoutManager mLayoutManager_1;
    protected TextView tvDesayuno;
    protected MenuAdapter mAdapter_2;
    protected RecyclerView mRecyclerView_2;
    RecyclerView.LayoutManager mLayoutManager_2;
    protected TextView tvAlmuerzo;
    protected MenuAdapter mAdapter_3;
    protected RecyclerView mRecyclerView_3;
    RecyclerView.LayoutManager mLayoutManager_3;
    protected TextView tvMerienda;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        initLista_1(root);
        initLista_2(root);
        initLista_3(root);

        final TextView textView = binding.textHome;
        tvDesayuno = (TextView) root.findViewById(R.id.tvDesayuno);
        tvAlmuerzo = (TextView) root.findViewById(R.id.tvAlmuerzo);
        tvMerienda = (TextView) root.findViewById(R.id.tvMerienda);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    List<RsMenu> menu = Servicio._menuDia(context);
                    List<RsMenu> menuDesayuno= new ArrayList<>();
                    List<RsMenu> menuAlmuerzo= new ArrayList<>();
                    List<RsMenu> menuMerienda= new ArrayList<>();
                    for (RsMenu item: menu){
                        if(item.getComida() == 1){
                            menuDesayuno.add(item);
                        }
                        if(item.getComida() == 2){
                            menuAlmuerzo.add(item);
                        }
                        if(item.getComida() == 3){
                            menuMerienda.add(item);
                        }
                    }
                    if(menuDesayuno.isEmpty()){
                        tvDesayuno.setVisibility(View.INVISIBLE);
                    }
                    if(menuAlmuerzo.isEmpty()){
                        tvAlmuerzo.setVisibility(View.INVISIBLE);
                    }
                    if(menuMerienda.isEmpty()){
                        tvMerienda.setVisibility(View.INVISIBLE);
                    }

                    mAdapter_1 = new MenuAdapter(menuDesayuno);
                    mRecyclerView_1.setAdapter(mAdapter_1);
                    mAdapter_2 = new MenuAdapter(menuAlmuerzo);
                    mRecyclerView_2.setAdapter(mAdapter_2);
                    mAdapter_3 = new MenuAdapter(menuMerienda);
                    mRecyclerView_3.setAdapter(mAdapter_3);




                }
            });
        } catch (Exception e) {
            System.err.println("tasks interrupted");
            Toast.makeText(context, "1:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void initLista_1(View root ){
        mRecyclerView_1 = (RecyclerView) root.findViewById(R.id.rv_menu_1);
        mRecyclerView_1.setHasFixedSize(true);
        mLayoutManager_1 = new LinearLayoutManager(getActivity());
        mRecyclerView_1.setLayoutManager(mLayoutManager_1);
        mRecyclerView_1.setItemAnimator(new DefaultItemAnimator());
    }
    private void initLista_2(View root ){
        mRecyclerView_2 = (RecyclerView) root.findViewById(R.id.rv_menu_2);
        mRecyclerView_1.setHasFixedSize(true);
        mLayoutManager_2 = new LinearLayoutManager(getActivity());
        mRecyclerView_2.setLayoutManager(mLayoutManager_2);
        mRecyclerView_2.setItemAnimator(new DefaultItemAnimator());
    }
    private void initLista_3(View root ){
        mRecyclerView_3 = (RecyclerView) root.findViewById(R.id.rv_menu_3);
        mRecyclerView_3.setHasFixedSize(true);
        mLayoutManager_3 = new LinearLayoutManager(getActivity());
        mRecyclerView_3.setLayoutManager(mLayoutManager_3);
        mRecyclerView_3.setItemAnimator(new DefaultItemAnimator());
    }








}