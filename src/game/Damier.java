package game;
import javax.swing.*;
import java.awt.*;
public class Damier extends JFrame {
    private int tailleCase; // Taille d'une case en pixels

    public Damier(int tailleCase) {
        this.tailleCase = tailleCase;

        setTitle("Damier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int largeur = getWidth() / tailleCase; // Nombre de cases en largeur
        int hauteur = getHeight() / tailleCase; // Nombre de cases en hauteur

        boolean couleurBlanche = true; // Indicateur pour alterner la couleur des cases

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                int x = i * tailleCase;
                int y = j * tailleCase;

                if (couleurBlanche) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(x, y, tailleCase, tailleCase);

                couleurBlanche = !couleurBlanche; // Changer la couleur pour la prochaine case
            }

            if (hauteur % 2 == 0) {
                couleurBlanche = !couleurBlanche; // Inverser la couleur pour la première case de la ligne suivante
            }
        }
    }

    public static void main(String[] args) {
        int tailleCase = 50; // Taille d'une case en pixels
        Damier damier = new Damier(tailleCase);
    }
}
