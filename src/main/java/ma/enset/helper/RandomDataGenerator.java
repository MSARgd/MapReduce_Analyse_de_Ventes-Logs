package ma.enset.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomDataGenerator {
    public static void main(String[] args) {
        // Définir les en-têtes
        String[] headers = {"Date", "Ville", "Produit", "Prix"};

        // Nombre de lignes
        int num_rows = 100;

        try (FileWriter fileWriter = new FileWriter("ventes.txt")) {
            // Écrire les en-têtes séparés par des espaces
            for (String header : headers) {
                fileWriter.write(header + " ");
            }
            fileWriter.write("\n");

            // Générer des données aléatoires et les écrire dans le fichier
            Random random = new Random();
            for (int i = 0; i < num_rows; i++) {
                String date = "2023-" + String.format("%02d", random.nextInt(12) + 1) + "-" + String.format("%02d", random.nextInt(28) + 1);
                String ville = randomChoice(new String[] {"Nouakchott", "Nouadhibou", "Rosso","Kiffa"});
                String produit = randomChoice(new String[] {"ProduitA", "ProduitB", "ProduitC"});
                double prix = 10.0 + (90.0 * random.nextDouble()); // Prix aléatoire entre 10.0 et 100.0

                // Écrire les données séparées par des espaces
                fileWriter.write(date + " " + ville + " " + produit + " " + String.format("%.2f", prix) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fonction pour choisir aléatoirement un élément d'un tableau
    private static String randomChoice(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}
