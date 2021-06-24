package com.robert.hospitalappointmentbookingsystem.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.robert.hospitalappointmentbookingsystem.FragmentTransition;
import com.robert.hospitalappointmentbookingsystem.R;
import com.robert.hospitalappointmentbookingsystem.appointment.AppointmentFragment;
import com.robert.hospitalappointmentbookingsystem.patients.PatientFragment;
import com.robert.hospitalappointmentbookingsystem.ui.MainActivity;

import java.io.File;


import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.aprilapps.easyphotopicker.EasyImage;

public class DoctorAvailabilityFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.doctor_availability_fragment, container, false);
        return root;
    }

    private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private LinearLayout edit_more_btn;
    private ImageView profile_image;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        monday = view.findViewById(R.id.monday);
        monday.setOnClickListener(this);
        tuesday = view.findViewById(R.id.tuesday);
        tuesday.setOnClickListener(this);
        wednesday = view.findViewById(R.id.wednesday);
        wednesday.setOnClickListener(this);
        thursday = view.findViewById(R.id.thursday);
        thursday.setOnClickListener(this);
        friday = view.findViewById(R.id.friday);
        friday.setOnClickListener(this);
        saturday = view.findViewById(R.id.saturday);
        saturday.setOnClickListener(this);
        sunday = view.findViewById(R.id.sunday);
        sunday.setOnClickListener(this);
        profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openGallery(getActivity(), 1);
            }
        });

        FragmentTransition.moveToFragmentWithHost(AppointmentFragment.class, null, ((MainActivity) getActivity()).getSupportFragmentManager(), R.id.parent_fragment_container);

        edit_more_btn = view.findViewById(R.id.edit_more_btn);
        edit_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransition.displayThePassedFragment(PatientFragment.class, null, ((MainActivity)getActivity()).getSupportFragmentManager());
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private File compressedImageFile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "should show", Toast.LENGTH_SHORT).show();
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                e.printStackTrace();
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, final int type) {

                new Compressor((MainActivity)getActivity())
                        .compressToFileAsFlowable(imageFile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.functions.Consumer<File>() {
                            @Override
                            public void accept(File file) {
                                if (type==1){
                                    compressedImageFile = file;
                                    profile_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                    profile_image.setAdjustViewBounds(true);
                                    Glide.with(getActivity()).load(compressedImageFile).into(profile_image);
                                }

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                throwable.printStackTrace();
                                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                                ;

                            }
                        });
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
            }
        });
    }

    private boolean zero = false;
    private boolean one = false;
    private boolean two = false;
    private boolean three = false;
    private boolean four = false;
    private boolean five = false;
    private boolean six = false;


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.monday) {
            zero = setAvailableOrNot(zero, monday);
        } else if (v.getId() == R.id.tuesday) {
            one = setAvailableOrNot(one, tuesday);
        } else if (v.getId() == R.id.wednesday) {
            two = setAvailableOrNot(two, wednesday);
        } else if (v.getId() == R.id.thursday) {
            three = setAvailableOrNot(three, thursday);
        } else if (v.getId() == R.id.friday) {
            four = setAvailableOrNot(four, friday);
        } else if (v.getId() == R.id.saturday) {
            five = setAvailableOrNot(five, saturday);
        } else if (v.getId() == R.id.sunday) {
            six = setAvailableOrNot(six, sunday);
        }
    }

    private boolean setAvailableOrNot(boolean paramClick, View paramView) {
        if (paramClick) {
            paramView.setBackground(getContext().getResources().getDrawable(R.drawable.days_not_available_bg, null));
            paramClick = false;
        } else {
            paramView.setBackground(getContext().getResources().getDrawable(R.drawable.days_available_bg, null));
            paramClick = true;
        }

        return paramClick;
    }
}