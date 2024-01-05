package kelompok.AbsenKantorService.utils;

import org.springframework.stereotype.Service;

@Service
public class CapitalWord {

    public String capitalizeWords(String input) {
        // Memisahkan kata-kata
        String[] words = input.split("\\s+");

        // Menggabungkan kata-kata yang sudah dimanipulasi
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                // Menggabungkan kata dengan awal huruf besar
                result.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Menghapus spasi ekstra di akhir
        return result.toString().trim();
    }
}
