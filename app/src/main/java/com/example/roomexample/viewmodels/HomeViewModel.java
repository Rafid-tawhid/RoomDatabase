package com.example.roomexample.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomexample.entities.Employee;
import com.example.roomexample.repository.EmployeeLocalRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
   private EmployeeLocalRepository repository;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository=new EmployeeLocalRepository(application);
    }

    public void addEmployees(Employee employee){
        repository.addEmployee(employee);


    }
    public LiveData<List<Employee>> getAllEmploees(){

        return repository.getEmployee();
    }
    public LiveData<Employee> getEmployeeById(long id){
        return repository.getEmployeeById(id);
    }
    public void updateEmployee(Employee employee){
        repository.updateEmployee(employee);
    }
    public void deleteEmployee(Employee employee){
        repository.deleteEmployee(employee);
    }
}