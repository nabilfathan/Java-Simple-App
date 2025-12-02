package com.kelompok2.proyekdppl;

public class Reservasi {
    private String idReservasi;
    private String nimPemesan;
    private String kodeRuang;
    private String tanggal;
    private String jamMulai;
    private String jamSelesai;
    private String keperluan;
    private String status;

    // CONSTRUCTOR KOSONG (Penting untuk JSON)
    public Reservasi() {}

    // CONSTRUCTOR LENGKAP (8 Parameter)
    public Reservasi(String idReservasi, String nimPemesan, String kodeRuang, 
                     String tanggal, String jamMulai, String jamSelesai, 
                     String keperluan, String status) {
        this.idReservasi = idReservasi;
        this.nimPemesan = nimPemesan;
        this.kodeRuang = kodeRuang;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.keperluan = keperluan;
        this.status = status;
    }

    // --- GETTER & SETTER ---
    public String getIdReservasi() { return idReservasi; }
    public void setIdReservasi(String idReservasi) { this.idReservasi = idReservasi; }

    public String getNimPemesan() { return nimPemesan; }
    public void setNimPemesan(String nimPemesan) { this.nimPemesan = nimPemesan; }

    public String getKodeRuang() { return kodeRuang; }
    public void setKodeRuang(String kodeRuang) { this.kodeRuang = kodeRuang; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getJamMulai() { return jamMulai; }
    public void setJamMulai(String jamMulai) { this.jamMulai = jamMulai; }

    public String getJamSelesai() { return jamSelesai; }
    public void setJamSelesai(String jamSelesai) { this.jamSelesai = jamSelesai; }

    public String getKeperluan() { return keperluan; }
    public void setKeperluan(String keperluan) { this.keperluan = keperluan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}