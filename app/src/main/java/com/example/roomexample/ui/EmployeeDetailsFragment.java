package com.example.roomexample.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomexample.R;
import com.example.roomexample.databinding.FragmentEmployeeDetailsBinding;
import com.example.roomexample.databinding.FragmentNewEmployeeBinding;
import com.example.roomexample.entities.Employee;
import com.example.roomexample.viewmodels.HomeViewModel;


public class EmployeeDetailsFragment extends Fragment {

    private long id = 0;

    private FragmentEmployeeDetailsBinding binding;
    private HomeViewModel homeViewModel;

    public EmployeeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(requireActivity())
                .get(HomeViewModel.class);
        binding=FragmentEmployeeDetailsBinding.inflate(inflater,container,false);


        id = EmployeeDetailsFragmentArgs.fromBundle(getArguments()).getId();

        if (id > 0){
            homeViewModel.getEmployeeById(id)
                    .observe(getViewLifecycleOwner(), employee -> {
//                        Toast.makeText(getActivity(), employee.getName(),
//                                Toast.LENGTH_SHORT).show();
                        binding.ed1.setText(employee.getName());
                    });

            homeViewModel.getEmployeeById(id).observe(getViewLifecycleOwner(),employee -> {

            });

        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
