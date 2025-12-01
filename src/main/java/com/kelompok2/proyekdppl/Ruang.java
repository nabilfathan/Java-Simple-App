package com.kelompok2.proyekdppl;

public class Ruang {
    private String kodeRuang;
    private String namaRuang;
    private int kapasitas;
    private String jenis;
    private String status;
    private Fasilitas fasilitas;

    // Getter methods
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

    // SETTER METHODS - penting untuk mengupdate data
    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFasilitas(Fasilitas fasilitas) {
        this.fasilitas = fasilitas;
    }

    // Inner class Fasilitas
    public static class Fasilitas {
        private boolean proyektor;
        private boolean ac;
        private boolean tv;
        
        public boolean isProyektor() {
            return proyektor;
        }

        public boolean isAc() {
            return ac;
        }

        public boolean isTv() {
            return tv;
        }

        // SETTER METHODS untuk Fasilitas
        public void setProyektor(boolean proyektor) {
            this.proyektor = proyektor;
        }

        public void setAc(boolean ac) {
            this.ac = ac;
        }

        public void setTv(boolean tv) {
            this.tv = tv;
        }
    }
}