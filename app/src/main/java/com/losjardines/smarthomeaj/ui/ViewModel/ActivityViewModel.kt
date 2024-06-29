package com.losjardines.smarthomeaj.ui.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.losjardines.smarthomeaj.data.model.Reference
import com.losjardines.smarthomeaj.data.model.TagMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {

    private val tag = TagMessage

    private var isValueDoor1Change = false
    private var isValueDoor2Change = false

    // Object Auth Firebase
    val auth = FirebaseAuth.getInstance()
    val authStateInit = MutableLiveData<Boolean>()

    // TimeOut
    val timeoutError = MutableLiveData<Boolean>()

    //Light value state
    val stateLight1 = MutableLiveData<Boolean>()
    val stateLight2 = MutableLiveData<Boolean>()
    val stateLight3 = MutableLiveData<Boolean>()
    val stateLight4 = MutableLiveData<Boolean>()
    val stateLight5 = MutableLiveData<Boolean>()
    val stateLight6 = MutableLiveData<Boolean>()

    //Door value state
    val stateDoor1 = MutableLiveData<Boolean>()
    val stateDoor2 = MutableLiveData<Boolean>()

    //Error of data Receive
    val errorLight1 = MutableLiveData<Boolean>()
    val errorLight2 = MutableLiveData<Boolean>()
    val errorLight3 = MutableLiveData<Boolean>()
    val errorLight4 = MutableLiveData<Boolean>()
    val errorLight5 = MutableLiveData<Boolean>()
    val errorLight6 = MutableLiveData<Boolean>()


    fun singIn(context: Context, email: String, password: String) {
        // Authenticate user
        Toast.makeText(context,"Ingresando..", Toast.LENGTH_SHORT).show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { singIn ->
                if (singIn.isSuccessful) {
                    // Email and password correct
                    authStateInit.postValue(true)
                } else {
                    // Email and password incorrect
                    authStateInit.postValue(false)
                }
            }
    }

    fun singOut(myAuth: FirebaseAuth) {
        myAuth.signOut()
    }

    fun accesLightInfo() {
        //Get reference of all light state on service RealTimeDataBase Firebase
        val database = Firebase.database

        val lightReference1 = database.getReference("light1")
        val lightReference2 = database.getReference("light2")
        val lightReference3 = database.getReference("light3")
        val lightReference4 = database.getReference("light4")
        val lightReference5 = database.getReference("light5")
        val lightReference6 = database.getReference("light6")

        //Access to get data using reference
        lightReference1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight1.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth1.", error.toException())
            }

        })

        lightReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight2.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth2.", error.toException())
            }

        })

        lightReference3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight3.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth3.", error.toException())
            }

        })

        lightReference4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight4.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth4.", error.toException())
            }

        })

        lightReference5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight5.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth5.", error.toException())
            }

        })

        lightReference6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"

                stateLight6.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                //Value to transmit error message
                Log.e(tag.ERROR, "Failed to read value ligth6.", error.toException())
            }

        })
    }

    fun accessDoorInfo() {
        val database = Firebase.database
        //Get reference of all Door state on service RealTimeDataBase Firebase
        val doorReference1 = database.getReference("door1")
        val doorReference2 = database.getReference("door2")

        //Access to get data using reference
        doorReference1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"
                stateDoor1.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(tag.ERROR, "Failed to read value door1.", error.toException())
            }

        })

        doorReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stateStr = snapshot.getValue<String>() ?: "0"
                val state = stateStr == "1"
                stateDoor2.postValue(state)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(tag.ERROR, "Failed to read value door2.", error.toException())
            }

        })

    }

    fun setValueLight(value: String, numberLight: Reference) {
        val database = Firebase.database
        when (numberLight) {
            Reference.LIGHT1 -> {
                val reference = database.getReference("light1")
                reference.setValue(value)
            }

            Reference.LIGHT2 -> {
                val reference = database.getReference("light2")
                reference.setValue(value)
            }

            Reference.LIGHT3 -> {
                val reference = database.getReference("light3")
                reference.setValue(value)
            }

            Reference.LIGHT4 -> {
                val reference = database.getReference("light4")
                reference.setValue(value)
            }

            Reference.LIGHT5 -> {
                val reference = database.getReference("light5")
                reference.setValue(value)
            }

            Reference.LIGHT6 -> {
                val reference = database.getReference("light6")
                reference.setValue(value)
            }

            else -> {
                // Do something
            }
        }
    }

    fun setValueDoor(value: String, numberDoor: Reference) {

        val database = Firebase.database
        when (numberDoor) {

            Reference.DOOR1 -> {
                val reference = database.getReference("door1")
                reference.setValue(value)
            }

            Reference.DOOR2 -> {
                val reference = database.getReference("door2")
                reference.setValue(value)
            }

            else -> {
                // Do something
            }
        }

    }

    fun changeValueState(value: Boolean, reference: Reference) {
        when (reference) {
            Reference.DOOR1 -> {
                isValueDoor1Change = value
            }

            Reference.DOOR2 -> {
                isValueDoor2Change = value
            }

            else -> {

            }
        }
    }

    fun isTimeOutDoor1() {
        viewModelScope.launch {
            Log.d("estado", "isRunning")
            val startTime = System.currentTimeMillis()
            var time: Long
            var isChange: Boolean
            while (true) {
                time = System.currentTimeMillis()
                isChange = isValueDoor1Change
                Log.d("estado", "isChange1: $isChange")
                if ((time - startTime) > 5000) {
                    Log.d("estado", "isTimeOut1")
                    timeoutError.postValue(true)
                    break
                }
                if (isChange) {
                    isValueDoor1Change = false
                    timeoutError.postValue(false)
                    break
                }
                delay(500)
            }
        }
    }

    fun isTimeOutDoor2() {
        viewModelScope.launch {
            Log.d("estado", "isRunning")
            val startTime = System.currentTimeMillis()
            var time: Long
            var isChange: Boolean
            while (true) {
                time = System.currentTimeMillis()
                isChange = isValueDoor2Change
                Log.d("estado", "isChange2: $isChange")
                if ((time - startTime) > 5000) {
                    Log.d("estado", "isTimeOut2")
                    timeoutError.postValue(true)
                    break
                }
                if (isChange) {
                    isValueDoor2Change = false
                    timeoutError.postValue(false)
                    break
                }
                delay(500)
            }
        }
    }

}