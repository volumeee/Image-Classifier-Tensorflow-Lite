package com.example.ml_with_tensorflowlite;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ImageClassifier {
    private Interpreter interpreter;
    private List<String> labels;
    private int inputSize = 224; // Example input size, update as per your model requirements

    public ImageClassifier(Context context, String modelPath, String labelPath) throws IOException {
        interpreter = new Interpreter(loadModelFile(context.getAssets(), modelPath));
        labels = loadLabels(context.getAssets(), labelPath);
    }

    private ByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private List<String> loadLabels(AssetManager assetManager, String labelPath) throws IOException {
        List<String> labels = new ArrayList<>();
        InputStream inputStream = assetManager.open(labelPath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            labels.add(line);
        }
        reader.close();
        return labels;
    }

    public String[] classifyImage(ByteBuffer byteBuffer) {
        TensorBuffer inputBuffer = TensorBuffer.createFixedSize(new int[]{1, inputSize, inputSize, 3}, DataType.FLOAT32);
        inputBuffer.loadBuffer(byteBuffer);

        TensorBuffer outputBuffer = TensorBuffer.createFixedSize(new int[]{1, labels.size()}, DataType.FLOAT32);
        interpreter.run(inputBuffer.getBuffer(), outputBuffer.getBuffer().rewind());

        float[] probabilities = outputBuffer.getFloatArray();
        PriorityQueue<Prediction> pq = new PriorityQueue<>(3, (a, b) -> Float.compare(b.probability, a.probability));
        for (int i = 0; i < probabilities.length; i++) {
            pq.add(new Prediction(labels.get(i), probabilities[i]));
        }

        String[] results = new String[3];
        for (int i = 0; i < 3; i++) {
            Prediction prediction = pq.poll();
            results[i] = prediction.label + ": " + String.format("%.2f", prediction.probability * 100) + "%";
        }
        return results;
    }

    private static class Prediction {
        String label;
        float probability;

        Prediction(String label, float probability) {
            this.label = label;
            this.probability = probability;
        }
    }
}
