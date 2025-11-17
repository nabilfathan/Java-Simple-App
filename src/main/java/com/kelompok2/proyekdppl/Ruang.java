package com.kelompok2.proyekdppl;
// File: Ruang.java

public class Ruang {
    private String kodeRuang;
    private String namaRuang;
    private int kapasitas;
    private String jenis;
    private String status;
    private Fasilitas fasilitas;

    // --- INI BAGIAN PENTING YANG HILANG ---
    // (Getter untuk semua variabel)

    public String getKodeRuang() {
        return kodeRuang;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public String getJenis() {
        return jenis;
    }

    public String getStatus() {
        return status;
    }

    public Fasilitas getFasilitas() {
        return fasilitas;
    }

    // Inner class untuk fasilitas
    public class Fasilitas {
        private boolean proyektor;
        private boolean ac;
        private boolean tv;
        
        // Getter untuk fasilitas
        public boolean isProyektor() {
            return proyektor;
        }

        public boolean isAc() {
            return ac;
        }

        public boolean isTv() {
            return tv;
        }
    }
}