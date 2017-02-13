package com.example.juliettecoia.foodonate;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends android.support.v4.app.Fragment{

    String foodBank = "yes";
    public static ArrayList<String> requestedFood = new ArrayList<String>();
    public static String foodListt = "";

    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    DatabaseReference parentRef = database.getReference(uid);

    parentRef.ge*/

   // boolean foodBank = NavigationDrawer.logged_in_user.getAcceptDon();


 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Setting the layout

     if(foodBank != "yes")
     {
         View view = inflater.inflate(R.layout.request_food, container,false);
         super.onActivityCreated(savedInstanceState);
         getActivity().getWindow().setSoftInputMode(
                 WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         return view;
     }

     else if(foodBank == "yes")
     {
         View view = inflater.inflate(R.layout.fragment_donate, container, false);
         super.onActivityCreated(savedInstanceState);
         getActivity().getWindow().setSoftInputMode(
                 WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         return view;
     }
     return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Send to function based on organziation type
        super.onActivityCreated(savedInstanceState);

        if(foodBank  == "yes")
            donateMap();

        else if(foodBank != "yes")
            requestFood();
    }

    private void donateMap()
        //select food to donate and select map - FIX SO IT ONLY WORKS WHEN YOU ENTER IN A FOOD
    {
        Button moveToMap = (Button) getActivity().findViewById(R.id.button);
        moveToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText donate = (EditText) getView().findViewById(R.id.donateFood);

                if(donate == null) {
                    Toast.makeText(getActivity(), "Please enter in food to donate before proceeding to select a location.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    foodListt = donate.getText().toString();
                    Intent firstpage = new Intent(getActivity(), MapsActivity.class);
                    startActivity(firstpage);

                }

            }
        });
    }

    public static EditText edittext;

    private void requestFood()
    {


        edittext = (EditText) getView().findViewById(R.id.requestFood);
        edittext.setText(null);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) ||
                        (keyCode == 66)) {

                    HomeActivity.food = edittext.getText().toString();
                    Toast.makeText(getActivity(), HomeActivity.food, Toast.LENGTH_SHORT).show();
                    //requestedFood.add(food);
                    edittext.setText("");
                    Toast.makeText(getActivity(), "test"+HomeActivity.food, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
