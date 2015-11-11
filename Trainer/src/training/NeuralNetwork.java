package training;

import com.googlecode.fannj.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nicol on 30/10/2015.
 */
public class NeuralNetwork {

    public Fann mFann;
    public Trainer mTrainer;

    public int mInputNeuronNumber;
    public int mOutputNeuronNumber;

    public NeuralNetwork(int inputNeuronNumber, int outputNeuronNumber) {
        System.setProperty("jna.library.path", "C:\\Users\\home\\Desktop\\OCR-NN\\mylib\\FANN-2.2.0-Source\\bin");

        mInputNeuronNumber = inputNeuronNumber;
        mOutputNeuronNumber = outputNeuronNumber;

        List<Layer> layers = new ArrayList<>();
        layers.add(Layer.create(mInputNeuronNumber));
        layers.add(Layer.create(55, ActivationFunction.FANN_GAUSSIAN));
        layers.add(Layer.create(55, ActivationFunction.FANN_GAUSSIAN));
        layers.add(Layer.create(mOutputNeuronNumber, ActivationFunction.FANN_GAUSSIAN));

        mFann = new Fann(layers);
        mTrainer = new Trainer(mFann);

        mTrainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_QUICKPROP);
    }

    public NeuralNetwork(String filePath) {
        System.setProperty("jna.library.path", "mylib\\FANN-2.2.0-Source\\bin");

        mFann = new Fann(filePath);
        mTrainer = new Trainer(mFann);
        mTrainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_QUICKPROP);
    }

    public void train(String filePath, int maxEpoch, int verboseStep, float desiredError, String neuralMapPath) {
        File outputFolder = new File(neuralMapPath);
        if (!outputFolder.exists())
            outputFolder.mkdir();

        int epochStep = 5000;
        float mse = 1;
        String path = null;
        for (int cpt = 0; cpt < maxEpoch && mse > desiredError; cpt = cpt + epochStep) {
            mse = mTrainer.train(filePath, epochStep, verboseStep, desiredError);
            path = neuralMapPath + "NeuralMap-" + mse + ".txt";
            System.out.println("Neural map printed at : " + path);
            mFann.save(path);
        }
        mFann.save(path);
    }


}
