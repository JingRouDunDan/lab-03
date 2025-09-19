package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {


    public interface AddCityDialogListener {
        void addCity(City city);
        void cityEdited();
    }

        private AddCityDialogListener listener;
        private City CityEdit = null;


        public static AddCityFragment newInstance(City city){
            AddCityFragment fragment = new AddCityFragment();
            Bundle args = new Bundle();
            args.putString("cityName", city.getName());
            args.putString("provinceName", city.getProvince());
            fragment.setArguments(args);
            fragment.CityEdit = city;
            return fragment;
    }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);

            if (context instanceof AddCityDialogListener) {
                listener = (AddCityDialogListener) context;
            } else {
                throw new RuntimeException(context + "must implement AddCityDialogListener");
            }
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            View view =
                    getLayoutInflater().inflate(R.layout.fragment_add_city, null);
            EditText editCityName = view.findViewById(R.id.edit_text_city_text);
            EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

            if (getArguments() != null) {
                editCityName.setText(getArguments().getString("cityName"));
                editProvinceName.setText(getArguments().getString("provinceName"));
            }


            return new AlertDialog.Builder(getContext())
                    .setView(view)
                    .setTitle(CityEdit == null ? "Add City" : "Edit City")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton(CityEdit == null? "Add" : "Edit", (dialog, which) -> {
                        String cityName = editCityName.getText().toString().trim();
                        String provinceName = editProvinceName.getText().toString().trim();

                        if (CityEdit == null)
                        {
                            listener.addCity(new City(cityName, provinceName));
                        }   else {
                            CityEdit.setName(cityName);
                            CityEdit.setProvince(provinceName);
                            listener.cityEdited();
                        }
                    })
                    .create();
        }
    }
