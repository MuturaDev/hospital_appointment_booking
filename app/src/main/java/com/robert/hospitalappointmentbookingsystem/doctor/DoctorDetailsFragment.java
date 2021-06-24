package com.robert.hospitalappointmentbookingsystem.doctor;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.robert.hospitalappointmentbookingsystem.R;


import java.util.HashMap;


public class DoctorDetailsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.doctor_details_fragment_layout, container, false);
        return root;
    }

    private CircularImageView profile_image;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profile_image  = view.findViewById(R.id.profile_image);
        Drawable profileImage =  (Drawable) getSerializableMessage("ProfileImage");
        if(profileImage != null)
            profile_image.setImageDrawable(profileImage);



        super.onViewCreated(view, savedInstanceState);
    }


    private Object getSerializableMessage(String messageKey){
        Object returnObject = null;
        if(this.getArguments() != null){
            HashMap message = (HashMap) this.getArguments().getSerializable("message");
            returnObject = message.get(messageKey);
        }
        return returnObject;
    }
}