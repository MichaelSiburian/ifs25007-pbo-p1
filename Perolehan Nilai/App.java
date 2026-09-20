import java.util.Scanner;

public class App {

    static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};
    static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Baca 6 baris bobot komponen (PA, T, K, P, UTS, UAS)
        int[] bobotTop = new int[6];
        for (int i = 0; i < 6; i++) {
            bobotTop[i] = Integer.parseInt(sc.nextLine().trim());
        }

        int totalBobot = 0;
        for (int b : bobotTop) totalBobot += b;

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        long[] totalX = new long[6];      // akumulasi bobot data per komponen
        long[] perolehanX = new long[6];  // akumulasi perolehan (sudah di-clamp) per komponen

        // 2. Baca baris data komponen sampai "---"
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("---")) {
                break;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            String bobotStr = parts[1].trim();
            String perolehanStr = parts[2].trim();

            int bobotData;
            int perolehanData;
            try {
                bobotData = Integer.parseInt(bobotStr);
                perolehanData = Integer.parseInt(perolehanStr);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            int idx = indexOfSimbol(simbol);
            if (idx == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            // Validasi clamp
            if (perolehanData > bobotData) perolehanData = bobotData;
            if (perolehanData < 0) perolehanData = 0;

            totalX[idx] += bobotData;
            perolehanX[idx] += perolehanData;
        }

        // 3. Hitung & cetak hasil
        System.out.println("Perolehan Nilai:");

        // Semua perhitungan kontribusi/nilai akhir dilakukan dalam satuan "sen"
        // (nilai x 100) memakai bilangan bulat (long) agar tidak ada galat
        // pembulatan floating-point (mis. 57.0 yang jadi 56.999999999999).
        long nilaiAkhirCent = 0;
        for (int i = 0; i < 6; i++) {
            long perolehanX100;
            if (totalX[i] == 0) {
                perolehanX100 = 0;
            } else {
                perolehanX100 = (perolehanX[i] * 100) / totalX[i];
            }

            // kontribusiX = (perolehanX100 / 100) * bobotTop[i]
            // dikali 100 -> kontribusiCent = perolehanX100 * bobotTop[i]
            long kontribusiCent = perolehanX100 * bobotTop[i];
            nilaiAkhirCent += kontribusiCent;

            System.out.printf(">> %s: %d/100 (%s/%d)%n",
                    NAMA[i], perolehanX100, formatCent(kontribusiCent), bobotTop[i]);
        }

        System.out.println();
        System.out.printf(">> Nilai Akhir: %s%n", formatCent(nilaiAkhirCent));
        System.out.println(">> Grade: " + hitungGrade(nilaiAkhirCent));
    }

    static int indexOfSimbol(String simbol) {
        for (int i = 0; i < SIMBOL.length; i++) {
            if (SIMBOL[i].equals(simbol)) return i;
        }
        return -1;
    }

    // Format nilai dalam satuan sen (integer) menjadi string 2 desimal, mis. 5700 -> "57.00"
    static String formatCent(long cent) {
        long whole = cent / 100;
        long frac = Math.abs(cent % 100);
        return whole + "." + (frac < 10 ? "0" + frac : String.valueOf(frac));
    }

    // Bandingkan nilai akhir (dalam sen) dengan ambang batas (juga dalam sen)
    static String hitungGrade(long nilaiCent) {
        if (nilaiCent >= 7950) return "A";
        if (nilaiCent >= 7200) return "AB";
        if (nilaiCent >= 6450) return "B";
        if (nilaiCent >= 5700) return "BC";
        if (nilaiCent >= 4950) return "C";
        if (nilaiCent >= 3400) return "D";
        return "E";
    }
}