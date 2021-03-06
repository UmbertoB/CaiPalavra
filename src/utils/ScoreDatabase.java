package utils;

import utils.models.PlayerScore;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ScoreDatabase {

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public void openToWrite (String nomeArq) {
        try {
            File arq = new File(nomeArq);
            FileOutputStream fileOut = new FileOutputStream(arq);
            output = new ObjectOutputStream(fileOut);
        }
        catch (IOException ioException){
            System.exit(1);
        }
    }

    public boolean openToRead (String nomeArq) {
        try {
            File arq = new File(nomeArq);
            if (arq.exists() == false) {
                return false;
            } else {
                FileInputStream fileIn = new FileInputStream(arq);
                input = new ObjectInputStream(fileIn);
                return true;
            }
        }
        catch (IOException ioException){
            return false;
        }
    }

    public void closeWriteFile() {
        try {
            if (output != null) {
                output.flush();
                output.close();
                output = null;
            }
        }
        catch (IOException ioException){
            System.exit(1);
        }
    }

    public void closeReadFile() {
        try {
            if (input != null) {
                input.close();
                input = null;
            }
        }
        catch (IOException ioException){
            System.exit(1);
        }
    }

    public void gravarDados (PlayerScore playerScore) {
        try {
            output.writeObject(playerScore);
        }
        catch (IOException ioException){
            System.exit(1);
        }
    }

    public PlayerScore lerDados (){
        PlayerScore playerScore;

        try {
            playerScore = (PlayerScore) input.readObject();
            return playerScore;
        }
        catch (EOFException endOfFileException) {
            return null;
        }
        catch (ClassNotFoundException classNotFoundException) {
            return null;
        }
        catch (IOException ioException){
            return null;
        }
    }
}