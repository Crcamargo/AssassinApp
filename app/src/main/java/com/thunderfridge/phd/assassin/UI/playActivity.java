package com.thunderfridge.phd.assassin.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.thunderfridge.phd.assassin.R;

import static android.R.color.white;

/*
 * PlayActivity Class
 */
public class playActivity extends AppCompatActivity {

    /** Member Variables **/
    public Button attackBut, takeDamBut;
    public ImageButton helpBut, undoBut;
    public TextView healthText, armorText, attackDamageText, aCountText;
    public Switch td;
    int health = 1000;
    int armor = 0;
    int attackDamage = 0;
    int count = 0;
    int undoHealth = 1000;
    int undoArmor = 0;
    Context context;
    CharSequence text;
    int duration = Toast.LENGTH_SHORT;
    Toast toast;
    View myView;


    /*
     * OnCreate method for Play activity, everything initialized in play()
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        play();
    }

    /*
     * Play method
     */
    public void play() {

        /*
         * Initialize Buttons and text views
         */
        attackBut        = (Button)      findViewById(R.id.buttonAttack);
        takeDamBut       = (Button)      findViewById(R.id.TakeDamage);
        helpBut          = (ImageButton) findViewById(R.id.helpBut);
        undoBut          = (ImageButton) findViewById(R.id.undo);
        healthText       = (TextView)    findViewById(R.id.Health);
        armorText        = (TextView)    findViewById(R.id.Shield);
        attackDamageText = (TextView)    findViewById(R.id.AttackDamage);
        aCountText       = (TextView)    findViewById(R.id.Count);
        td               = (Switch)      findViewById(R.id.switch1);

        context = getApplicationContext();

        /* Set font to blackcherry */
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/blackcherry.ttf");
        attackBut.setTypeface(customFont);
        takeDamBut.setTypeface(customFont);
        healthText.setTypeface(customFont);
        armorText.setTypeface(customFont);
        attackDamageText.setTypeface(customFont);
        aCountText.setTypeface(customFont);


        /*
            Show tutorial if first time
        */
        SharedPreferences settings = getSharedPreferences("pref",0);
        boolean firstRun = settings.getBoolean("firstRun",true);
        if( firstRun ){
            tutorial();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
        }

        /*
            Help button
         */
        helpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutorial();
            }
        });

        /*
            Undo button
         */
        undoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healthText.setText(Integer.toString(undoHealth));
                armorText.setText(Integer.toString(undoArmor));
                armor = undoArmor;
                health = undoHealth;
            }
        });

        /*
            Tap Armor text action
         */
        armorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu attackDamageMenu = new PopupMenu(getApplicationContext(),v);
                attackDamageMenu.inflate(R.menu.armormenu);
                attackDamageMenu.show();
                attackDamageMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int selectedItemID = item.getItemId();
                        switch(selectedItemID){
                            case R.id.tier1armor:
                                armor = 100;
                                armorText.setText("100");
                                break;
                            case R.id.tier2armor:
                                armor = 200;
                                armorText.setText("200");
                                break;
                            case R.id.tier3armor:
                                armor = 300;
                                armorText.setText("300");
                                break;
                        }
                        return true;
                    }
                });
            }
        });

        /*
            Tap attckDamgeText action
         */
        attackDamageText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu armorMenu = new PopupMenu(getApplicationContext(),v);
                armorMenu.inflate(R.menu.weaponmenu);
                armorMenu.show();
                armorMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int selectedItemID = item.getItemId();
                        switch(selectedItemID){
                            case R.id.tier1weapon:
                                attackDamage = 100;
                                attackDamageText.setText("100");
                                count = 3;
                                aCountText.setText("3");
                                break;
                            case R.id.tier2weapon:
                                attackDamage = 200;
                                attackDamageText.setText("200");
                                count = 3;
                                aCountText.setText("3");
                                break;
                            case R.id.tier3weapon:
                                attackDamage = 300;
                                attackDamageText.setText("300");
                                count = 3;
                                aCountText.setText("3");
                                break;
                        }
                        return true;
                    }
                });

            }
        });

        /*
            Tap health action
         */
        healthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu healthMenu = new PopupMenu(getApplicationContext(),v);
                healthMenu.inflate(R.menu.healmenu);
                healthMenu.show();
                healthMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int selectedItemID = item.getItemId();
                        switch(selectedItemID){
                            case R.id.threehundredheal:
                                health += 300;
                                if(health > 1000)
                                    health = 1000;
                                healthText.setText(String.valueOf(health));
                                break;
                            case R.id.twohundredheal:
                                health += 200;
                                if(health > 1000)
                                    health = 1000;
                                healthText.setText(String.valueOf(health));
                                break;
                        }
                        return true;
                    }
                });
            }
        });

        /*
            Attack! Button Action
         */
        attackBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count--;
                if(count >= 0 ){
                    aCountText.setText(String.valueOf(count));
                }
                if(count <= 0){
                    count = 0;
                    aCountText.setText(String.valueOf(count));
                    attackDamageText.setText("0");
                    text = "Can't Attack!";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        /*
            Take Damage Button Action
         */
        takeDamBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView = v;
                final PopupMenu damageMenu = new PopupMenu(getApplicationContext(),v);
                damageMenu.inflate(R.menu.damagemenu);
                damageMenu.show();
                damageMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        PopupMenu basicMenu;
                        int selectedItemID = item.getItemId();
                        switch(selectedItemID){
                            /* If Basic Attack */
                            case R.id.basicClick:
                                basicMenu = new PopupMenu(getApplicationContext(), myView);
                                basicMenu.inflate(R.menu.basicdamage);
                                basicMenu.show();
                                basicMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        int selectedItemId = item.getItemId();
                                        switch(selectedItemId){
                                            case R.id.one:
                                                takeDamage(100,td.isChecked());
                                                break;
                                            case R.id.two:
                                                takeDamage(200,td.isChecked());
                                                break;
                                            case R.id.three:
                                                takeDamage(300,td.isChecked());
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                break;
                            /* If Fire attack */
                            case R.id.fireClick:
                                basicMenu = new PopupMenu(getApplicationContext(), myView);
                                basicMenu.inflate(R.menu.firedamage);
                                basicMenu.show();
                                basicMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        int selectedItemId = item.getItemId();
                                        switch(selectedItemId){
                                            case R.id.eighty:
                                                takeDamage(80,true);
                                                break;
                                            case R.id.hundred:
                                                takeDamage(100,true);
                                                break;
                                            case R.id.onetwenty:
                                                takeDamage(120,true);
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                break;
                            /* if poison attack */
                            case R.id.PoisonClick:
                                basicMenu = new PopupMenu(getApplicationContext(), myView);
                                basicMenu.inflate(R.menu.poisondamage);
                                basicMenu.show();
                                basicMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        int selectedItemId = item.getItemId();
                                        switch(selectedItemId){
                                            case R.id.fifty:
                                                takeDamage(50,true);
                                                break;
                                            case R.id.sixty:
                                                takeDamage(60,true);
                                                break;
                                            case R.id.seventy:
                                                takeDamage(70,true);
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                break;
                            /* if Custom damage */
                            case R.id.CustomClick:
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(playActivity.this);
                                final EditText enterCustomText = new EditText(playActivity.this);
                                enterCustomText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                alertBuilder.setView(enterCustomText);
                                alertBuilder.setCancelable(true)
                                        .setPositiveButton("Take Damage", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Editable my = enterCustomText.getText();
                                                String damageString = my.toString();
                                                int damage;
                                                if(damageString.matches("")){
                                                    damage = 0;
                                                }
                                                else{
                                                    damage = Integer.parseInt(damageString);
                                                }
                                                takeDamage(damage, td.isChecked());
                                            }
                                        });
                                Dialog dialog = alertBuilder.create();
                                dialog.show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    /*
        Method for taking damage
        direct = true  if damage is dealt to health directly
        direct = false if damage is applied to armor first, then health
     */
    public void takeDamage(int amount, boolean direct){
        if(direct && health > 0){
            undoHealth = health;
            undoArmor = armor;
            health -= amount;
            healthText.setText(String.valueOf(health));
        }
        else if( health > 0){
            undoHealth = health;
            undoArmor = armor;
            armor -= amount;
            if(armor > 0){
                armorText.setText(String.valueOf(armor));
            }
            else{
                health += armor;
                armor = 0;
                armorText.setText(String.valueOf(armor));
                healthText.setText(String.valueOf(health));
            }
        }
        /* Did they die? */
        if( health <= 0){
            text = "YOU HAVE DIED";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /*
       Create showcase to show how to use app
    */
    public void tutorial() {
       TapTargetSequence seq = new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.Health), "Health", getString(R.string.healthTut))
                                .cancelable(false)
                                .targetRadius(100)
                                .textColor(white),
                        TapTarget.forView(findViewById(R.id.Shield), "Armor", getString(R.string.armorTut))
                                .cancelable(false)
                                .textColor(white),
                        TapTarget.forView(findViewById(R.id.AttackDamage), "Attack", getString(R.string.attackTut))
                                .cancelable(false)
                                .textColor(white),
                        TapTarget.forView(findViewById(R.id.buttonAttack), "Attack", getString(R.string.attackButtonTut))
                                .cancelable(false)
                                .textColor(white),
                        TapTarget.forView(findViewById(R.id.TakeDamage), "Take Damage", getString(R.string.takeDamageButTut))
                                .cancelable(false)
                                .textColor(white)
                                .targetRadius(100),
                        TapTarget.forView(findViewById(R.id.undo), "Undo", getString(R.string.undoTut))
                                .cancelable(false)
                                .textColor(white),
                        TapTarget.forView(findViewById(R.id.helpBut), "Help Button", getString(R.string.healpTut))
                                .cancelable(false)
                                .textColor(white));
        seq.start();


    }
}