package com.butuhpembantu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.butuhpembantu.R;
import com.butuhpembantu.model.MaidLevel;
import com.butuhpembantu.model.ServicePackage;
import com.butuhpembantu.util.Util;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by akm on 1/17/17.
 */

public class OrderServiceActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.location_input)
    EditText locationInput;
    @BindView(R.id.detail_location_input)
    EditText detailLocationInput;
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.employee_grade_selector)
    Spinner employeeGradeSelector;
    @BindView(R.id.service_package_selector)
    Spinner servicePackageSelector;
    @BindView(R.id.order_button)
    AppCompatButton orderButton;
    @BindView(R.id.time_input)
    EditText timeInput;
    @BindView(R.id.date_input)
    EditText dateInput;
    @BindView(R.id.mode_24_hours)
    CheckBox mode24Hours;
//    @BindView(R.id.price_input)
//    EditText priceInput;

    Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_service);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        List<MaidLevel> maidLevels = Select.from(MaidLevel.class).list();
        ArrayAdapter<MaidLevel> maidLevelsAdapter = new ArrayAdapter<MaidLevel>(this, android.R.layout.simple_spinner_dropdown_item, maidLevels);
        employeeGradeSelector.setAdapter(maidLevelsAdapter);

        List<ServicePackage> servicePackages = Select.from(ServicePackage.class).list();
        ArrayAdapter<ServicePackage> servicePackageArrayAdapter = new ArrayAdapter<ServicePackage>(this, android.R.layout.simple_spinner_dropdown_item, servicePackages);
        servicePackageSelector.setAdapter(servicePackageArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("unused")
//    @OnTextChanged(value = R.id.price_input)
//    public void onTextChanged(CharSequence s) {
//        String selectedPackages = servicePackageSelector.getSelectedItem().toString();
//        ServicePackage servicePackage = Select.from(ServicePackage.class).where(Condition.prop("NAME").like("%" + selectedPackages + "%")).first();
//        priceInput.setText(Util.monetaryFormat(servicePackage.getIdnPrice()));
//    }

    @OnClick({R.id.date_input, R.id.time_input, R.id.location_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_input:
                DatePickerDialog dialog = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                onOrderDateSet(view, year, monthOfYear, dayOfMonth);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.setTitle(getResources().getString(R.string.order_date_label));
                dialog.show(getFragmentManager(), "DatePickerDialog");
                break;

            case R.id.time_input:
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        this,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        mode24Hours.isChecked());

                timePickerDialog.setTitle(getResources().getString(R.string.order_time_label));
                timePickerDialog.enableSeconds(false);
                timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
                break;

            case R.id.location_input:
                startActivity(new Intent(this, MapsActivity.class));
                break;
        }

    }

    public void onOrderDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dt = format.format(calendar.getTime());
        dateInput.setText(dt);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        String tm = formatTime.format(calendar.getTime());
        timeInput.setText(tm);
    }


    @OnClick(R.id.order_button)
    public void onClick() {
    }
}
