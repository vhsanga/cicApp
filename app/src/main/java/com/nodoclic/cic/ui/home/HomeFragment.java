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
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    //Intialize attributes
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private static final String TAG ="TAG_NFC";
    private Context context;
    protected MenuAdapter mAdapter;
    protected RecyclerView mRecyclerView_1;
    RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        mRecyclerView_1 = (RecyclerView) root.findViewById(R.id.rv_menu_1);
        mRecyclerView_1.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView_1.setLayoutManager(mLayoutManager);
        mRecyclerView_1.setItemAnimator(new DefaultItemAnimator());



        final TextView textView = binding.textHome;
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
                    mAdapter = new MenuAdapter(menu);
                    mRecyclerView_1.setAdapter(mAdapter);


                }
            });
        } catch (Exception e) {
            System.err.println("tasks interrupted");
            Toast.makeText(context, "1:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return root;
    }








}