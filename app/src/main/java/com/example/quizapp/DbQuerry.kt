package com.example.quizapp

import android.util.ArrayMap
import android.util.Log
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.Models.TestModel
import com.example.quizapp.interfaces.categoriesListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore





object DbQuerry {

    val g_firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var g_catList = arrayListOf<CategoryModel>()
    val g_testList = arrayListOf<TestModel>()
    var g_selected_cat_Index = 0

    val TAG: String = "QuizeApp"

    /*when registering user in the signup process  than we can assign them total score "0" and incrementing
     1 in the count each time for registering new user to keep the record of total user*/
    fun createUserData(email: String, name: String, completeListener: MyCompleteListener) {
        val userData: MutableMap<String, Any> = ArrayMap()
        userData["Email_Id"] = email
        userData["Name"] = name
        userData["Total_score"] = "0"
        val userDoc =
            g_firestore.collection("Users").document(FirebaseAuth.getInstance().uid.toString())
        val batch = g_firestore.batch()
        batch[userDoc] = userData
        val countDoc = g_firestore.collection("Users").document("Total_Users")

        batch.update(countDoc, "Count", FieldValue.increment(1))
        batch.commit()
            .addOnSuccessListener { completeListener.onSucess() }
            .addOnFailureListener { completeListener.onFailure() }
    }
/* when user click on any item view of the recyclerview showing category list it will shows the complete
history of login user like the marks in attempted quiz */


    fun getCategories(completeListener: categoriesListener) {
        if (g_catList.size > 0) g_catList.clear()
        g_firestore.collection("Quiz").get().addOnSuccessListener { queryDocumentSnapshots ->
            for (doc in queryDocumentSnapshots) {
                //categoriesList.add(CategoryModel(doc.getString("Name").toString(), doc.getString("Cat_Id").toString(), doc.getLong("No_Of_Test").toString()))
                g_catList.add(CategoryModel(doc.getString("Name").toString(),
                    doc.getString("Cat_Id").toString(),
                    "${doc.getLong("No_Of_Test").toString()} Tests"))
            }
            Log.wtf("HELLO", g_catList.size.toString())
            completeListener.onSuccess(g_catList)
        }.addOnFailureListener {
            completeListener.onError(it.message!!)
        }
    }


    fun loadTestData(completeListener: MyCompleteListener) : List<TestModel>
    {

        Log.d(TAG, "My Category List : " + g_catList);
        Log.d(TAG, "Document ID : " + g_catList.get(g_selected_cat_Index).docId);



//        g_firestore.collection("Quiz").document(g_catList.get(g_selected_cat_Index).docId)
//            .collection("Test_List").document("Test_Info")
//            .get()

        g_firestore.collection("Quiz").document("DrtMu9NXRF0gq5973nqK")
            .collection("Test_List").document("Test_Info")
            .get().addOnCompleteListener(OnCompleteListener {

                g_testList.clear()
                if (it.isSuccessful)
                {
                    val document: DocumentSnapshot = it.getResult()!!

//                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    Log.d(TAG, "db firstName getString() is: " +document.getString("Test1_Id"));
//                    Log.d(TAG, "db lastName getString() is: " +document.getLong("Test1_Time"));


                    val ID1 =document.getString("Test1_Id")
                    val Time1 = document.getLong("Test1_Time")

                    val ID2 = document.getString("Test2_Id")
                    val Time2 = document.getString("Test2_Time")

                    val ID3 = document.getString("Test3_Id")
                    val Time3 = document.getString("Test3_Time")

                    g_testList.add(TestModel(ID1, 0, Time1.toString()))
                    g_testList.add(TestModel(ID2, 0, Time2.toString()))
                    g_testList.add(TestModel(ID3, 0, Time3.toString()))


                }

            })

            /*.addOnSuccessListener {

                val noOfTest = g_catList.get(g_selected_cat_Index).noOfTests

                for (element in g_catList) {

                    element.
                    Log.d(TAG, "loadTestData: "+element)
                    g_testList.add(TestModel("1",0,"5min"))


                }



                completeListener.onSucess()

            }
            .addOnFailureListener {
                completeListener.onFailure()
            }*/
        Log.d(TAG, "List: " + g_testList)
        return g_testList

    }
}
//    fun loadQuestion() {
//    g_firestore.collection("Questions")
//        .whereEqualTo("Category",catList.get(g_selected_cat_Index).docId)//getDocId()
//
//
//    }




