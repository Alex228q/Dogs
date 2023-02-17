package com.example.dogs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDog;
    private Button buttonLoadImage;
    private ProgressBar progressBar;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.loadDogImage();

        viewModel.getDogImage().observe(this, dogImage -> Glide.with(MainActivity.this)
                .load(dogImage.getMessage())
                .into(imageViewDog));

        viewModel.getIsLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        buttonLoadImage.setOnClickListener(v -> viewModel.loadDogImage());
    }

    private void initViews() {
        imageViewDog = findViewById(R.id.imageViewDog);
        buttonLoadImage = findViewById(R.id.buttonLoadImage);
        progressBar = findViewById(R.id.progressBar);
    }
}