package com.robert.hospitalappointmentbookingsystem.payments;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.robert.hospitalappointmentbookingsystem.R;

public class PaymentFragment extends Fragment {

    private Button pay_amount_btn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        shareViewModel =
//                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.payment_fragment_layout, container, false);
//        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
          return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pay_amount_btn = view.findViewById(R.id.pay_amount_btn);
        pay_amount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                View alertView = LayoutInflater.from(getActivity()).inflate(R.layout.mpesa_confirmation_dialog_layout,null,false);
                alert.setView(alertView);
                final AlertDialog dialog = alert.create();
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.white)));
                dialog.show();

                EditText phoneNumber = alertView.findViewById(R.id.phone_number);
                Button make_payment_btn = alertView.findViewById(R.id.make_payment_btn);
                make_payment_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Processing payment", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });






            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}