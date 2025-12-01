package com.kelompok2.proyekdppl;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataManager {

    private List<User> listUsers;
    private List<Ruang> listRuangan;
    private List<Reservasi> listReservasi;
    private Gson gson;

    public DataManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        
        listUsers = loadData("users.json", new TypeToken<List<User>>(){}.getType());
        listRuangan = loadData("ruangan.json", new TypeToken<List<Ruang>>(){}.getType());
        listReservasi = loadData("reservasi.json", new TypeToken<List<Reservasi>>(){}.getType());
        
        System.out.println("DataManager: " + listUsers.size() + " user, " + 
                           listRuangan.size() + " ruangan, " + 
                           listReservasi.size() + " reservasi dimuat.");
    }

    private <T> T loadData(String filename, Type type) {
        try (InputStream inputStream = DataManager.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                System.err.println("File JSON tidak ditemukan di 'src/main/resources/': " + filename);
                return (T) Collections.emptyList(); 
            }
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
            return (T) Collections.emptyList(); 
        }
    }

    // Method untuk menyimpan data ruangan ke JSON
    public boolean saveRuanganToJson() {
        try {
            // Cari file ruangan.json di beberapa lokasi yang mungkin
            File file = findRuanganJsonFile();
            
            if (file == null) {
                System.err.println("File ruangan.json tidak ditemukan di mana pun!");
                return false;
            }
            
            // Tulis data ke file
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(listRuangan, writer);
                System.out.println("Data ruangan berhasil disimpan ke: " + file.getAbsolutePath());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Gagal menyimpan data ruangan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Method untuk mencari file ruangan.json
    private File findRuanganJsonFile() {
        // Coba beberapa lokasi yang mungkin
        String[] possiblePaths = {
            "src/main/resources/ruangan.json",
            "resources/ruangan.json", 
            "ruangan.json",
            "target/classes/ruangan.json"
        };
        
        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
        }
        
        // Jika tidak ditemukan, coba buat file baru di src/main/resources
        try {
            File newFile = new File("src/main/resources/ruangan.json");
            newFile.getParentFile().mkdirs(); // Buat direktori jika belum ada
            return newFile;
        } catch (Exception e) {
            System.err.println("Gagal membuat file ruangan.json baru: " + e.getMessage());
        }
        
        return null;
    }

    public User login(String nim, String password) {
        if (listUsers == null || listUsers.isEmpty()) {
            System.err.println("Login gagal: Tidak ada data user.");
            return null;
        }
        for (User user : listUsers) {
            if (user.getNim().equals(nim) && user.getPassword().equals(password)) {
                System.out.println("Login BERHASIL untuk: " + user.getNama() + " (Role: " + user.getRole() + ")");
                return user; 
            }
        }
        System.err.println("Login gagal: NIM atau Password salah.");
        return null; 
    }
   
    public List<Reservasi> getReservasiByNIM(String nim) {
        return listReservasi.stream()
                .filter(r -> r.getNimPemesan().equals(nim))
                .collect(Collectors.toList());
    }
    
    public List<Ruang> getAllRuangan() {
        return listRuangan;
    }
    
    public List<Reservasi> getAllReservasi() {
        return listReservasi;
    }
    
    public User getUserByNIM(String nim) {
        if (listUsers == null) return null;
        
        for (User user : listUsers) {
            if (user.getNim().equals(nim)) {
                return user;
            }
        }
        return null;
    }
    
    // Method untuk mendapatkan ruangan berdasarkan kode
    public Ruang getRuanganByKode(String kodeRuang) {
        if (listRuangan == null) return null;
        
        return listRuangan.stream()
                .filter(ruang -> ruang.getKodeRuang().equals(kodeRuang))
                .findFirst()
                .orElse(null);
    }
    
    // Method untuk update data ruangan
    public boolean updateRuangan(Ruang ruangUpdated) {
        if (listRuangan == null) return false;
        
        for (int i = 0; i < listRuangan.size(); i++) {
            Ruang ruang = listRuangan.get(i);
            if (ruang.getKodeRuang().equals(ruangUpdated.getKodeRuang())) {
                // Ganti ruangan lama dengan ruangan yang sudah diupdate
                listRuangan.set(i, ruangUpdated);
                System.out.println("Ruangan " + ruangUpdated.getKodeRuang() + " berhasil diupdate di memory");
                
                // Simpan perubahan ke JSON
                boolean saveSuccess = saveRuanganToJson();
                if (saveSuccess) {
                    System.out.println("Perubahan ruangan " + ruangUpdated.getKodeRuang() + " berhasil disimpan ke JSON");
                    return true;
                } else {
                    System.err.println("Gagal menyimpan perubahan ruangan ke JSON");
                    return false;
                }
            }
        }
        
        System.err.println("Ruangan " + ruangUpdated.getKodeRuang() + " tidak ditemukan untuk diupdate");
        return false;
    }
}