package com.example.risalat.slidingtabsusingviewpager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mEditTextName;
    private EditText mEditTextName1;
    private TextView mResult;
    RecyclerView recyclerView_s;
    private SQLiteDatabase mDatabase;
    private CalAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab2, container, false);

        CalDBHelper dbHelper = new CalDBHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();

        recyclerView_s= view.findViewById(R.id.recyclerview_s);
        recyclerView_s.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter= new CalAdapter(getContext(),getAllItems());
        recyclerView_s.setAdapter(mAdapter);

        mEditTextName = view.findViewById(R.id.num1);
        mEditTextName1= view.findViewById(R.id.num2);
        //mResult=view.findViewById(R.id.result);

        Button buttonSub = (Button) view.findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   int num1 = Integer.parseInt(mEditTextName.getText().toString());
                   int num2 = Integer.parseInt(mEditTextName1.getText().toString());
                   int result = num1 - num2;
                   String message = (String) (num1 + "-" + num2 + "= " + result);

                   // mResult.setText(message);

                   ContentValues cv = new ContentValues();
                   cv.put(CalContract.CalEntry.COLUMN_NAME_S, message);

                   mDatabase.insert(CalContract.CalEntry.TABLE_NAME_S, null, cv);

                   mAdapter.swapCursor(getAllItems());
                   mEditTextName.getText().clear();
                   mEditTextName1.getText().clear();
               }
               catch (Exception e){

                   Toast.makeText(getContext(),"yor must enter numbers",Toast.LENGTH_LONG).show();
               }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                CalContract.CalEntry.TABLE_NAME_S,
                null,
                null,
                null,
                null,
                null,
                CalContract.CalEntry.COLUMN_TIMESTAMP_S + " DESC"
        );
    }
}
