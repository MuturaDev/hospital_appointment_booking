package com.robert.hospitalappointmentbookingsystem.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.robert.hospitalappointmentbookingsystem.FragmentTransition;
import com.robert.hospitalappointmentbookingsystem.R;
import com.robert.hospitalappointmentbookingsystem.appointment.AppointmentFragment;
import com.robert.hospitalappointmentbookingsystem.doctor.DoctorDetailsFragment;
import com.robert.hospitalappointmentbookingsystem.payments.PaymentFragment;
import com.robert.hospitalappointmentbookingsystem.ui.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeDoctorsRecyclerAdapter extends RecyclerView.Adapter<HomeDoctorsRecyclerAdapter.MyViewHolder> {


    private Context context;
    public FragmentManager fragmentManager;
    private List<Drawable> profile = new ArrayList<>();

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeDoctorsRecyclerAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;


        profile.add(context.getResources().getDrawable(R.drawable.a, null));
        profile.add(context.getResources().getDrawable(R.drawable.b, null));
        profile.add(context.getResources().getDrawable(R.drawable.d, null));
        profile.add(context.getResources().getDrawable(R.drawable.e, null));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeDoctorsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_recycler_item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.setIsRecyclable(false);
        if (position == 1) {
            holder.itemView.findViewById(R.id.open_slot).setVisibility(View.VISIBLE);
            holder.make_payment_btn.setVisibility(View.VISIBLE);

        }

        List<Integer> days = new ArrayList<>();
        days.add(1);
        days.add(2);
        days.add(3);
        makeDayAvailable(days, holder);


        setProfile(profile, holder, position);

    }

    private void setProfile(List<Drawable> profiles, MyViewHolder holder, int position) {

        holder.profile_image.setImageDrawable(profiles.get(position));

    }

    private void makeDayAvailable(List<Integer> daysAvailable, MyViewHolder holder) {
        for (int day : daysAvailable) {
            if (day == 0) {
                holder.monday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 1) {
                holder.tuesday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 2) {
                holder.wednesday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 3) {
                holder.thursday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 4) {
                holder.friday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 5) {
                holder.saturday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            } else if (day == 6) {
                holder.sunday.setBackground(context.getResources().getDrawable(R.drawable.days_available_bg, null));
            }
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 4;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
        private LinearLayout more_btn;
        private LinearLayout book_appointment_click;
        private LinearLayout make_payment_btn;
        boolean book_p_clicked = false;

        private CircularImageView profile_image;
        private LinearLayout doctor_item;


        public MyViewHolder(View view) {
            super(view);
            monday = view.findViewById(R.id.monday);
            tuesday = view.findViewById(R.id.tuesday);
            wednesday = view.findViewById(R.id.wednesday);
            thursday = view.findViewById(R.id.thursday);
            friday = view.findViewById(R.id.friday);
            saturday = view.findViewById(R.id.saturday);
            sunday = view.findViewById(R.id.sunday);

            doctor_item = view.findViewById(R.id.doctor_item);


            more_btn = view.findViewById(R.id.more_btn);
            more_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,Object> message = new HashMap<>();
                    message.put("ProfileImage", profile.get(getAdapterPosition()));
                    FragmentTransition.displayThePassedFragment(DoctorDetailsFragment.class, message, fragmentManager);
                }
            });

            book_appointment_click = view.findViewById(R.id.book_appointment_click);

            book_appointment_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout actionsLayout = view.findViewById(R.id.actions_layout);
                    LinearLayout doctorAvailability = view.findViewById(R.id.doctor_availability);

                    TextView btnText = view.findViewById(R.id.btn_text);
                    ImageView btnImage = view.findViewById(R.id.btn_image);

                    if (book_p_clicked) {
                        actionsLayout.setVisibility(View.GONE);
                        doctorAvailability.setVisibility(View.VISIBLE);
                        btnText.setText("Book or more");
                        btnImage.setImageResource(R.drawable.book_appointment);
                        make_payment_btn.setVisibility(View.VISIBLE);
                        doctor_item.setBackgroundColor(context.getResources().getColor(R.color.white_white));
                        book_p_clicked = false;
                    } else {
                        actionsLayout.setVisibility(View.VISIBLE);
                        doctorAvailability.setVisibility(View.GONE);
                        HashMap<String, Object> message = new HashMap<>();
                        message.put("DoctorIDNumber", getAdapterPosition());
                        //FragmentTransition.moveToFragmentWithHost(AppointmentFragment.class, message, ((MainActivity) context).getSupportFragmentManager(), R.id.fragment_container);
                        btnText.setText("BACK");
                        btnImage.setImageResource(R.drawable.back);
                        make_payment_btn.setVisibility(View.GONE);
                        doctor_item.setBackgroundColor(context.getResources().getColor(R.color.highlight));
                        book_p_clicked = true;
                    }

                }
            });

            make_payment_btn = view.findViewById(R.id.make_payment_btn);
            make_payment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransition.displayThePassedFragment(PaymentFragment.class, null, fragmentManager);
                }
            });

            profile_image = view.findViewById(R.id.profile_image);

        }


    }
}