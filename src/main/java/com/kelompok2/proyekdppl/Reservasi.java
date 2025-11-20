package com.kelompok2.proyekdppl;

public class Reservasi {
    private String idReservasi;
    private String nimPemesan;
    private String kodeRuang;
    private String tanggal;
    private String jamMulai;
    private String jamSelesai;
    private String keperluan;
    private int jumlahOrang;
    private String status;
  
    public String getIdReservasi() {
        return idReservasi;
    }

    public String getNimPemesan() {
        return nimPemesan;
    }

    public String getKodeRuang() {
        return kodeRuang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public int getJumlahOrang() {
        return jumlahOrang;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}