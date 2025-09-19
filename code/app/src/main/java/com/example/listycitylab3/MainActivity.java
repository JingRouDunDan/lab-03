package com.example.listycitylab3;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private CityArrayAdapter cityAdapter;

    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();

    }

    public void cityEdited() {
        cityAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Toronto"
        };

        String[] provinces = {"AB","BC","ON"};

        ArrayList<City> dataList = new ArrayList<City>();

        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        ListView cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
                    new AddCityFragment().show(getSupportFragmentManager(), "add_city");
                });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City city = dataList.get(position);
            AddCityFragment editDialog = AddCityFragment.newInstance(city);
            editDialog.show(getSupportFragmentManager(), "edit_city");
            });
        };
    }