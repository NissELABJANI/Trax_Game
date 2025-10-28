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

/*public class Tuile {
    private final ImageIcon faceRecto;
    private final ImageIcon faceVerso;
    private boolean estRectoVisible;

    public Tuile(String cheminRecto, String cheminVerso) {
        this.faceRecto = new ImageIcon(cheminRecto);
        this.faceVerso = new ImageIcon(cheminVerso);
        this.estRectoVisible = true; // Démarre avec le recto visible par défaut
    }

    public void pivoter() {
        // Cette méthode change l'orientation de la tuile
        estRectoVisible = !estRectoVisible;
    }

    public ImageIcon getImageVisible() {
        // Retourne l'image correspondant au côté visible de la tuile
        return estRectoVisible ? faceRecto : faceVerso;
    }

    public void dessiner(Graphics g, int x, int y, int width, int height) {
        // Dessine l'image de la tuile dans un composant Swing à la position (x, y)
        if (estRectoVisible) {
            g.drawImage(faceRecto.getImage(), x, y, width, height, null);
        } else {
            g.drawImage(faceVerso.getImage(), x, y, width, height, null);
        }
    }
}
*/

/*
public class Tuile {
    private final ImageIcon faceRecto;
    private final ImageIcon faceVerso;
    private boolean estRectoVisible;
    private int orientation; // 0 = orientation standard, 1 = pivoté 90 degrés, 2 = 180 degrés, 3 = 270 degrés

    public Tuile(String cheminRecto, String cheminVerso) {
        this.faceRecto = new ImageIcon(cheminRecto);
        this.faceVerso = new ImageIcon(cheminVerso);
        this.estRectoVisible = true; // Démarre avec le recto visible par défaut
        this.orientation = 0;
    }

    public void pivoter() {
        // Change l'orientation de la tuile de 90 degrés
        orientation = (orientation + 1) % 4;
    }

    public void retourner() {
        // Change le côté visible de la tuile
        estRectoVisible = !estRectoVisible;
    }

    public ImageIcon getImageVisible() {
        // Obtient l'image avec l'orientation correcte
        ImageIcon icon = estRectoVisible ? faceRecto : faceVerso;
        return rotateIcon(icon, orientation);
    }

    private ImageIcon rotateIcon(ImageIcon icon, int orientation) {
        // Créer une nouvelle image avec l'orientation spécifiée
        // Cette méthode nécessite une implémentation correcte pour la rotation des images.
        return icon; // Placeholder - Remplacer par l'implémentation de la rotation de l'icône
    }
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tuile {
    private final ImageIcon faceRecto;
    private final ImageIcon faceVerso;
    private boolean estRectoVisible;
    private int orientation; // 0 = 0 degrés, 1 = 90 degrés, 2 = 180 degrés, 3 = 270 degrés

    public Tuile(String cheminRecto, String cheminVerso) {
        this.faceRecto = new ImageIcon(cheminRecto);
        this.faceVerso = new ImageIcon(cheminVerso);
        this.estRectoVisible = true; // Démarre avec le recto visible par défaut
        this.orientation = 0;
    }

    public void pivoter() {
        // Change l'orientation de la tuile de 90 degrés
        orientation = (orientation + 1) % 4;
    }

    public void retourner() {
        // Change le côté visible de la tuile
        estRectoVisible = !estRectoVisible;
    }

    public ImageIcon getImageVisible() {
        // Obtient l'image avec l'orientation correcte
        ImageIcon icon = estRectoVisible ? faceRecto : faceVerso;
        return rotateIcon(icon, orientation);
    }

    private ImageIcon rotateIcon(ImageIcon icon, int orientation) {
        // Rotation de l'icône selon l'orientation
        int rotate = orientation * 90;
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotate), icon.getIconWidth() / 2.0, icon.getIconHeight() / 2.0);

        // Avant de faire la rotation, nous devons aussi translater le graphique pour que l'image reste centrée
        int offset = (icon.getIconWidth() - icon.getIconHeight()) / 2;
        transform.translate(offset, offset);

        g2d.setTransform(transform);
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();
        
        return new ImageIcon(bufferedImage);
    }
}
