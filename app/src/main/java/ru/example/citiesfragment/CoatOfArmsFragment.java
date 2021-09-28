package ru.example.citiesfragment;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CoatOfArmsFragment extends Fragment {

    /*    static final String ARG_INDEX = "index";
        private int index;*/
    static final String ARG_CITY = "city";
    private City city;

    public CoatOfArmsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    //фабричный метод создания фрагмента, рекомендуется через него создавать
    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment(); //создание
        //передача параметра
        Bundle args = new Bundle();
        //args.putInt(ARG_INDEX, index);
        args.putParcelable(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //index = getArguments().getInt(ARG_INDEX);
            city = getArguments().getParcelable(ARG_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Получаем головной элемент из макета
        View view = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        //находим в контейнере элемент-изображение
        AppCompatImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms);
        //получение массива указателей на изображения гербов
        TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        //выбираем подходящий по индексу
        imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), -1));
        TextView cityNameView = view.findViewById(R.id.textView);
        cityNameView.setText(city.getCityName());
        return view;
    }
}