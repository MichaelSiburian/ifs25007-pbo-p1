import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Baca dan validasi jam awal
        String baris1 = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        String[] bagianJam = baris1.split(":", -1);

        if (bagianJam.length != 2) {
            System.out.println("Jam tidak valid");
            return;
        }

        int jam, menit;
        try {
            jam = Integer.parseInt(bagianJam[0].trim());
            menit = Integer.parseInt(bagianJam[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return;
        }

        if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        // Jam awal valid, simpan untuk ditampilkan nanti
        int jamAwalJam = jam;
        int jamAwalMenit = menit;

        int totalMenitAwal = jam * 60 + menit; // total menit dalam basis 0-1439
        int current = totalMenitAwal;          // posisi menit berjalan (selalu dinormalisasi 0-1439)
        int totalGeser = 0;                    // akumulasi N mentah (tanpa normalisasi)
        int pergantianHari = 0;

        // 2. Baca perintah geser sampai "---"
        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();

            if (baris.equals("---")) {
                break;
            }

            if (baris.isEmpty()) {
                continue;
            }

            if (baris.matches("^[+-]\\d+$")) {
                int n;
                try {
                    n = Integer.parseInt(baris);
                } catch (NumberFormatException e) {
                    System.out.println("Perintah tidak valid");
                    continue;
                }

                totalGeser += n;
                current += n;

                // Normalisasi ke rentang 0-1439, hitung pergantian hari tiap kali melewati batas
                while (current >= 1440) {
                    current -= 1440;
                    pergantianHari++;
                }
                while (current < 0) {
                    current += 1440;
                    pergantianHari++;
                }
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        // 3. Tampilkan hasil akhir
        int jamAkhir = current / 60;
        int menitAkhir = current % 60;

        String totalMenitStr;
        if (totalGeser > 0) {
            totalMenitStr = "+" + totalGeser;
        } else {
            totalMenitStr = String.valueOf(totalGeser);
        }

        System.out.printf("Jam Awal: %02d:%02d%n", jamAwalJam, jamAwalMenit);
        System.out.printf("Jam Akhir: %02d:%02d%n", jamAkhir, menitAkhir);
        System.out.println("Total Menit: " + totalMenitStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}