package com.kelompok2.proyekdppl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataManager {

    private List<User> listUsers;
    private List<Ruang> listRuangan;
    private List<Reservasi> listReservasi;
    private Gson gson;

    // PATH FILES
    private final String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private final String PATH_RESERVASI = BASE_PATH + "reservasi.json";
    private final String PATH_RUANGAN = BASE_PATH + "ruangan.json";
    private final String PATH_USERS = BASE_PATH + "users.json";

    public DataManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        
        System.out.println("=== DEBUG PATH ===");
        System.out.println("Lokasi File Reservasi: " + PATH_RESERVASI);
        
        // Load data awal (Cukup sekali saat aplikasi mulai!)
        listUsers = loadData(PATH_USERS, new TypeToken<List<User>>(){}.getType());
        listRuangan = loadData(PATH_RUANGAN, new TypeToken<List<Ruang>>(){}.getType());
        listReservasi = loadData(PATH_RESERVASI, new TypeToken<List<Reservasi>>(){}.getType());
        
        System.out.println("Data Loaded: " + listReservasi.size() + " reservasi.");
    }

    private <T> List<T> loadData(String filePath, Type type) {
        File file = new File(filePath);
        // Buat file kosong [] jika tidak ada, agar tidak error
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
        
        try (FileReader reader = new FileReader(file)) {
            List<T> data = gson.fromJson(reader, type);
            return (data != null) ? data : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // ==========================================================
    // BAGIAN RESERVASI (SUDAH DIPERBAIKI)
    // ==========================================================

    public void saveReservasi() {
        try (FileWriter writer = new FileWriter(PATH_RESERVASI)) {
            gson.toJson(listReservasi, writer);
            System.out.println("SUKSES: Data reservasi tersimpan ke JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReservasi(Reservasi reservasiBaru) {
        // 1. Update Memory (RAM) -> Agar UI langsung update tanpa baca file
        listReservasi.add(reservasiBaru);
        // 2. Update File (Disk) -> Agar permanen
        saveReservasi(); 
        System.out.println("Reservasi baru ditambahkan: " + reservasiBaru.getIdReservasi());
    }

    public boolean updateStatusReservasi(String idReservasi, String statusBaru) {
        for (Reservasi r : listReservasi) {
            if (r.getIdReservasi().equals(idReservasi)) {
                r.setStatus(statusBaru); // Update RAM
                saveReservasi();         // Update File
                return true;
            }
        }
        return false;
    }

    // PERBAIKAN UTAMA: HAPUS reloadReservasi() DISINI
    // Cukup kembalikan list yang ada di memori.
    public List<Reservasi> getAllReservasi() { 
        return listReservasi; 
    }

    // ==========================================================
    // BAGIAN DASHBOARD & UTILS
    // ==========================================================
    
    public int getCountTotal() { return listReservasi.size(); }
    
    public int getCountByStatus(String status) {
        int count = 0;
        for (Reservasi r : listReservasi) {
            if (r.getStatus().equalsIgnoreCase(status)) count++;
        }
        return count;
    }

    // ==========================================================
    // BAGIAN USER & LOGIN
    // ==========================================================

    public User login(String nim, String password) {
        if (listUsers == null) return null;
        for (User user : listUsers) {
            if (user.getNim().equals(nim) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public User getUserByNIM(String nim) {
        if (listUsers == null) return null;
        for (User user : listUsers) {
            if (user.getNim().equals(nim)) return user;
        }
        return null;
    }

    // ==========================================================
    // BAGIAN RUANGAN
    // ==========================================================
    
    public List<Ruang> getAllRuangan() { return listRuangan; }

    public Ruang getRuanganByKode(String kode) {
        return listRuangan.stream().filter(r -> r.getKodeRuang().equals(kode)).findFirst().orElse(null);
    }
    
    public void saveRuangan() {
        try (FileWriter writer = new FileWriter(PATH_RUANGAN)) {
            gson.toJson(listRuangan, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRuangan(Ruang ruangBaru) {
        if (listRuangan == null) return false;
        for (int i = 0; i < listRuangan.size(); i++) {
            if (listRuangan.get(i).getKodeRuang().equals(ruangBaru.getKodeRuang())) {
                listRuangan.set(i, ruangBaru);
                saveRuangan();
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteRuangan(String kodeRuang) {
        boolean removed = listRuangan.removeIf(r -> r.getKodeRuang().equals(kodeRuang));
        if (removed) saveRuangan();
        return removed;
    }

    public void addRuangan(Ruang ruang) {
        listRuangan.add(ruang);
        saveRuangan();
    }
}