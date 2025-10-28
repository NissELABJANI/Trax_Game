/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu2;

/**
 *
 * @author ELABJANI
 */
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;

    public void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Pour jouer en boucle
            } else {
                System.out.println("Fichier audio introuvable");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
        }
    }
}
