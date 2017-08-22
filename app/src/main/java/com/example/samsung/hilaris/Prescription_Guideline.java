package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * First, brings JSON Object date.
 *@paramsetGuidelines : Parsing guidelines in Json object. Guidlines' formats are XML.
 *So,Convert XML to JSON object
 *@paramsetPrescription : Parsing Prescriptions in Json object. Prescriptions' formats are XML.
 *So,Convert XML to JSON object

 */

public class Prescription_Guideline extends AppCompatActivity {

    public String getDate() {
        return date;
    }



    public String date;
    public Guidelines guideline;
    public Prescription prescription;
    private JSON response_JSON;
    public String responseString;
    Prescription_Guideline(JSONObject response) throws JSONException {

        responseString = response.toString();
        response_JSON = new JSON(response);
        date = response_JSON.getDate();
        setGuidelines(response);
        setPrescriptions(response);
    }

    public void setGuidelines(JSONObject response) throws JSONException {
        JSON Guideline = new JSON(response); // Parse it into JsonObject --> GuideLine
        JSON xmlToJson = new JSON(XML.toJSONObject(Guideline.get_Guideline())); // make XML type to Json Type
        JSON guidelines_JSON = new JSON(xmlToJson.get_guidelines()); // Make it into JsonObject
        JSONArray GuidelineArray = guidelines_JSON.get_guidelineConvert();
        guideline = new Guidelines(GuidelineArray);
    }

    public void setPrescriptions(JSONObject response) throws JSONException {
        JSON Prescription = new JSON(response); // Parse it into JsonObject --> GuideLine
        JSON xmlToJson = new JSON(XML.toJSONObject(Prescription.get_Prescription())); // make XML type to Json Type
        JSON prescriptions_JSON = new JSON(xmlToJson.get_prescriptions());
        JSONArray PrescriptionArray = prescriptions_JSON.get_ExerciseRoutineConvert();
        prescription = new Prescription(PrescriptionArray);

    }

    @Override
    public String toString() {
        return "Prescription_Guideline{" +
                ", prescription=" + prescription +
                '}';
    }
}
