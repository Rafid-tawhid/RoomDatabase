package com.example.roomexample.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomexample.R;
import com.example.roomexample.databinding.FragmentNewEmployeeBinding;
import com.example.roomexample.db.EmployeeDatabase;
import com.example.roomexample.entities.Employee;
import com.example.roomexample.viewmodels.HomeViewModel;

public class NewEmployeeFragment extends Fragment {

    private FragmentNewEmployeeBinding binding;
    private HomeViewModel homeViewModel;
    private int id=0;
    public NewEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //receive arguments
        final Employee employee=NewEmployeeFragmentArgs.fromBundle(getArguments()).getEmployee();

        // Inflate the layout for this fragment
        homeViewModel=new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding=FragmentNewEmployeeBinding.inflate(inflater,container,false);


        //edit the employee
        if(employee!=null){
            id=employee.getId();
            binding.nameId.setText(employee.getName());
            binding.salId.setText(String.valueOf(employee.getSalary()));
            binding.btn.setText("Update");
        }



        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=binding.nameId.getText().toString();
                final Double salary=Double.parseDouble(binding.salId.getText().toString());
//                Toast.makeText(getActivity(), ""+name+salary, Toast.LENGTH_SHORT).show();
//                final Employee employee=new Employee(name,salary);
//                EmployeeDatabase.getDb(getActivity()).getEmployeeDao().insertEmployee(employee);
               if(id>0)
               {
                   //update new employee
                   final Employee emp=new Employee(id,name,salary);
                   homeViewModel.updateEmployee(emp);
               }
               else {

                   //insert new emp
                   final Employee employee=new Employee(name,salary);
                   homeViewModel.addEmployees(employee);
               }

                Navigation.findNavController(view).popBackStack();
            }
        });
    }
}