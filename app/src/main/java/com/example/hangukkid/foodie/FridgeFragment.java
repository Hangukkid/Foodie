package com.example.hangukkid.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements FridgeAdapter.ICloseFABMenuCallback {
    private FloatingActionButton fabNewItem, fabManualEntry, fabBarcodeEntry, fabImageEntry;
    private LinearLayout fabManualEntryLayout, fabBarcodeEntryLayout, fabImageEntryLayout;
    private TextView textManualEntry, textBarcodeEntry, textImageEntry;
    private View rootView;
    private boolean isFABOpen = false;
    public static boolean pIsFABOpen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fridge_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rootView =  view.findViewById(R.id.root);

        textManualEntry = (TextView) view.findViewById(R.id.text_manual_entry);
        textManualEntry.setVisibility(View.GONE);

        textBarcodeEntry = (TextView) view.findViewById(R.id.text_barcode_entry);
        textBarcodeEntry.setVisibility(View.GONE);

        fabNewItem = (FloatingActionButton) view.findViewById(R.id.fab_new_item);

        fabManualEntryLayout = (LinearLayout) view.findViewById(R.id.fab_manual_entry_layout);
        fabManualEntry = (FloatingActionButton) view.findViewById(R.id.fab_manual_entry);

        fabBarcodeEntryLayout = (LinearLayout) view.findViewById(R.id.fab_barcode_entry_layout);
        fabBarcodeEntry = (FloatingActionButton) view.findViewById(R.id.fab_barcode_entry);

        fabNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view_screen = rootView.findViewById(R.id.shadowView);
                if (!isFABOpen) {
                    showFABMenu(view_screen);
                } else {
                    closeFABMenu(view_screen);
                }
            }
        });

        fabManualEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManualActivity.class);
                startActivity(intent);
            }
        });

        fabBarcodeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarcodereaderActivity.class);
                startActivity(intent);
            }

        });

        // Create a list of FridgeItems
        final ArrayList<FridgeItem> items = new ArrayList<>(); // final so you can use in on click
        items.add(new FridgeItem("hi"));
        items.add(new FridgeItem("bye"));
        items.add(new FridgeItem("see you later aligator! see you later aligator!"));


        // Create an {@link FridgeAdapter}, whose data source is a list of {@link FridgeItem}s. The
        // adapter knows how to create list items for each item in the list.
        FridgeAdapter adapter = new FridgeAdapter(getActivity(), items, rootView, this);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // fridge_list.xml layout file.
        ListView listView = (ListView) view.findViewById(R.id.fridge_list);

        // Make the {@link ListView} use the {@link FridgeAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Item} in the list.
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view_screen = rootView.findViewById(R.id.shadowView);
                if (isFABOpen) {
                    closeFABMenu(view_screen);
                }
                return true;
            }
        });

        // CAN'T DO THIS WHEN YOU HAVE FOCUSABLE VIEWS IN LIST VIEW ITEM LIKE EDIT TEXT
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
               startActivity(intent);
            }
        });*/
    }

/*    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            View view_screen = view.findViewById(R.id.shadowView);
            closeFABMenu(view_screen);
        }
    }*/

    private void showFABMenu(View view_screen) {
        isFABOpen = true;
        pIsFABOpen = isFABOpen;

        view_screen.setVisibility(View.VISIBLE);

        fabManualEntryLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_95));
        textManualEntry.setVisibility(View.VISIBLE);

        fabBarcodeEntryLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_65));
        fabBarcodeEntryLayout.animate().translationX(-getResources().getDimension(R.dimen.standard_60));
        textBarcodeEntry.setVisibility(View.VISIBLE);
    }

    public void closeFABMenu(View view_screen) {
        isFABOpen = false;
        pIsFABOpen = isFABOpen;

        view_screen.setVisibility(View.GONE);

        fabManualEntryLayout.animate().translationY(0);
        textManualEntry.setVisibility(View.GONE);

        fabBarcodeEntryLayout.animate().translationY(0);
        fabBarcodeEntryLayout.animate().translationX(0);
        textBarcodeEntry.setVisibility(View.GONE);
    }
}
