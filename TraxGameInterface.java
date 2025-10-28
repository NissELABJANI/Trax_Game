/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu2;

/**
 *
 * @author ELABJANI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class TraxGameInterface extends JFrame {
    private final int gridSize = 8;
    private final JButton[][] tileButtons = new JButton[gridSize][gridSize];
    private Tuile tuileActuelle;
    private final JLabel selectedTileLabel = new JLabel();
    private final String cheminRecto = "C:\\Users\\ELABJANI\\Documents\\JavaProg2\\Jeu2\\src\\jeu2\\recto.PNG"; 
    private final String cheminVerso = "C:\\Users\\ELABJANI\\Documents\\JavaProg2\\Jeu2\\src\\jeu2\\verso.PNG"; 
    private SoundPlayer soundPlayer;

    public TraxGameInterface() {
        setTitle("Jeu de Trax");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(gridSize, gridSize));
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JButton button = new JButton();
                final int x = i; // Créer une variable finale pour la colonne
                final int y = j; // Créer une variable finale pour la ligne
                button.addActionListener(e -> placeTile(x, y));
                boardPanel.add(button);
                tileButtons[y][x] = button;
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Boutons de contrôle
        JPanel controlPanel = new JPanel();
        JButton drawButton = new JButton("Piocher Tuile");
        JButton rotateButton = new JButton("Pivoter Tuile");
        JButton flipButton = new JButton("Retourner Tuile");

        drawButton.addActionListener(this::drawTile);
        rotateButton.addActionListener(this::rotateTile);
        flipButton.addActionListener(this::flipTile);

        controlPanel.add(drawButton);
        controlPanel.add(rotateButton);
        controlPanel.add(flipButton);
        add(controlPanel, BorderLayout.PAGE_END);

        // Panneau pour afficher la tuile sélectionnée
        JPanel selectedTilePanel = new JPanel();
        selectedTilePanel.setBorder(BorderFactory.createTitledBorder("Tuile Sélectionnée"));
        selectedTilePanel.add(selectedTileLabel);
        add(selectedTilePanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        soundPlayer = new SoundPlayer();
        soundPlayer.playMusic("C:\\Users\\ELABJANI\\Documents\\JavaProg2\\Jeu2\\src\\jeu2\\musique\\musique.wav"); // Remplacer par le chemin de votre fichier audio
    }
    
    // N'oubliez pas de stopper la musique lorsque vous fermez le jeu
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            soundPlayer.stopMusic();
        }
    }

    private void drawTile(ActionEvent e) {
        tuileActuelle = new Tuile(cheminRecto, cheminVerso);
        updateSelectedTile();
    }

    private void rotateTile(ActionEvent e) {
        if (tuileActuelle != null) {
            tuileActuelle.pivoter();
            updateSelectedTile();
        }
    }

    private void flipTile(ActionEvent e) {
        if (tuileActuelle != null) {
            tuileActuelle.retourner(); 
            updateSelectedTile();
        }
    }

    private void placeTile(int x, int y) {
        if (tuileActuelle != null){
            tileButtons[y][x].setIcon(tuileActuelle.getImageVisible());
            tuileActuelle = null; // Réinitialiser la tuile actuelle
            
        }
    }

    private void updateSelectedTile() {
        // Met à jour l'affichage de la tuile sélectionnée
        selectedTileLabel.setIcon(tuileActuelle.getImageVisible());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TraxGameInterface::new);
    }
}

