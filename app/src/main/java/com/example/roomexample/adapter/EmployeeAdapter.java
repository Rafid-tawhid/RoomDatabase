package com.example.roomexample.adapter;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomexample.R;
import com.example.roomexample.callback.EmoloyeeDeleteListener;
import com.example.roomexample.callback.EmployeeRowEditListener;
import com.example.roomexample.callback.OnEmployeeItemClickListener;
import com.example.roomexample.databinding.EmpRowBinding;
import com.example.roomexample.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
    
        private List<Employee> employeeList;
        private EmployeeRowEditListener editListener;
        private EmoloyeeDeleteListener deleteListener;
        private OnEmployeeItemClickListener employeeItemClickListener;

        public EmployeeAdapter(Fragment fragment){
            employeeList = new ArrayList<>();
            editListener = (EmployeeRowEditListener) fragment;
            deleteListener = (EmoloyeeDeleteListener)  fragment;
            employeeItemClickListener = (OnEmployeeItemClickListener) fragment;
        }

        
        public EmployeeAdapter(){
            employeeList = new ArrayList<>();
        }
        
        
    

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final EmpRowBinding binding = EmpRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
            final Employee employee = employeeList.get(position);
            holder.bind(employee);
            holder.itemView.setOnClickListener(v -> {
                final long id = employeeList.get(position).getId();
                employeeItemClickListener.onEmpItemClicked(id);

            });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
    public void setEmployeeList(List<Employee> employees){
            this.employeeList = employees;
            notifyDataSetChanged();
    }

    public void submitNewList(List<Employee> employees) {
            this.employeeList = employees;
            notifyDataSetChanged();
    }


    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private EmpRowBinding binding;
        public EmployeeViewHolder(EmpRowBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.imgBtn.setOnClickListener(v->{
                final int position = getAdapterPosition();
                final Employee employee = employeeList.get(position);
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menus, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()){
                        case R.id.item_edit:
                            editListener.onEditEmployee(employee);

                            return  true;
                    }
                    switch (menuItem.getItemId()){
                        case R.id.item_delete:
                            deleteListener.onDeleteEmployee(employee);
                            return true;
                    }
                    return  false;
                });
            });
        }
        public void bind(Employee employee){
            binding.setEmployee(employee);
        }
    }


}
