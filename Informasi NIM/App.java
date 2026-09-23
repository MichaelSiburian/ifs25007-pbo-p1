import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nim = scanner.nextLine().trim();

        // 1. Validasi panjang NIM harus tepat 8 karakter
        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        // 2. Ambil 3 karakter pertama sebagai prefix program studi
        String prefix = nim.substring(0, 3);

        // 3. Tabel prefix -> nama program studi
        Map<String, String> prodiMap = new HashMap<>();
        prodiMap.put("11S", "Sarjana Informatika");
        prodiMap.put("12S", "Sarjana Sistem Informasi");
        prodiMap.put("13S", "Sarjana Teknik Elektro");
        prodiMap.put("21S", "Sarjana Manajemen Rekayasa");
        prodiMap.put("22S", "Sarjana Teknik Metalurgi");
        prodiMap.put("31S", "Sarjana Teknik Bioproses");
        prodiMap.put("32S", "Sarjana Bioteknologi");
        prodiMap.put("114", "Diploma 4 Teknologi Rekayasa Perangkat Lunak");
        prodiMap.put("113", "Diploma 3 Teknologi Informasi");
        prodiMap.put("133", "Diploma 3 Teknologi Komputer");

        // 4. Jika prefix tidak ditemukan, hentikan program
        if (!prodiMap.containsKey(prefix)) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        String programStudi = prodiMap.get(prefix);

        // 5. Ambil karakter indeks 3-4, tambahkan "20" di depan -> angkatan
        int angkatan = Integer.parseInt("20" + nim.substring(3, 5));

        // 6. Ambil karakter indeks 5-7 -> nomor urut
        int urutan = Integer.parseInt(nim.substring(5, 8));

        // 7. Tampilkan informasi lengkap
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + programStudi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }
}