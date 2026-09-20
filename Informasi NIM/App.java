import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nim = scanner.nextLine().trim();

        // Validasi panjang NIM harus tepat 8 karakter
        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        // Ambil 3 karakter pertama sebagai prefix program studi
        String prefix = nim.substring(0, 3);
        String programStudi = getProgramStudi(prefix);

        if (programStudi == null) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        // Karakter indeks 3-4 -> kode angkatan, digabung dengan "20" di depan
        String kodeAngkatan = nim.substring(3, 5);
        int angkatan = Integer.parseInt("20" + kodeAngkatan);

        // Karakter indeks 5-7 -> nomor urut mahasiswa
        String kodeUrutan = nim.substring(5, 8);
        int urutan = Integer.parseInt(kodeUrutan);

        // Tampilkan hasil (perhatikan penulisan "Inforamsi" sesuai spesifikasi)
        System.out.println("Inforamsi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + programStudi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }

    // Mencocokkan prefix NIM dengan tabel program studi
    private static String getProgramStudi(String prefix) {
        switch (prefix) {
            case "11S": return "Sarjana Informatika";
            case "12S": return "Sarjana Sistem Informasi";
            case "13S": return "Sarjana Teknik Elektro";
            case "21S": return "Sarjana Manajemen Rekayasa";
            case "22S": return "Sarjana Teknik Metalurgi";
            case "31S": return "Sarjana Teknik Bioproses";
            case "32S": return "Sarjana Bioteknologi";
            case "114": return "Diploma 4 Teknologi Rekasaya Perangkat Lunak";
            case "113": return "Diploma 3 Teknologi Informasi";
            case "133": return "Diploma 3 Teknologi Komputer";
            default: return null;
        }
    }
}