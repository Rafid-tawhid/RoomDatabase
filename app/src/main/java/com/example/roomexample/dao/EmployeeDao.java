package com.example.roomexample.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomexample.entities.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insertEmployee(Employee employee);
    @Update
    void updateEmployee(Employee employee);
    @Delete
    void deleteEmployee(Employee employee);

    @Query("select * from tbl_employee")
    LiveData<List<Employee>> getAllEmployees();

    @Query("select * from tbl_employee where id =:id")
    LiveData<Employee> getEmployeeById(long id);


}
