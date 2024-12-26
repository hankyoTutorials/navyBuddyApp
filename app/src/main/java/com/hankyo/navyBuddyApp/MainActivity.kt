package com.hankyo.navyBuddyApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hankyo.navyBuddyApp.shortNoteBook.ShortNoteBookActivity
import com.hankyo.navyBuddyApp.epay.ePayActivity
import com.hankyo.navyBuddyApp.findTheDaysCountInTheCamp.findTheDaysCountInTheCampFragment
import com.hankyo.navyBuddyApp.checkListsUpdate.checkListsUpdateFragment
import com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.expensesAndBudgetMonitorFragment
import com.hankyo.navyBuddyApp.sealinkWebsite.sealinkWebsiteFragment
import com.hankyo.navyBuddyApp.forMentalHealth.forMentalHealthFragment
import com.hankyo.navyBuddyApp.about.aboutActivity

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //receive noticesSection data from internet
        val url: String = "https://raw.githubusercontent.com/hankyoTutorials/navyBuddyApp/refs/heads/master/textNoticeInApp"
        val noticesFromInternet: String;
        AndroidNetworking.initialize(this.applicationContext) //initialize for networking
        AndroidNetworking.get(url)
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String) {
                    val response: String = response.trim().replace("\n+".toRegex(),"\n") //prevent getting a blank line
                    System.out.println("noticesFromInternet: " + response)
                    val textNoticesSection: TextView = findViewById(R.id.textNoticesSection)
                    textNoticesSection.setText(response)
                }

                override fun onError(error: ANError) {
                    System.out.println("error: " + error)
                }
            })

        //shortNoteBook
        val shortNoteBookButton: Button = findViewById(R.id.shortNoteBookButton)
        shortNoteBookButton.setOnClickListener {
            val intent = Intent(this, ShortNoteBookActivity::class.java)
            startActivity(intent)
            //shortNoteBookButton.setText("buttonClicked")
        }

        //expensesAndBudgetMonitor
        val expensesAndBudgetMonitorButton: Button = findViewById(R.id.expensesAndBudgetMonitorButton)
        expensesAndBudgetMonitorButton.setOnClickListener {
            val expensesAndBudgetMonitorFragment = expensesAndBudgetMonitorFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main,expensesAndBudgetMonitorFragment)
            transaction.addToBackStack("expensesAndBudgetMonitor")
            transaction.commit()
        }

        //checkListsUpdate
        val checkListsUpdate: Button = findViewById(R.id.checkListsUpdate)
        checkListsUpdate.setOnClickListener {
            val checkListsUpdateFragment = checkListsUpdateFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main,checkListsUpdateFragment)
            transaction.addToBackStack("checkListsUpdate")
            transaction.commit()
        }

        //epay
        val ePay: Button = findViewById(R.id.ePayButton)
        ePay.setOnClickListener {
            val intent = Intent(this,ePayActivity::class.java)
            startActivity(intent)
            System.out.println("clickes ePay Button")
        }

        //findTheDaysCountInTheCamp
        val findTheDaysCountInTheCamp: Button = findViewById(R.id.findTheDaysCountInTheCamp)
        findTheDaysCountInTheCamp.setOnClickListener {
            val findTheDaysCountInTheCampFragment = findTheDaysCountInTheCampFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main, findTheDaysCountInTheCampFragment)
            transaction.addToBackStack("findTheDaysCountInTheCamp")
            transaction.commit()
        }

        //gotoSealinkWebsite
        val sealinkWebsite: Button = findViewById(R.id.sealinkWebsite)
        sealinkWebsite.setOnClickListener {
            val sealinkWebsiteFragment = sealinkWebsiteFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main, sealinkWebsiteFragment)
            transaction.addToBackStack("sealinkWebsite")
            transaction.commit()
        }

        //goto forMentalHeath section
        val forMentalHealth: Button = findViewById(R.id.forMentalHealthButton);
        forMentalHealth.setOnClickListener {
            val forMentalHealthFragment = forMentalHealthFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main,forMentalHealthFragment)
            transaction.addToBackStack("forMentalHealth")
            transaction.commit()
        }

        //goto about section
       val aboutSection: Button = findViewById(R.id.forAboutButton)
       aboutSection.setOnClickListener {
           val intent = Intent(this,aboutActivity::class.java)
           startActivity(intent)
       }


    }
}