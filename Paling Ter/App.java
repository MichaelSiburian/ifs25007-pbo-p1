import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nilai = new ArrayList<>();

        // Baca nilai sampai menemukan baris "---"
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            nilai.add(Integer.parseInt(line));
        }

        // Input kosong (langsung "---") -> tidak menampilkan apa pun
        if (nilai.isEmpty()) {
            return;
        }

        // Hitung frekuensi tiap nilai
        Map<Integer, Integer> frekuensi = new HashMap<>();
        for (int n : nilai) {
            frekuensi.put(n, frekuensi.getOrDefault(n, 0) + 1);
        }

        int tertinggi = Collections.max(nilai);
        int terendah = Collections.min(nilai);

        // Terbanyak: frekuensi tertinggi, seri -> nilai lebih besar
        // Tersedikit: frekuensi terendah, seri -> nilai lebih kecil
        int terbanyakNilai = 0, terbanyakFrek = -1;
        int tersedikitNilai = 0, tersedikitFrek = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int v = entry.getKey();
            int f = entry.getValue();

            if (f > terbanyakFrek || (f == terbanyakFrek && v > terbanyakNilai)) {
                terbanyakFrek = f;
                terbanyakNilai = v;
            }

            if (f < tersedikitFrek || (f == tersedikitFrek && v < tersedikitNilai)) {
                tersedikitFrek = f;
                tersedikitNilai = v;
            }
        }

        // Jumlah Tertinggi: (nilai x frekuensi) terbesar, seri -> nilai lebih besar
        // Jumlah Terendah: (nilai x frekuensi) terkecil, seri -> nilai lebih kecil
        long jumlahTertinggiHasil = Long.MIN_VALUE;
        int jumlahTertinggiNilai = 0, jumlahTertinggiFrek = 0;
        long jumlahTerendahHasil = Long.MAX_VALUE;
        int jumlahTerendahNilai = 0, jumlahTerendahFrek = 0;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int v = entry.getKey();
            int f = entry.getValue();
            long hasil = (long) v * f;

            if (hasil > jumlahTertinggiHasil || (hasil == jumlahTertinggiHasil && v > jumlahTertinggiNilai)) {
                jumlahTertinggiHasil = hasil;
                jumlahTertinggiNilai = v;
                jumlahTertinggiFrek = f;
            }

            if (hasil < jumlahTerendahHasil || (hasil == jumlahTerendahHasil && v < jumlahTerendahNilai)) {
                jumlahTerendahHasil = hasil;
                jumlahTerendahNilai = v;
                jumlahTerendahFrek = f;
            }
        }

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakNilai + " (" + terbanyakFrek + "x)");
        System.out.println("Tersedikit: " + tersedikitNilai + " (" + tersedikitFrek + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiNilai + " * " + jumlahTertinggiFrek + " = " + jumlahTertinggiHasil);
        System.out.println("Jumlah Terendah: " + jumlahTerendahNilai + " * " + jumlahTerendahFrek + " = " + jumlahTerendahHasil);
    }
}