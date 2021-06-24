package com.robert.hospitalappointmentbookingsystem.appointment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.robert.hospitalappointmentbookingsystem.R;

import java.util.HashMap;

public class AppointmentFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity();

        View root = inflater.inflate(R.layout.appointment_fragment_layout, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Integer message =  (Integer) getSerializableMessage("DoctorIDNumber");
//        if(message != null)
//            Toast.makeText(getContext(), "DoctorIDNumber: " + message, Toast.LENGTH_SHORT).show();
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