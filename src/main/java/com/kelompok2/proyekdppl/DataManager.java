package com.kelompok2.proyekdppl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataManager {

    private List<User> listUsers;
    private List<Ruang> listRuangan;
    private List<Reservasi> listReservasi;

    public DataManager() {
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
}