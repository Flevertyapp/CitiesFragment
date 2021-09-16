package ru.example.citiesfragment;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoatOfArmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoatOfArmsFragment extends Fragment {

    private static final String ARG_INDEX = "index";
    private int index;

    public CoatOfArmsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    //фабричный метод создания фрагмента, рекомендуется через него создавать
    public static CoatOfArmsFragment newInstance(int index) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment(); //создание
        //передача параметра
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Получаем головной элемент из макета
        View view= inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        //находим в контейнере элемент-изображение
        AppCompatImageView imageCoatOfArms= view.findViewById(R.id.coat_of_arms);
        //получение массива указателей на изображения гербов
        TypedArray images=getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        //выбираем подходящий по индексу
        imageCoatOfArms.setImageResource(images.getResourceId(index,-1));
        return view;
    }
}