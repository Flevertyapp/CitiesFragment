package ru.example.citiesfragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CitiesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    //private int currentPosition = 0;
    private City currentCity;
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    //здесь инициализируется список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    //создается список городов тз ресурсов
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);
        //создание текствью, заполнение и добавление на экран
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    currentPosition = fi;
                    showCoatOfArms(currentPosition);*/
                    currentCity = new City(fi, getResources().getStringArray(R.array.cities)[fi]);
                    showCoatOfArms(currentCity);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putInt(CURRENT_CITY, currentPosition);
        outState.putParcelable(CURRENT_CITY, currentCity);
        super.onSaveInstanceState(outState);
    }

    //активити создана, можно ее использовать
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //проверка конфигурации экрана
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            //currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0);
            currentCity = savedInstanceState.getParcelable(CURRENT_CITY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.cities)[0]);
        }
        if (isLandscape) {
            //showLandCoatOfArms(currentPosition);
            showLandCoatOfArms(currentCity);
        }
    }

    /*    private void showCoatOfArms(int index) {
            if (isLandscape) {
                showLandCoatOfArms(index);
            } else showRortCoatOfArms(index);
        }*/
    private void showCoatOfArms(City currentCity) {
        if (isLandscape) {
            showLandCoatOfArms(currentCity);
        } else showRortCoatOfArms(currentCity);
    }

    private void showLandCoatOfArms(City currentCity) {
        //создание нового фрагмента с позицией для вывода герба
        //CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(index);
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(currentCity);
        //транзакция по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms, detail); //замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showRortCoatOfArms(City currentCity) {
        //open second activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), CoatOfArmsActivity.class);
        //send parameters in second activity
        intent.putExtra(CoatOfArmsFragment.ARG_CITY, currentCity);
        startActivity(intent);
    }
}