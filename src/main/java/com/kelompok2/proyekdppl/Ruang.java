package com.kelompok2.proyekdppl;

public class Ruang {
    private String kodeRuang;
    private String namaRuang;
    private int kapasitas;
    private String jenis; // "Kelas" atau "Laboratorium"
    private String status; // "Aktif" atau "Tidak Aktif"
    private Fasilitas fasilitas;

    // Constructor Kosong (Penting untuk JSON)
    public Ruang() {}

    // Constructor Lengkap (Sesuai dengan PanelKelolaRuangan)
    public Ruang(String kodeRuang, String namaRuang, int kapasitas, String jenis, String status, Fasilitas fasilitas) {
        this.kodeRuang = kodeRuang;
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
        this.jenis = jenis;
        this.status = status;
        this.fasilitas = fasilitas;
    }

    // --- GETTER & SETTER ---
    public String getKodeRuang() { return kodeRuang; }
    public void setKodeRuang(String kodeRuang) { this.kodeRuang = kodeRuang; }

    public String getNamaRuang() { return namaRuang; }
    public void setNamaRuang(String namaRuang) { this.namaRuang = namaRuang; }

    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Fasilitas getFasilitas() { return fasilitas; }
    public void setFasilitas(Fasilitas fasilitas) { this.fasilitas = fasilitas; }

    // --- INNER CLASS FASILITAS (STATIC) ---
    public static class Fasilitas {
        private boolean ac;
        private boolean proyektor;
        private boolean tv;

        public Fasilitas() {}

        // Constructor Fasilitas (Urutan: AC, Proyektor, TV)
        public Fasilitas(boolean ac, boolean proyektor, boolean tv) {
            this.ac = ac;
            this.proyektor = proyektor;
            this.tv = tv;
        }

        // Getter Fasilitas
        public boolean isAc() { return ac; }
        public void setAc(boolean ac) { this.ac = ac; }

        public boolean isProyektor() { return proyektor; }
        public void setProyektor(boolean proyektor) { this.proyektor = proyektor; }

        public boolean isTv() { return tv; }
        public void setTv(boolean tv) { this.tv = tv; }
    }
}