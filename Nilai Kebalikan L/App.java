import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Nilai Tengah: berlaku untuk semua ukuran (ganjil -> 1 elemen, genap -> blok 2x2)
        long nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            int mid = n / 2;
            nilaiTengah = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                        + matrix[mid][mid - 1] + matrix[mid][mid];
        }

        // Kasus khusus: matriks 1x1 dan 2x2 tidak punya pola L / Kebalikan L
        if (n < 3) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
            return;
        }

        long nilaiL = 0;
        long nilaiKebalikanL = 0;

        // Kolom pertama & kolom terakhir (semua baris)
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0];
            nilaiKebalikanL += matrix[i][n - 1];
        }
        // Baris terakhir (tanpa pojok kanan bawah) untuk L
        // Baris pertama (tanpa pojok kiri atas) untuk Kebalikan L
        for (int j = 1; j <= n - 2; j++) {
            nilaiL += matrix[n - 1][j];
            nilaiKebalikanL += matrix[0][j];
        }

        long perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        long dominan = (perbedaan == 0) ? nilaiTengah : Math.max(nilaiL, nilaiKebalikanL);

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}