package com.example.roomexample.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomexample.R;
import com.example.roomexample.adapter.EmployeeAdapter;
import com.example.roomexample.callback.EmoloyeeDeleteListener;
import com.example.roomexample.callback.EmployeeRowEditListener;
import com.example.roomexample.callback.OnEmployeeItemClickListener;
import com.example.roomexample.databinding.HomeFragmentBinding;
import com.example.roomexample.entities.Employee;
import com.example.roomexample.viewmodels.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements EmoloyeeDeleteListener, EmployeeRowEditListener, OnEmployeeItemClickListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding=HomeFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newEmployeeFragment);
            }
        });
        final LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        binding.rv.setLayoutManager(llm);
        final EmployeeAdapter adapter=new EmployeeAdapter(this);
        binding.rv.setAdapter(adapter);


        mViewModel.getAllEmploees().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
              adapter.submitNewList(employees);
                Toast.makeText(getActivity(), ""+employees.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDeleteEmployee(Employee employee) {
        showAlertDialogue(employee);
    }

    @Override
    public void onEditEmployee(Employee employee) {

        final HomeFragmentDirections.ActionHomeFragmentToNewEmployeeFragment action=
                HomeFragmentDirections.actionHomeFragmentToNewEmployeeFragment();
        action.setEmployee(employee);
        Navigation.findNavController(getActivity(),R.id.fragmentContainerView).navigate(action);
    }
    private  void showAlertDialogue(Employee employee){
        final androidx.appcompat.app.AlertDialog.Builder builder= new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Delete" +employee.getName()+"?");
        builder.setMessage("Are you sure to dlete this employee?");
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            mViewModel.deleteEmployee(employee);
        });
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEmpItemClicked(long id) {


        final HomeFragmentDirections.DetailsEmpAction action=HomeFragmentDirections.detailsEmpAction();
        action.setId(id);
        Navigation.findNavController(getActivity(),R.id.fragmentContainerView).navigate(action);

    }
}