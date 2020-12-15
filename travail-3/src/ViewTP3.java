/**
 * Objectif : Analyser le texte d’un JTextArea d’en extraire les mots et les nombres, d’effectuer des recherches et des tris.
 *
 * @author : Jean-Philippe Miguel-Gagnon
 * Automne 2020
 */

import javax.swing.*;
import java.awt.*;

public class ViewTP3 extends JFrame {

    JFrame frame;

    JPanel panNorth;
    JPanel panCenter;
    JPanel panEast;
    JPanel panSouth;

    JLabel labMots;
    JLabel labNombres;
    JLabel labFouille;
    JTextArea txaTexte;
    JTextArea txaMots;
    JTextArea txaNombres;
    JTextField txfRecherche;
    JCheckBox chbDoublons;
    JRadioButton radCroissant;
    JRadioButton radDecroissant;
    ButtonGroup radTriage;
    JButton btnQuitter;
    JButton btnRechercher;
    JButton btnExtraireMots;
    JButton btnExtraireNombres;
    JButton btnTrierMots;
    JButton btnTrierNombres;
    JButton btnViderMots;
    JButton btnViderNombres;
    JButton btnInfoNombres;
    JButton btnInfoMots;

    Dimension dimTxf = new Dimension(200, 30);
    Dimension dimBtn = new Dimension(143, 30);

    // --- Constructeur --- //

    public ViewTP3() {
        frame = new JFrame("TP3 Jean-Philippe Miguel-Gagnon - 1927230");
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setSize(new Dimension(1280, 720));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createNorth();
        createCenter();
        createEast();
        createSouth();

        frame.setVisible(true);
    }

    // --- JPanel Components --- //

    public void createNorth() {
        panNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txfRecherche = new JTextField();
        txfRecherche.setPreferredSize(dimTxf);

        btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> btnRechercherAction());
        labFouille = new JLabel();

        panNorth.add(txfRecherche);
        panNorth.add(btnRechercher);
        panNorth.add(labFouille);
        frame.add(panNorth, BorderLayout.NORTH);
    }

    public void createCenter() {
        panCenter = new JPanel(new GridLayout(1, 3));

        txaTexte = new JTextArea();
        txaTexte.setLineWrap(true);
        txaMots = new JTextArea();
        txaMots.setLineWrap(true);
        txaMots.setEditable(false);
        txaNombres = new JTextArea();
        txaNombres.setLineWrap(true);
        txaNombres.setEditable(false);

        panCenter.add(new JScrollPane(txaTexte));
        panCenter.add(new JScrollPane(txaMots));
        panCenter.add(new JScrollPane(txaNombres));
        frame.add(panCenter, BorderLayout.CENTER);
    }

    public void createEast() {
        panEast = new JPanel();
        panEast.setLayout(new BoxLayout(panEast, BoxLayout.Y_AXIS));

        chbDoublons = new JCheckBox("Permettre doublons");
        chbDoublons.setSelected(true);

        btnExtraireMots = new JButton("Extraire Mots");
        btnExtraireMots.setMaximumSize(dimBtn);
        btnExtraireMots.addActionListener(e -> btnExtraireMotsAction());
        btnViderMots = new JButton("Vider Mots");
        btnViderMots.setMaximumSize(dimBtn);
        btnViderMots.addActionListener(e -> btnViderMotsAction());

        btnExtraireNombres = new JButton("Extraire Nombres");
        btnExtraireNombres.setMaximumSize(dimBtn);
        btnExtraireNombres.addActionListener(e -> btnExtraireNombresAction());
        btnViderNombres = new JButton("Vider Nombres");
        btnViderNombres.setMaximumSize(dimBtn);
        btnViderNombres.addActionListener(e -> btnViderNombresAction());

        radCroissant = new JRadioButton("tri croissant");
        radCroissant.setSelected(true);
        radDecroissant = new JRadioButton("tri décroissant");

        radTriage = new ButtonGroup();
        radTriage.add(radCroissant);
        radTriage.add(radDecroissant);

        btnTrierMots = new JButton("Trier Mots");
        btnTrierMots.setMaximumSize(dimBtn);
        btnTrierMots.addActionListener(e -> btnTrierMotsAction());
        btnTrierNombres = new JButton("Trier Nombres");
        btnTrierNombres.setMaximumSize(dimBtn);
        btnTrierNombres.addActionListener(e -> btnTrierNombresAction());


        btnInfoMots = new JButton("Info Mots");
        btnInfoMots.setMaximumSize(dimBtn);
        btnInfoMots.addActionListener(e -> btnInfoMotsAction());
        btnInfoNombres = new JButton("Info Nombres");
        btnInfoNombres.setMaximumSize(dimBtn);
        btnInfoNombres.addActionListener(e -> btnInfoNombresAction());

        panEast.add(chbDoublons);
        panEast.add(btnExtraireMots);
        panEast.add(btnViderMots);
        panEast.add(Box.createVerticalGlue());
        panEast.add(btnExtraireNombres);
        panEast.add(btnViderNombres);
        panEast.add(Box.createVerticalGlue());
        panEast.add(radCroissant);
        panEast.add(radDecroissant);
        panEast.add(btnTrierMots);
        panEast.add(btnTrierNombres);
        panEast.add(Box.createVerticalGlue());
        panEast.add(btnInfoMots);
        panEast.add(btnInfoNombres);
        panEast.add(Box.createVerticalGlue());

        frame.add(panEast, BorderLayout.EAST);
    }

    public void createSouth() {
        panSouth = new JPanel();
        panSouth.setLayout(new BoxLayout(panSouth, BoxLayout.X_AXIS));

        labMots = new JLabel("Mots: 0");
        labNombres = new JLabel("Nombres: 0");
        btnQuitter = new JButton("Quitter");
        btnQuitter.setPreferredSize(dimBtn);
        btnQuitter.addActionListener(e -> btnQuitterAction());

        panSouth.add(Box.createHorizontalGlue());
        panSouth.add(labMots);
        panSouth.add(Box.createHorizontalStrut(20));
        panSouth.add(labNombres);
        panSouth.add(Box.createHorizontalGlue());
        panSouth.add(btnQuitter);

        frame.add(panSouth, BorderLayout.SOUTH);
    }

    // --- Action Listeners --- //

    private void btnRechercherAction() {
        int indice = 0; // indice présent
        int[] tabIndice = new int[]{}; // tableau d'indices trouvées
        String search = txfRecherche.getText(); // string à chercher
        String[] texte = txaMots.getText().split("\n"); // mots à chercher
        StringBuilder trouves = new StringBuilder(); // texte pour remplacer labFouille

        do {
            indice = nextIndexOf(texte, search, indice);
            if (indice != -1) {
                tabIndice = addTableau(tabIndice, indice);
                indice++;
            }
        } while (indice < texte.length && indice != -1);

        for (int i = 0; i < tabIndice.length; i++) {
            if (i == 0) {
                trouves.append(tabIndice[i]);
            } else if (i == tabIndice.length - 1) {
                trouves.append(" et ").append(tabIndice[i]);
            } else {
                trouves.append(", ").append(tabIndice[i]);
            }
        }

        if (tabIndice.length > 1) {
            labFouille.setText(tabIndice.length + " mots trouvés aux l'indices " + trouves);
        } else if (tabIndice.length == 1) {
            labFouille.setText(tabIndice.length + " mot trouvé à l'indice " + trouves);
        } else {
            labFouille.setText("Aucun mots trouvés.");
        }

    }

    private void btnExtraireMotsAction() {
        String[] tabString; // tableau des mots trouvés

        // On ajoute un espace après chaque contraction (c'était -> c' était), ensuite on le transforme en tableau
        tabString = txaTexte.getText().replaceAll("(\\b[a-zA-Z]{1,2}')", "$1 ").split("[^a-zA-ZÀ-ÿ']+");

        if (!chbDoublons.isSelected()) {
            tabString = remDoublons(tabString);
        }

        txaMots.setText(toStringNewline(tabString));
        labMots.setText("Mots: " + tabString.length);
    }

    private void btnViderMotsAction() {
        txaMots.setText(null);
        labMots.setText("Mots: 0");
    }

    private void btnExtraireNombresAction() {
        int[] tabInt; // tableau des int trouvés
        String[] tabString; // tableau des mots trouvés

        tabString = txaTexte.getText().split("[^0-9-]+");
        tabInt = tabStringAInt(tabString);

        txaNombres.setText(toStringNewline(tabInt));
        labNombres.setText("Nombres: " + tabInt.length);
    }

    private void btnViderNombresAction() {
        txaNombres.setText(null);
        labNombres.setText("Nombres: 0");
    }

    private void btnTrierMotsAction() {
        String[] tabMots = txaMots.getText().split("\n"); // mots à trier

        if (radCroissant.isSelected()) {
            triSSSUpString(tabMots);
        } else {
            triSSSDownString(tabMots);
        }

        txaMots.setText(toStringNewline(tabMots));
    }

    private void btnTrierNombresAction() {
        String[] tabString = txaNombres.getText().split("\n"); // nombres à trier
        int[] tabInt = tabStringAInt(tabString); // tableau des int

        if (radCroissant.isSelected()) {
            triSSSUpInt(tabInt);
        } else {
            triSSSDownInt(tabInt);
        }

        txaNombres.setText(toStringNewline(tabInt));
    }

    private void btnInfoMotsAction() {
        String[] tabString = txaMots.getText().split("\n"); // input
        String message; // message pour la boite d'info
        String motCourt;// plus court mot
        String motLong; // plus long mot

        if (tabString[0].equals("")) {
            message = "Aucun mots d'extrait.";
        } else {
            motCourt = sShorter(tabString);
            motLong = sLonger(tabString);
            message = "Mot le plus court: " + motCourt + "\nMot le plus long: " + motLong;
        }

        JOptionPane.showMessageDialog(frame, message, "Info Mots", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnInfoNombresAction() {
        String[] tabString = txaNombres.getText().split("\n"); // input
        String message; // message pour la boite d'info
        int[] tabInt = tabStringAInt(tabString); // tableau des int
        int nbrPetit; // plus petit nombre
        int nbrGrand; // plus grand nombre

        if (tabInt.length == 0) {
            message = "Aucun nombres d'extrait.";
        } else {
            nbrPetit = min(tabInt);
            nbrGrand = max(tabInt);
            message = "Nombre le plus petit: " + nbrPetit + "\nNombre le plus grand: " + nbrGrand;
        }

        JOptionPane.showMessageDialog(frame, message, "Info Nombres", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnQuitterAction() {
        int rep = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment quitter?", "Confirmation de fermeture", JOptionPane.YES_NO_OPTION);
        if (rep == 0)
            System.exit(0);
    }


    // --- Méthodes Utilitaires --- //

    /**
     * Recherche dans un tableau de string un string spécifier, et retourne l'index.
     *
     * @param tab tableau de string
     * @param txt le string à trouver
     * @param i   l'index qu'on commence à
     * @return l'index du string trouver, sinon -1
     */
    private int nextIndexOf(String[] tab, String txt, int i) {
        boolean trouve = false; // si on a trouver le prochain index

        while (i < tab.length && !trouve) {
            if (tab[i].equals(txt))
                trouve = true;
            else
                i++;
        }

        return trouve ? i : -1;
    }

    /**
     * Tri en croissant un tableau string par la méthode SSS (Straight Selection Sort)
     *
     * @param tab le tableau à trier
     */
    private void triSSSUpString(String[] tab) {
        if (tab.length != 0 && tab.length != 1) {
            for (int i = 0; i < tab.length - 1; i++) {
                int index = i;

                for (int j = i + 1; j < tab.length; j++) {
                    if (tab[j].compareTo(tab[index]) < 0) {
                        index = j;
                    }
                }

                String minString = tab[index];
                tab[index] = tab[i];
                tab[i] = minString;
            }
        }

    }

    /**
     * Tri en décroissant un tableau string par la méthode SSS (Straight Selection Sort)
     *
     * @param tab le tableau à trier
     */
    private void triSSSDownString(String[] tab) {
        if (tab.length != 0 && tab.length != 1) {
            for (int i = tab.length - 1; i >= 1; i--) {
                int index = i;

                for (int j = i - 1; j >= 0; j--) {
                    if (tab[j].compareTo(tab[index]) < 0) {
                        index = j;
                    }
                }

                String maxString = tab[index];
                tab[index] = tab[i];
                tab[i] = maxString;
            }
        }

    }

    /**
     * Tri en croissant un tableau int par la méthode SSS (Straight Selection Sort)
     *
     * @param tab le tableau à trier
     */
    private void triSSSUpInt(int[] tab) {
        if (tab.length != 0 && tab.length != 1) {
            for (int i = 0; i < tab.length - 1; i++) {
                int index = i;

                for (int j = i + 1; j < tab.length; j++) {
                    if (tab[j] < tab[index]) {
                        index = j;
                    }
                }

                int minInt = tab[index];
                tab[index] = tab[i];
                tab[i] = minInt;
            }
        }

    }

    /**
     * Tri en décroissant un tableau int par la méthode SSS (Straight Selection Sort)
     *
     * @param tab le tableau à trier
     */
    private void triSSSDownInt(int[] tab) {
        if (tab.length != 0 && tab.length != 1) {
            for (int i = tab.length - 1; i >= 1; i--) {
                int index = i;

                for (int j = i - 1; j >= 0; j--) {
                    if (tab[j] < tab[index]) {
                        index = j;
                    }
                }

                int maxInt = tab[index];
                tab[index] = tab[i];
                tab[i] = maxInt;
            }
        }

    }

    /**
     * Retourne le plus gros nombre dans un tableau
     *
     * @param tab le tableau à vérifier
     * @return le plus gros nombre
     */
    private int max(int[] tab) {
        triSSSDownInt(tab);
        return tab[0];
    }

    /**
     * Retourne le plus petit nombre dans un tableau
     *
     * @param tab le tableau à vérifier
     * @return le plus petit nombre
     */
    private int min(int[] tab) {
        triSSSUpInt(tab);
        return tab[0];
    }

    /**
     * Retourne le plus long string dans un tableau
     *
     * @param tab le tableau à vérifier
     * @return le plus petit nombre
     */
    private String sLonger(String[] tab) {
        triSSSDownString(tab);
        return tab[0];
    }

    /**
     * Retourne le plus long string dans un tableau
     *
     * @param tab le tableau à vérifier
     * @return le plus petit nombre
     */
    private String sShorter(String[] tab) {
        triSSSUpString(tab);
        return tab[0];
    }

    /**
     * Crée un nouveau tableau avec un int d'ajouté à la fin
     *
     * @param tab le tableau
     * @param i   le int qu'on ajoute
     * @return le tableau avec l'élément ajoutée
     */
    private int[] addTableau(int[] tab, int i) {
        int[] newTab = new int[tab.length + 1]; // le nouveau tableau à retourner

        for (int j = 0; j < tab.length; j++) {
            newTab[j] = tab[j];
        }

        newTab[newTab.length - 1] = i;

        return newTab;
    }

    /**
     * Crée un nouveau tableau avec un string d'ajouté à la fin
     *
     * @param tab le tableau
     * @param str le string qu'on ajoute
     * @return le tableau avec l'élément ajoutée
     */
    private String[] addTableau(String[] tab, String str) {
        String[] newTab = new String[tab.length + 1]; // le nouveau tableau à retourner

        for (int j = 0; j < tab.length; j++) {
            newTab[j] = tab[j];
        }
        newTab[newTab.length - 1] = str;

        return newTab;
    }

    /**
     * Converti un tableau string en string séparé par des newlines
     *
     * @param tab le tableau string
     * @return le texte
     */
    private String toStringNewline(String[] tab) {
        StringBuilder strBuild = new StringBuilder(); // texte pour envoyer

        if (tab.length == 0) {
            return "";
        }

        strBuild.append(tab[0]);
        for (int i = 1; i < tab.length; i++) {
            strBuild.append("\n").append(tab[i]);
        }

        return String.valueOf(strBuild);
    }

    /**
     * Converti un tableau int en string séparé par des newlines
     *
     * @param tab le tableau string
     * @return le texte
     */
    private String toStringNewline(int[] tab) {
        StringBuilder strBuild = new StringBuilder(); // texte pour envoyer

        if (tab.length == 0) {
            return "";
        }

        strBuild.append(tab[0]);
        for (int i = 1; i < tab.length; i++) {
            strBuild.append("\n").append(tab[i]);
        }

        return String.valueOf(strBuild);
    }

    /**
     * Crée un nouveau tableau sans de doublons
     *
     * @param tab le tableau
     * @return le tableau sans doublons
     */
    private String[] remDoublons(String[] tab) {
        int end = tab.length;
        boolean found;
        String[] sansDoublons = new String[]{};

        for (int i = 0; i < end; i++) {
            found = false;
            if (i > 0) {
                int j = 0;
                while (!found && j < sansDoublons.length) {
                    if (tab[i].equals(sansDoublons[j])) {
                        found = true;
                    }
                    j++;
                }
            }
            if (!found) {
                sansDoublons = addTableau(sansDoublons, tab[i]);
            }
        }

        return sansDoublons;
    }

    /**
     * Converti un tableau string à int en ignorant les int non valid
     * @param tab tableau string
     * @return tableau int
     */
    private int[] tabStringAInt(String[] tab) {
        int[] newTab = new int[]{}; // nouveau tab à retourner

        for (String str : tab) {
            int newValue;
            try {
                newValue = Integer.parseInt(str);
                newTab = addTableau(newTab, newValue);
            } catch (NumberFormatException ignored) {
            }
        }

        return newTab;
    }

    public static void main(String[] args) {
        new ViewTP3();
    }
}
