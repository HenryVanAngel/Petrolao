package br.com.petrolao;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView gasPriceTextView;
    private TextView gasPriceLabel;
    private SeekBar gasPriceSeekBar;
    private TextView etanolPriceTextView;
    private TextView etanolPriceLabel;
    private SeekBar etanolPriceSeekBar;
    private TextInputLayout resultTextInput;
    private ImageView imageResult;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double priceGas;
    private double priceEtanol;
    private double reason;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasPriceTextView = (TextView) findViewById(R.id.gasPriceTextView);
        gasPriceLabel = (TextView) findViewById(R.id.gasPriceLabel);
        gasPriceSeekBar = (SeekBar) findViewById(R.id.etanolPriceSeekBar);
        etanolPriceTextView = (TextView) findViewById(R.id.etanolPriceTextView);
        etanolPriceLabel = (TextView) findViewById(R.id.etanolPriceLabel);
        etanolPriceSeekBar = (SeekBar) findViewById(R.id.etanolPriceSeekBar);
        resultTextInput =(TextInputLayout) findViewById(R.id.resultTextInput);

        imageResult = (ImageView) findViewById(R.id.imageResult);

        priceGas = priceEtanol = 3;
        calculate();
        gasPriceSeekBar.setOnSeekBarChangeListener(listen);
        etanolPriceSeekBar.setOnSeekBarChangeListener(listen);

    }
    private void calculate(){
        reason = priceEtanol / priceGas;
        gasPriceLabel.setText(currencyFormat.format(priceGas));
        etanolPriceLabel.setText(currencyFormat.format(priceEtanol));
        if(reason>=0.7){
            imageResult.setImageResource(R.drawable.gasoline);
            TextInputEditText editText = new TextInputEditText(resultTextInput.getContext());
            editText.setText(getString(R.string.gas));
        }else{
            imageResult.setImageResource(R.drawable.etanol);
            TextInputEditText editText = new TextInputEditText(resultTextInput.getContext());
            editText.setText(getString(R.string.etanol));
        }
    }

    private SeekBar.OnSeekBarChangeListener listen = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.gasPriceSeekBar){
                priceGas = progress / 100.;
            }else {
                priceEtanol = progress /100.;
            }
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
