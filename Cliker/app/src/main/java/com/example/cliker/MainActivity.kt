package com.example.cliker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var numberClickCount = 0
    private var numberClickValue = 1
    private var numberClickAuto = 0

    private var numberStatCarbonMine = 0
    private var numberStatIronMine = 0
    private var numberStatGoldMine = 0

    private var numberStatEmployee = 0
    private var numberStatManager = 0
    private var numberStatBoss = 0

    private lateinit var clickCount: TextView
    private lateinit var statClickValue : TextView
    private lateinit var statClickAuto : TextView

    private lateinit var statCarbonMine : TextView
    private lateinit var statIronMine : TextView
    private lateinit var statGoldMine : TextView

    private lateinit var statEmployee : TextView
    private lateinit var statManager : TextView
    private lateinit var statBoss : TextView

    private lateinit var clickButton : Button

    private lateinit var upgradeCarbonMineButton : Button
    private lateinit var upgradeIronMineButton : Button
    private lateinit var upgradeGoldMineButton : Button

    private lateinit var upgradeEmployeeButton : Button
    private lateinit var upgradeManagerButton : Button
    private lateinit var upgradeBossButton : Button

    //price unlock default

    private var numberUpgradeCarbonMine = 10
    private var numberUpgradeIronMine = 2000
    private var numberUpgradeGoldMine = 40000

    private var numberUpgradeEmployee = 5000
    private var numberUpgradeManager = 50000
    private var numberUpgradeBoss = 500000

    //future value click

    private var numberValueClickCarbon = 1
    private var numberValueClickIron = 20
    private var numberValueClickGold = 50


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remain)
        enableEdgeToEdge()
        clickCount = findViewById(R.id.clickCount)
        clickButton = findViewById(R.id.clickButton)
        statClickValue = findViewById(R.id.statClickValue)
        statClickAuto = findViewById(R.id.statClickAuto)

        statCarbonMine = findViewById(R.id.statCarbonMine)
        statIronMine = findViewById(R.id.statIronMine)
        statGoldMine = findViewById(R.id.statGoldMine)

        statEmployee = findViewById(R.id.statEmployee)
        statManager = findViewById(R.id.statManager)
        statBoss = findViewById(R.id.statBoss)

        upgradeCarbonMineButton = findViewById(R.id.upgradeCarbonMineButton)
        upgradeIronMineButton = findViewById(R.id.upgradeIronMineButton)
        upgradeGoldMineButton = findViewById(R.id.upgradeGoldMineButton)

        upgradeEmployeeButton = findViewById(R.id.upgradeEmployeeButton)
        upgradeManagerButton = findViewById(R.id.upgradeManagerButton)
        upgradeBossButton = findViewById(R.id.upgradeBossButton)

        loadData()
        updateStat()

        clickButton.setOnClickListener {
            numberClickCount+=numberClickValue
            updateStat()
        }

        upgradeCarbonMineButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeCarbonMine){
                numberClickCount-=numberUpgradeCarbonMine
                numberUpgradeCarbonMine=(1.5*numberUpgradeCarbonMine).toInt()
                numberStatCarbonMine++
                numberClickValue+=numberValueClickCarbon
                if (numberStatCarbonMine==1){
                    numberValueClickCarbon=2
                }
                if (numberStatCarbonMine%10==0){
                    numberValueClickCarbon+=2
                }
                updateStat()
            }
        }
        upgradeIronMineButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeIronMine){
                numberClickCount-=numberUpgradeIronMine
                numberUpgradeIronMine=(1.6*numberUpgradeIronMine).toInt()
                numberStatIronMine++
                numberClickValue+=numberValueClickIron
                if (numberStatIronMine%10==0){
                    numberValueClickIron+=20
                }
                updateStat()
            }
        }
        upgradeGoldMineButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeGoldMine){
                numberClickCount-=numberUpgradeGoldMine
                numberUpgradeGoldMine=(1.7*numberUpgradeGoldMine).toInt()
                numberStatGoldMine++
                numberClickValue+=numberValueClickGold
                if (numberStatGoldMine%10==0){
                    numberValueClickGold+=50
                }
                updateStat()
            }
        }
        upgradeEmployeeButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeEmployee){
                numberClickCount-=numberUpgradeEmployee
                numberUpgradeEmployee=(1.2*numberUpgradeEmployee).toInt()
                numberStatEmployee++
                if (numberStatEmployee==1){
                    numberClickAuto=1
                }else{
                    numberClickAuto+=2*numberStatEmployee
                }
                updateStat()
            }
        }
        upgradeManagerButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeManager){
                numberClickCount-=numberUpgradeManager
                numberUpgradeManager=(1.3*numberUpgradeManager).toInt()
                numberStatManager++
                if (numberStatManager==1){
                    numberClickAuto=5
                }else{
                    numberClickAuto+=5*numberStatManager
                }
                updateStat()
            }
        }
        upgradeBossButton.setOnClickListener {
            if (numberClickCount>=numberUpgradeBoss){
                numberClickCount-=numberUpgradeBoss
                numberUpgradeBoss=(1.4*numberUpgradeBoss).toInt()
                numberStatBoss++
                if (numberStatBoss==1){
                    numberClickAuto=10
                }else{
                    numberClickAuto+=10*numberStatBoss
                }
                updateStat()
            }
        }

        startAutoClick()



    }

    private fun startAutoClick() {
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                numberClickCount += numberClickAuto
                updateStat()
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }

    private fun updateStat() {
        clickCount.text="Monnaie : $numberClickCount"
        statClickValue.text="Valeur d'un clique : $numberClickValue"
        statClickAuto.text="Monnaie automatique : $numberClickAuto"
        statCarbonMine.text="Mine de charbon level : $numberStatCarbonMine"
        statIronMine.text="Mine de fer level : $numberStatIronMine"
        statGoldMine.text="Mine d'or level : $numberStatGoldMine"
        statEmployee.text="Nombre d'Employée : $numberStatEmployee"
        statManager.text="Nombre de Manager : $numberStatManager"
        statBoss.text="Nombre de PDG : $numberStatBoss"

        if (numberStatCarbonMine>0){
            upgradeCarbonMineButton.text="Améliorer : $numberUpgradeCarbonMine \n Valeur clique + $numberValueClickCarbon"
        }
        if (numberStatIronMine>0){
            upgradeIronMineButton.text="Améliorer : $numberUpgradeIronMine  \n Valeur clique + $numberValueClickIron "
        }
        if (numberStatGoldMine>0){
            upgradeGoldMineButton.text="Améliorer : $numberUpgradeGoldMine \n Valeur clique + $numberValueClickGold "
        }
        if (numberUpgradeEmployee>500){
            var temp =numberStatEmployee
            upgradeEmployeeButton.text="Améliorer : $numberUpgradeEmployee \n Monnaie auto + $temp "
        }
        if (numberUpgradeManager>5000){
            var temp =5*numberStatManager
            upgradeManagerButton.text="Améliorer : $numberUpgradeManager \n Monnaie auto + $temp "
        }
        if (numberUpgradeBoss>50000){
            var temp =10*numberStatBoss
            upgradeBossButton.text="Améliorer : $numberUpgradeBoss \n Monnaie auto + $temp "
        }


    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("ClickerGamePrefs", MODE_PRIVATE)

        // Récupération des valeurs sauvegardées
        numberClickCount = sharedPreferences.getInt("clickCount", 0)
        numberClickValue = sharedPreferences.getInt("clickValue", 1)
        numberClickAuto = sharedPreferences.getInt("clickAuto", 0)

        numberStatCarbonMine = sharedPreferences.getInt("statCarbonMine", 0)
        numberStatIronMine = sharedPreferences.getInt("statIronMine", 0)
        numberStatGoldMine = sharedPreferences.getInt("statGoldMine", 0)
        numberStatEmployee = sharedPreferences.getInt("statEmployee", 0)
        numberStatManager = sharedPreferences.getInt("statManager", 0)
        numberStatBoss = sharedPreferences.getInt("statBoss", 0)

        numberUpgradeCarbonMine = sharedPreferences.getInt("upgradeCarbonMinePrice", 10)
        numberUpgradeIronMine = sharedPreferences.getInt("upgradeIronMinePrice", 2000)
        numberUpgradeGoldMine = sharedPreferences.getInt("upgradeGoldMinePrice", 40000)
        numberUpgradeEmployee = sharedPreferences.getInt("upgradeEmployeePrice", 5000)
        numberUpgradeManager = sharedPreferences.getInt("upgradeManagerPrice", 50000)
        numberUpgradeBoss = sharedPreferences.getInt("upgradeBossPrice", 500000)

        // Mise à jour de l'affichage
        updateStat()
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("ClickerGamePrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Sauvegarde des valeurs essentielles
        editor.putInt("clickCount", numberClickCount)
        editor.putInt("clickValue", numberClickValue)
        editor.putInt("clickAuto", numberClickAuto)

        editor.putInt("statCarbonMine", numberStatCarbonMine)
        editor.putInt("statIronMine", numberStatIronMine)
        editor.putInt("statGoldMine", numberStatGoldMine)
        editor.putInt("statEmployee", numberStatEmployee)
        editor.putInt("statManager", numberStatManager)
        editor.putInt("statBoss", numberStatBoss)

        // Sauvegarde des prix des améliorations
        editor.putInt("upgradeCarbonMinePrice", numberUpgradeCarbonMine)
        editor.putInt("upgradeIronMinePrice", numberUpgradeIronMine)
        editor.putInt("upgradeGoldMinePrice", numberUpgradeGoldMine)
        editor.putInt("upgradeEmployeePrice", numberUpgradeEmployee)
        editor.putInt("upgradeManagerPrice", numberUpgradeManager)
        editor.putInt("upgradeBossPrice", numberUpgradeBoss)

        // Appliquer les modifications
        editor.apply()
    }


    override fun onPause() {
        super.onPause()
        saveData()
    }

}