package com.hankyo.navyBuddyApp.findTheDaysCountInTheCamp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hankyo.navyBuddyApp.databinding.FindTheDaysCountInTheCampFragmentBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class findTheDaysCountInTheCampFragment extends Fragment {

    private FindTheDaysCountInTheCampFragmentBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FindTheDaysCountInTheCampFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.find_the_days_count_in_the_camp_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        binding.reportedDateSelect.setOnClickListener(v -> {
            String buttonName = "reportedDateSelect";
            openCalendar(buttonName);
        });

        binding.travellingDateSelect.setOnClickListener(v -> {
            String buttonName = "travellingDateSelect";
            openCalendar(buttonName);
        });


    }


    private void openCalendar(String buttonName){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar. YEAR);
        int currentMonth = calendar.get(Calendar. MONTH);
        int currentDay = calendar.get(Calendar. DAY_OF_MONTH);
        DatePickerDialog calenderDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1; //calender shows 0 for january
                String selectedDate = year +"-"+ month +"-"+ day;
                if(Objects.equals(buttonName, "reportedDateSelect")) {
                    //binding.showSelectedReportedDate.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
                    binding.showSelectedReportedDate.setText(selectedDate);

                    //show days count for today date:
                    //String lastReportedDate = (String) binding.showSelectedReportedDate.getText();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date reportedDate = dateFormat.parse(selectedDate);
                        Date todayDate = new Date();
                        //Toast.makeText(requireContext(),String.valueOf(reportedDate +" "+ todayDate),Toast.LENGTH_SHORT).show();

                        assert reportedDate != null;
                        long difference = todayDate.getTime() - reportedDate.getTime();
                        long days = TimeUnit.MILLISECONDS.toDays(difference)+1; //added 1 to count the today as a 1day
                        //Toast.makeText(requireContext(),String.valueOf(days),Toast.LENGTH_SHORT).show();
                        if (reportedDate.before(todayDate)) {
                            binding.showsTheDaysCountInTheCamp.setText("නිවාඩු ගොස් පැමිණ අදට දින " + days + "කි");
                            binding.showSelectedTravellingDate.setText("____");

                            //show 30th day from last reported date
                            calendar.setTime(reportedDate);
                            calendar.add(Calendar.DAY_OF_MONTH, 30);
                            Date dateToSpecificDaysCount = calendar.getTime();
                            if (todayDate.after(dateToSpecificDaysCount)) { //30days < yourDaysCount
                                binding.show30thDayAndRemainingDaysCountToLeaveTitle.setVisibility(View.INVISIBLE);
                                binding.show30thDayAndRemainingDaysCountToLeave.setVisibility(View.INVISIBLE);
                            } else if (todayDate.before(dateToSpecificDaysCount)) { //30days > yourDaysCount
                                binding.show30thDayAndRemainingDaysCountToLeaveTitle.setVisibility(View.VISIBLE);
                                binding.show30thDayAndRemainingDaysCountToLeave.setVisibility(View.VISIBLE);
                                binding.show30thDayAndRemainingDaysCountToLeaveTitle.setText("\uD83D\uDD35නිවාඩු ගොස් පැමිණ දින 30ක් සම්පූර්ණ වන දිනය:     \uD83D\uDD3D");
                                binding.show30thDayAndRemainingDaysCountToLeave.setText(dateFormat.format(dateToSpecificDaysCount));
                            }

                        }
                        else{
                            Toast.makeText(requireContext(),"නිවාඩු ගොස් පැමිණි දිනය නිසිලෙස තෝරා නැත.",Toast.LENGTH_SHORT).show();
                        }

                    } catch (ParseException e) {
                        Toast.makeText(requireContext(),"නිවාඩු ගොස් පැමිණි දිනය නිසිලෙස තෝරා නැත.",Toast.LENGTH_SHORT).show();
                        //throw new RuntimeException(e);
                    }
                }
                else if(Objects.equals(buttonName, "travellingDateSelect")) {
                    binding.showSelectedTravellingDate.setText(selectedDate);


                    //shows the daysCountInTheCamp
                    String lastReportedDate = (String) binding.showSelectedReportedDate.getText();
                    //Toast.makeText(requireContext(),String.valueOf(lastReportedDate +" "+ travellingDate),Toast.LENGTH_SHORT).show();

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date oldDate = dateFormat.parse(lastReportedDate);
                        Date todayDate = new Date();
                        Date newDate = dateFormat.parse(selectedDate);
                        //Toast.makeText(requireContext(),String.valueOf(oldDate +"\n"+ todayDate +"\n"+ newDate),Toast.LENGTH_SHORT).show();
                        assert newDate != null;
                        assert oldDate != null;
                        long daysCountInCampInMilliseconds = newDate.getTime() - oldDate.getTime();
                        long remainingDaysCountToLeaveInMilliseconds = newDate.getTime() - todayDate.getTime();
//                    long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
//                    long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
//                    long hours = TimeUnit.MILLISECONDS.toHours(difference);
// Toast.makeText(requireContext(), String.valueOf(days + " " + hours + " " + minutes + " " + seconds), Toast.LENGTH_SHORT).show();
                        long daysCountInTheCamp = TimeUnit.MILLISECONDS.toDays(daysCountInCampInMilliseconds);
                        long remainingDaysCountToLeave = TimeUnit.MILLISECONDS.toDays(remainingDaysCountToLeaveInMilliseconds); //this counts the reportedDate and not count the travelling date

                        if (oldDate.before(newDate) && 0 < remainingDaysCountToLeave) {
                            //show days count in the camp:
                            binding.showsTheDaysCountInTheCamp.setText("නිවාඩු යාමට රැදීසිටි දින ගණන දින "+ daysCountInTheCamp +"කි");
                            //show remaining days count:
                            binding.show30thDayAndRemainingDaysCountToLeaveTitle.setVisibility(View.VISIBLE);
                            binding.show30thDayAndRemainingDaysCountToLeave.setVisibility(View.VISIBLE);
                            binding.show30thDayAndRemainingDaysCountToLeaveTitle.setText("\uD83D\uDD35නිවාඩු යාමට ඉතිරිව ඇති දින ගණන:                             \uD83D\uDD3D");
                            binding.show30thDayAndRemainingDaysCountToLeave.setText(remainingDaysCountToLeave +"කි");

                        }
                        else {
                            Toast.makeText(requireContext(),"දිනයන් නිවැරදිව තෝරන්න.",Toast.LENGTH_SHORT).show();
                            binding.showSelectedTravellingDate.setText("____");
                            binding.showsTheDaysCountInTheCamp.setText("");
                            binding.show30thDayAndRemainingDaysCountToLeaveTitle.setText("");
                            binding.show30thDayAndRemainingDaysCountToLeave.setText("");

                        }
                    } catch (ParseException e) {
                        Toast.makeText(requireContext(),"දිනයන් නිවැරදිව තෝරන්න.",Toast.LENGTH_SHORT).show();
                        //e.printStackTrace();
                        //throw new RuntimeException(e);
                    }
                }
            }
        }, currentYear,currentMonth, currentDay);
        calenderDialog.show();
    }

   // pass values to a function when calling function, use arguments to functions
}